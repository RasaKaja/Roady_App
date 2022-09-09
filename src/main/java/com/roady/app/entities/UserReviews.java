package com.roady.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserReviews {

    private List<Review> passengerReviewsResult;
    private List<Review> driverReviewsResult;

    public double addReviewForReviewClas(List<Review> review){
        double result = 0;
        for (Review r : review) {
            result += r.getReview();
        }
        return result;
    }

    public double findReviewPointAverage (List<Review> reviews) {
        int lengthOfReviews = reviews.size();
        double sum = addReviewForReviewClas(reviews);
        double result = sum / lengthOfReviews;

        // add a round function
        BigDecimal resultRound = BigDecimal.valueOf(result);
        resultRound = resultRound.setScale(2, RoundingMode.HALF_UP);
        return resultRound.doubleValue();
    }
}