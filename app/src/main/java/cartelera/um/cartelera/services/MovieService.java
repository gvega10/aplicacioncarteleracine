package cartelera.um.cartelera.services;

import java.util.List;

import cartelera.um.cartelera.entities.Movie;
import cartelera.um.cartelera.entities.Review;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Christian on 17/01/2019.
 */

public interface MovieService {

    @GET("movies")
    @Headers({"Content-Type: application/json; charset=UTF-8"})
    Observable<List<Movie>> getAllMovies();

    @GET("movies/{id}/reviews")
    @Headers({"Content-Type: application/json; charset=UTF-8"})
    Observable<List<Review>> getMovieReviews(@Path(value = "id") String movieId);


    @POST("movies/{id}/reviews")
    @Headers({"Content-Type: application/json; charset=UTF-8"})
    Observable<Review> addReview(@Path(value = "id") String movieId, @Field("message") String message, @Field("score") double score);
}
