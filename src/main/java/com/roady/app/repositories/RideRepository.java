package com.roady.app.repositories;

import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {


    ArrayList<Ride> findAllByCar_Id(Long id);

    ArrayList<Ride> findAllByPassengerId(Long Id);

    void deleteRideByRideRequestId(Long id);

    ArrayList<Ride> findAllByDeparturePointAndDestinationPointAndCarIsNull(String destinationPoint, String departurePoint);
    ArrayList<Ride> findAllByDeparturePointAndDestinationPointAndPassengerIsNull(String destinationPoint, String departurePoint);

    Ride findByRideRequestId(Long id);

}
