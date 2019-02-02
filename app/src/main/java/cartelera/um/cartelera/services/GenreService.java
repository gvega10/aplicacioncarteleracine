package cartelera.um.cartelera.services;

import java.util.List;

import cartelera.um.cartelera.entities.Genre;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Christian on 15/01/2019.
 */

public interface GenreService {

    @GET("genres")
    @Headers({"Content-Type: application/json; charset=UTF-8"})
    Observable<List<Genre>> getAllMovieGenres();

}
