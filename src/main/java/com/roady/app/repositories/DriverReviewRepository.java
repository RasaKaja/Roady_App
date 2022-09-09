package com.roady.app.repositories;

import com.roady.app.entities.DriverReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverReviewRepository extends JpaRepository<DriverReview, Long> {

    public Iterable<DriverReview> findDriverReviewById(Long id);
}
