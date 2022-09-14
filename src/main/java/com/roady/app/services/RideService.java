package com.roady.app.services;

import com.roady.app.entities.Car;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.repositories.CarRepository;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.roady.app.repositories.RideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class RideService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RideRepository rideRepository;

    //CRUD operations
    public void saveRideRequest(Ride rideRequest){
        User user = userRepository.getReferenceById(1l);
        Car car = carRepository.getReferenceById(1l);
        rideRepository.save(rideRequest);
        carRepository.save(car);
        userRepository.save(user);
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
