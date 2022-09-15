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

import java.util.ArrayList;
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
        rideRepository.save(rideRequest);
    }

      public Ride getRideRequestById(Long rideRequestId) {
        return rideRepository.findById(rideRequestId).get();
    }


    //method still not created at rideRepository
    public void deleteRideRequest(Long rideRequestId){
        System.out.println("tika servisa");
        rideRepository.deleteRideByRideRequestId(rideRequestId);
        System.out.println("izdzesa servisā");
    }

    public ArrayList<Ride> getAllUsersRides(Long id){
        return rideRepository.findAllByCar_Id(id);
    }

    public ArrayList<Ride> getAllPassengerRides(Long id){
        return rideRepository.findAllByPassengerId(id);
    }

    public ArrayList<Ride> getAllBySearch(String departurePoint ,String destinationPoint){
          return rideRepository.findAllByDeparturePointAndDestinationPointAndCarIsNull(destinationPoint, departurePoint);
    }

    public ArrayList<Ride> getAllDriversBySearch(String departurePoint ,String destinationPoint){return rideRepository.findAllByDeparturePointAndDestinationPointAndPassengerIsNull(destinationPoint, departurePoint);}


    public Ride lookUpRideById(Long id){
        return rideRepository.findByRideRequestId(id);
    }



}
