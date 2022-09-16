package com.roady.app.services;

import com.roady.app.repositories.DriverReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class ReviewServiceTest {

    @Autowired
    private DriverReviewRepository driverReviewRepository;

    @Test
    void avgRating() {

        Long id = 1l;
        Double averageRating = driverReviewRepository.avgRating(id);

        System.out.println("Driver average rating: " + averageRating);

    }
}