package cartelera.um.cartelera.services;

import cartelera.um.cartelera.entities.Review;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Christian on 02/02/2019.
 */

public interface ReviewService {
    @FormUrlEncoded
    @POST("/movies/5c438e3231357100170d04ff/reviews")
    Observable<Review> addReview(@Field("email") String email);

}
