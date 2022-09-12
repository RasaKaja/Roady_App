package com.roady.app.controllers;

import com.roady.app.entities.Car;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.services.CarService;
import com.roady.app.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

@Autowired
    private UserController userController;

@Autowired
private CarService carService;


    @PostMapping("ride_requests_save")
    public String createRideRequest(
            String destinationPoint,
            String departurePoint,
            String departureDate,
            String departureTime,
            String ridePrice,
            String paymentType
    ){
        if (userController.currentUser.getCar()==null){
            return "ride_request?status=no_car";
        }else{
            Ride ride = new Ride();
//            ride.setCar(userController.currentUser.getCar());
            ride.setDeparturePoint(departurePoint);
            ride.setDepartureDate(departureDate);
            ride.setDestinationPoint(destinationPoint);
            ride.setDepartureTime(departureTime);
            ride.setRidePrice(ridePrice);
            ride.setPaymentType(paymentType);

            User user = userController.currentUser;
            Car car = carService.getCarById(user.getCar().getId());
            System.out.println(car.getCarType());
            ride.setCar(car);
            System.out.println(ride.getCar().getCarType());
            rideService.saveRideRequest(ride);
            return "redirect:transport_offer?status=request_added";
        }}

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