package com.roady.app.repositories;

import com.roady.app.entities.Ride;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {


    ArrayList<Ride> findAllByCar_Id(Long id);

    ArrayList<Ride> findAllByPassengerId(Long Id);
}
