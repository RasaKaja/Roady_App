package com.roady.app.controllers;

import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("ride_requests/save")
    public String createRideRequest(Ride ride){
        rideService.saveRideRequest(ride);
        return "ride_requests";
    }

    @GetMapping("/ride_requests")
    public String viewRidesList(Model model){
        List<Ride> listRides = rideService.allRideRequestsList();
        model.addAttribute("rideRequestList", listRides);
        return "ride_requests";
    }


    @GetMapping("/ride_requests/edit")
    public String editRideRequest(@PathVariable("rideRequestId") Long rideRequestId, Model model){
        Ride ride = rideService.getRideRequestById(rideRequestId);
        model.addAttribute("rideRequest", ride);
        return "ride_request";
    }


    //Required to think if needed, intend of creating it to finish RideRequest

//    @GetMapping("/ride_requests/update")
//    public String updateRideRequest(@PathVariable("rideRequestId") Long rideRequestId, Model model){
//        Ride ride = rideService.getRideRequestById(rideRequestId);
//        model.addAttribute("rideRequest", ride);
//        return "ride_request";
//    }

}