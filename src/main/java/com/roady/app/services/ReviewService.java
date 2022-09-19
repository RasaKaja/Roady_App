package com.roady.app.services;

import com.roady.app.entities.*;
import com.roady.app.repositories.DriverReviewRepository;
import com.roady.app.repositories.PassengerReviewRepository;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private PassengerReviewRepository passengerReviewRepository;

    @Autowired
    private DriverReviewRepository driverReviewRepository;



    public double findDriverRating(Ride ride){
        DriverReview driverReview = driverReviewRepository.findByRide(ride);
        Double rating = driverReview.getReview();
        return rating;
    }

    public DriverReview getByRide(Ride ride){
        return driverReviewRepository.findByRide(ride);
    }

    public void saveDriversReviewToPassenger(DriverReview driverReview){
        driverReviewRepository.save(driverReview);
    }

    public void savePassengerReviewToDriver(PassengerReview passengerReview) {passengerReviewRepository.save(passengerReview);}


}