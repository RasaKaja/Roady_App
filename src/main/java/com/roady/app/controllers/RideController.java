package com.roady.app.controllers;

import com.roady.app.entities.Car;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.services.CarService;
import com.roady.app.services.RideService;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

@Autowired
    private UserController userController;

@Autowired
private CarService carService;

@Autowired
private UserService userService;


    @PostMapping("ride_requests_save")
    public String createRideRequest(
            String destinationPoint,
            String departurePoint,
            Date departureDate,
            String departureTime,
            String ridePrice,
            String paymentType
    ){
        if (userController.currentUser.getCar()==null){
            return "ride_request?status=no_car";
        }else{
            Ride ride = new Ride();
            ride.setDeparturePoint(departurePoint);
            ride.setDepartureDate(departureDate);
            ride.setDestinationPoint(destinationPoint);
            ride.setDepartureTime(departureTime);
            ride.setRidePrice(ridePrice);
            ride.setPaymentType(paymentType);

            User user = userController.currentUser;
            Car car = carService.getCarById(user.getCar().getId());
            ride.setCar(car);
            rideService.saveRideRequest(ride);
            return "redirect:transport_offer?status=request_added";
        }}

    @GetMapping("/my_active_transport_offers")
    public String showMyTransportOffersPage(Model model){
        if(userController.currentUser==null){
            return"redirect:login";
        }
        try{
            userController.currentUser.getCar().getId();

        }catch (Exception e){
            return "redirect:cars?status=no_car";
        }

        ArrayList<Ride> userRides = rideService.getAllUsersRides(userController.currentUser.getCar().getId());
        Long car_Id = userController.currentUser.getCar().getId();
        String platenumber = carService.getCarById(car_Id).getPlateNumber();
        model.addAttribute("userRides", userRides);
        model.addAttribute("plateNumber", platenumber);
        return "my_active_transport_offers";
    }

//    @PostMapping("/find_passenger")
//    public String findPassengerByDestAndDep(){
//        return "";
//    }

//    @PostMapping("/send_info_to_ride_update")
//    public String editRide(Ride ride){
//        try{
//            return "redirect:edit_ride";
//        }catch (Exception e){
//            return "redirect:my_active_transport_offers?status=error";
//        }
//
//    }
//
    @GetMapping("/edit_ride")
    public String displayEditRidePage(Ride ride){
//        try{
//            ride.getRideRequestId();
//            return "redirect:my_active_transport_offers?status=updated";
//        }catch (Exception e){
        return "edit_ride";
    }

    @GetMapping("/passenger_today")
    public String displayPassengerTodayPage(){
        return "passenger_today";
    }

    @GetMapping("/add_ride_request")
    public String showAddRideRequestPage(Model model){
        ArrayList<Ride> userRideRequests = rideService.getAllPassengerRides(userController.currentUser.getId());
        Integer userRideRequestCount = userRideRequests.size();

        model.addAttribute("userRidesCount", userRideRequestCount);
        return "add_ride_request";
    }

    @PostMapping("passenger_ride_requests_save")
    public String createPassengerRideRequest(
            String destinationPoint,
            String departurePoint,
            Date departureDate,
            String departureTime,
            String ridePrice,
            String paymentType
    ){
        if (userController.currentUser==null){
            return "redirect:login";
        }else{
            Ride ride = new Ride();
            ride.setDeparturePoint(departurePoint);
            ride.setDepartureDate(departureDate);
            ride.setDestinationPoint(destinationPoint);
            ride.setDepartureTime(departureTime);
            ride.setRidePrice(ridePrice);
            ride.setPaymentType(paymentType);

            User user = userService.getUserById(userController.currentUser.getId());
            System.out.println("user");
            ride.setPassenger(user);
            System.out.println("ride");
            rideService.saveRideRequest(ride);
            return "redirect:add_ride_request?status=request_added";
        }}

    @GetMapping("/my_active_ride_requests")
    public String showMyRideRequestsPage(Model model){
        if(userController.currentUser==null){
            return"redirect:login";
        }

        ArrayList<Ride> userRideRequests = rideService.getAllPassengerRides(userController.currentUser.getId());


        model.addAttribute("userRideRequests", userRideRequests);

        return "my_active_ride_requests";
    }

    @GetMapping("/edit_ride_request")
    public String showRideEditRequestPage(){
        return "edit_ride_request";
    }


//    @GetMapping("/ride_requests")
//    public String viewRidesList(Model model){
//        List<Ride> listRides = rideService.allRideRequestsList();
//        model.addAttribute("rideRequestList", listRides);
//        return "ride_requests";
//    }


//    @GetMapping("/ride_requests/edit")
//    public String editRideRequest(@PathVariable("rideRequestId") Long rideRequestId, Model model){
//        Ride ride = rideService.getRideRequestById(rideRequestId);
//        model.addAttribute("rideRequest", ride);
//        return "ride_request";
//    }


//    @GetMapping("/transport_offer")
//    public String showTransportOfferPage(){
//        return "transport_offer";
//    }

    //Required to think if needed, intend of creating it to finish RideRequest

//    @GetMapping("/ride_requests/update")
//    public String updateRideRequest(@PathVariable("rideRequestId") Long rideRequestId, Model model){
//        Ride ride = rideService.getRideRequestById(rideRequestId);
//        model.addAttribute("rideRequest", ride);
//        return "ride_request";
//    }

}