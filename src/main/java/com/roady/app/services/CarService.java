package com.roady.app.services;

import com.roady.app.entities.Car;
import com.roady.app.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void saveNewCar(Car car){
        carRepository.save(car);
    }

    public List<Car> allCarsList(){
        return carRepository.findAll();
    }
}
