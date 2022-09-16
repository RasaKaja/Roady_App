package com.roady.app.controllers;

import com.roady.app.entities.Car;
import com.roady.app.entities.Ride;
import com.roady.app.entities.RideStatus;
import com.roady.app.entities.User;
import com.roady.app.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;
    public Ride currentRideRequest;


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


    @GetMapping("/update_ride_status")
    public String updateRideStatus(
            @RequestParam(name = "status", required = false) String status,
            Model model
    ){
        Long rideRequestId = currentRideRequest.getRideRequestId();
        String departurePoint = rideService.getRideRequestById(rideRequestId).getDeparturePoint();
        String destinationPoint = rideService.getRideRequestById(rideRequestId).getDestinationPoint();
        String departureDate = String.valueOf(rideService.getRideRequestById(rideRequestId).getDepartureDate());
        String departureTime = String.valueOf(rideService.getRideRequestById(rideRequestId).getDepartureTime());
        String paymentType = String.valueOf(rideService.getRideRequestById(rideRequestId).getPaymentType());
        RideStatus rideStatus = rideService.getRideRequestById(rideRequestId).getRideStatus();


        model.addAttribute("status", status);
        model.addAttribute("carType", departurePoint);
        model.addAttribute("plateNumber", destinationPoint);
        model.addAttribute("availableSeats", departureDate);
        model.addAttribute("availableSeats", departureTime);
        model.addAttribute("availableSeats", paymentType);
        model.addAttribute("availableSeats", rideStatus);

        return "ride_request";
    }


    @PostMapping("/update_ride_request_status")
    public String updateRequestStatus(RideStatus rideStatus) {
        try {
            Ride rideRequest = rideService.getRideRequestById(currentRideRequest.getRideRequestId());
            rideRequest.setRideStatus(rideStatus);
            rideService.saveRideRequest(rideRequest);

            return "redirect:ride?status=request_updated";
        } catch (Exception e) {
            return "redirect:ride?status=request_update_failed";
        }
    }

//    @GetMapping("/ride_requests/update_status")
//    public String updateRideRequest(@PathVariable("rideRequestId") Long rideRequestId, Model model){
//        Ride ride = rideService.getRideRequestById(rideRequestId);
//        model.addAttribute("rideRequest", ride);
//        return "ride_request";
//    }

}