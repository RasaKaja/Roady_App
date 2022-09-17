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

//    @Autowired
//    private PassengerReview passengerReview;
//
//    @Autowired
//    private DriverReview driverReview;

    @Autowired
    private PassengerReviewRepository passengerReviewRepository;

    @Autowired
    private DriverReviewRepository driverReviewRepository;

    @Autowired
    private UserReviews userReviews;

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

//    public Iterable<User> getReviewInfo() {
//        Iterable<User> users = userRepository.findAll();
//        return users;
//    }

//    public boolean assessReview(double review, Long userId, String reviewType, Long rideId) {
//        if (!userService.checkIfIsUser(userId)) {
//            return false;
//        }
//
//        if (review >= 0 && review <= 5) {
//            if (reviewType.equals("passenger")) {
//                passengerReview.setRideId(rideId);
//                passengerReview.setUserId(userId);
//                passengerReview.setReview(review);
//                passengerReviewRepository.save(passengerReview);
//                return true;
//            }
//            if (reviewType.equals("driver")) {
//                driverReview.setRideId(rideId);
//                driverReview.setUserId(userId);
//                driverReview.setReview(review);
//                driverReviewRepository.save(driverReview);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public ReviewPartAndUser userInformation(Long id) {
//        if (userService.checkIfIsUser(id)) {
//            return null;
//        }
//
//        Optional<User> user = userRepository.findById(id);
//        Iterable<PassengerReview> passengerReviews = passengerReviewRepository.findPassengerReviewById(id);
//        Iterable<DriverReview> driverReviews = driverReviewRepository.findDriverReviewById(id);
//
//        // convert Iterable to list
//        List<com.roady.app.entities.Review> passengerReviewList = new ArrayList<>();
//        passengerReviews.forEach(passengerReviewList::add); // loop through passengerReviews and call passengerReviews.add(..) with current Item
//
//        List<com.roady.app.entities.Review> driverReviewList = new ArrayList<>();
//        driverReviews.forEach(driverReviewList::add);
//
//        userReviews.setPassengerReviewsResult(passengerReviewList);
//        userReviews.setDriverReviewsResult(driverReviewList);
//
//        ReviewPartAndUser reviewPartAndUser = new ReviewPartAndUser(user.get().getId(),
//                user.get().getFirstName(), user.get().getLastName(), userReviews);
//
//        return reviewPartAndUser;
//    }

//    public void configureUserInformationModel(Long id, Model model) {
//        ReviewPartAndUser userEntity = userInformation(id);
//
//        model.addAttribute("user", userEntity);
//        if (userEntity.getUserReviews().getDriverReviewsResult().size() > 0) {
//            model.addAttribute("driverAverage", userEntity.getUserReviews().findReviewPointAverage(userEntity.getUserReviews().getDriverReviewsResult()));
//        } else {
//            model.addAttribute("driverAverage", "N/A");
//        }
//
//        if (userEntity.getUserReviews().getPassengerReviewsResult().size() > 0) {
//            model.addAttribute("passengerAverage", userEntity.getUserReviews().findReviewPointAverage(userEntity.getUserReviews().getPassengerReviewsResult()));
//        } else {
//            model.addAttribute("passengerAverage", "N/A");
//        }
//    }

//    @Override
//    public Double avgRating(Long userId) {
//        if (userId == driverReview.getUserId()) {
//            driverReviewRepository.avgRating(userId);
//        }else {
//            passengerReviewRepository.avgRating(userId);
//        }
//        return avgRating(userId);
//    }
}