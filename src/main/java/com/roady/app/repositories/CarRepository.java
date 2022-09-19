package com.roady.app.repositories;

import com.roady.app.entities.Car;
import com.roady.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CarRepository extends JpaRepository<Car, Long> {

    ArrayList<Car> findAllByUserId(Long id);

    Car findCarById(Long Id);

    @Override
    void delete(Car entity);
}
