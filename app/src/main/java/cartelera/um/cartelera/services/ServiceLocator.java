package cartelera.um.cartelera.services;

/**
 * Created by Christian on 15/01/2019.
 */

import com.google.gson.Gson;

import cartelera.um.cartelera.flow.FlowManager;
import io.reactivex.Scheduler;

public interface  ServiceLocator {

    FlowManager getFlowManager();

    Gson getGson();

    GenreService getGenreService();

    MovieService getMovieService();

    AuthService getAuthService();

    Scheduler getSchedulers();
}