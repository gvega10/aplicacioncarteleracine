package cartelera.um.cartelera.listeners;

import cartelera.um.cartelera.entities.Review;

/**
 * Created by Christian on 02/02/2019.
 */

public interface AddReviewListener {
    void addReview(Review review);
    void editReview(Review review);
}
