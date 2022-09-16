package com.roady.app.controllers;

import com.roady.app.entities.Ride;
import com.roady.app.services.CarService;
import com.roady.app.services.RideService;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class RideSearchingController {
    @Autowired
    private RideService rideService;

    @Autowired
    private UserController userController;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    String departure;
    String destination;

    Long bookedRideId;

    @PostMapping("/send_info_for_ride_search")
    public String getRideSearchInfo(String destination, String departure){
        this.destination=destination;
        this.departure=departure;
        return "redirect:search_passengers?status=search_info_received";
    }

    @PostMapping("/send_info_for_passenger_ride_search")
    public String getPassengerRideSearchInfo(String destination, String departure){
        this.destination=destination;
        this.departure=departure;
        return "redirect:search_driver?status=search_info_received";
    }

    @PostMapping("/book_ride_request")
    public String getBookedRideRequestId(Long rideId){
        this.bookedRideId=rideId;
        return "redirect:ride_profile";
    }

    @PostMapping("/passenger_book_ride_request")
    public String getPassengerBookedRideRequestId(Long rideId){
        this.bookedRideId=rideId;
        return "redirect:drivers_ride_profile";
    }


    @GetMapping("/search_passengers")
    public String searchPassengersPage(Model model) {
                String destination = this.destination;
                String departure = this.departure;
                this.departure = this.destination = null;
                ArrayList<Ride> searchedRides = rideService.getAllBySearch(destination, departure);
                model.addAttribute("searchedRides", searchedRides);
                return "search_passengers";
        }

    @GetMapping("/search_driver")
    public String searchDriverPage(Model model) {
        String destination = this.destination;
        String departure = this.departure;
        this.departure = this.destination = null;
        ArrayList<Ride> searchedRides = rideService.getAllDriversBySearch(destination, departure);
        model.addAttribute("searchedRides", searchedRides);
        return "search_driver";
    }

    @GetMapping("/ride_profile")
    public String showRideProfile(Model model){
        Ride ride = rideService.getRideRequestById(this.bookedRideId);
        model.addAttribute("ride", ride);
        return "ride_profile";
    }

    @GetMapping("/drivers_ride_profile")
    public String showDriversRideProfile(Model model){
        Ride ride = rideService.getRideRequestById(this.bookedRideId);
        model.addAttribute("ride", ride);
        return "drivers_ride_profile";
    }

    @PostMapping("/confirm_booking_as_passenger")
    public String passengerConfirmsBooking(Long rideId){
        Ride ride = rideService.lookUpRideById(rideId);
        ride.setPassenger(userService.getUserById(userController.currentUser.getId()));
        ride.setIsFinished(true);
        rideService.saveRideRequest(ride);
        return "redirect:my_active_ride_requests";
    }

    @PostMapping("/confirm_booking_as_driver")
    public String driverConfirmsBooking(Long rideId){
        Ride ride = rideService.lookUpRideById(rideId);
        ride.setCar(carService.getCarById(userController.currentUser.getId()));
        ride.setIsFinished(true);
        rideService.saveRideRequest(ride);
        return "redirect:my_active_transport_offers";
    }

    @PostMapping("/send_info_for_ride_overview")
    public String getInfoForRideRequestDisplaying(Long rideId, String link){
        this.bookedRideId=rideId;
        return "redirect:finished_ride_profile?status="+link;
    }

    @GetMapping("/finished_ride_profile")
    public String showFinishedRideProfile(
            @RequestParam(name="status", required = false) String status,
            Model model){
        Ride ride = rideService.getRideRequestById(this.bookedRideId);
        model.addAttribute("ride", ride);
        model.addAttribute("status", status);
        return "finished_ride_profile";
    }

    @PostMapping("/return_to_previous_page")
    public String returnToPreviousPage(String link){
        return "redirect:"+link;
    }


    }

