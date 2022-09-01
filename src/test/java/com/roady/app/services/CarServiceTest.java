package com.roady.app.services;

import com.roady.app.entities.Car;
import com.roady.app.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CarServiceTest {

    @Autowired
    CarRepository carRepository;

//    @Test
//    public void testAllCarsList(){
//        System.out.println(carRepository.findAll());
//
//    }
}