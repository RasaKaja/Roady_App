package com.roady.app.services;

import com.roady.app.entities.Car;
import com.roady.app.entities.User;
import com.roady.app.repositories.CarRepository;
import com.roady.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

// C R U D  operations
    public void saveNewCar(Car car){
        User user = userRepository.getReferenceById(1l);
        carRepository.save(car);
        userRepository.save(user);
    }

    public List<Car> allCarsList(){
        return carRepository.findAll();
    }

    public Car getCarById(Long id){
        return carRepository.findById(id).get();
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

}