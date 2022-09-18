//package com.roady.app;
//
//
//import com.roady.app.entities.Car;
//import com.roady.app.entities.DriverReview;
//import com.roady.app.entities.User;
//import com.roady.app.repositories.DriverReviewRepository;
//import com.roady.app.repositories.PassengerReviewRepository;
//import com.roady.app.repositories.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//public class ReviewRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private DriverReview driverReview;
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private DriverReviewRepository driverReviewRepository;
//
//    @Autowired
//    private PassengerReviewRepository passengerReviewRepository;
//
//    @Test
//    void testAverageRatingCounter(){
//        Long id = 1l;
//        Double averageRating = driverReviewRepository.avgRating(id);
//
//        System.out.println("Driver average rating: " + averageRating);
//
//        assertThat(5.6);
//    }
//
//
//}
