package com.roady.app.services;

import com.roady.app.controllers.UserController;
import com.roady.app.entities.Car;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.repositories.CarRepository;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.roady.app.repositories.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class RideService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserController userController;

    //CRUD operations
    public void saveRideRequest(Ride rideRequest){

        System.out.println("tika šeit");
        rideRepository.save(rideRequest);

    }

    public List<Ride> allRideRequestsList(){
        return rideRepository.findAll();
    }

    public Ride getRideRequestById(Long rideRequestId) {
        return rideRepository.findById(rideRequestId).get();
    }


    //method still not created at rideRepository
    public void deleteRideRequest(Long rideRequestId){
        rideRepository.deleteById(rideRequestId);
    }


}
