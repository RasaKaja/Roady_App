package com.roady.app.repositories;

import com.roady.app.entities.Car;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    /* the email column is unique in the users table, so we define the findByEmail() method
     that returns a single User object based on email (no two users having the same email).*/
    @Query("SELECT u FROM User u where u.email=?1")
    User findUserByEmail(String email);

    @Override
    List<User> findAll();

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    ArrayList<Ride> findAllByCar_Id(Long id);

    ArrayList<Ride> findAllByPassengerId(Long Id);

    void deleteRideByRideRequestId(Long id);

    ArrayList<Ride> findAllByDeparturePointAndDestinationPointAndCarIsNullAndPassengerIsNotNull(String destinationPoint, String departurePoint);
    ArrayList<Ride> findAllByDeparturePointAndDestinationPointAndPassengerIsNullAndCarIsNotNull(String destinationPoint, String departurePoint);

    Ride findByRideRequestId(Long id);

    ArrayList<Ride> findAllByPassengerIdAndAndCarIsNull(Long id);
    ArrayList<Ride> findAllByPassengerIdAndAndCarIsNotNull(Long id);

    ArrayList<Ride> findAllByCarAndPassengerIsNotNull(Car car);

    ArrayList<Ride> findAllByCarAndPassengerIsNull(Car car);


    ArrayList<Ride> findAllByPassengerIdAndDriverReviewIsNotNull(Long Id);
    ArrayList<Ride> findAllByCar_IdAndPassengerReviewIsNotNull(Long id);

    Integer countAll();

}

//JpaRepository is "bigger" than CRUD repository, has more methods inside
