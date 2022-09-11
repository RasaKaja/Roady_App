package com.roady.app.services;

import com.roady.app.controllers.UserController;
import com.roady.app.entities.Car;
import com.roady.app.entities.User;
import com.roady.app.repositories.CarRepository;
import com.roady.app.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

// C R U D  operations
    public void saveNewCar(Car car){
//        User user = userRepository.getReferenceById(1l);
        carRepository.save(car);

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

    public ArrayList<Car> getByUser(User user){
        return carRepository.findAllByUser(user);}

}