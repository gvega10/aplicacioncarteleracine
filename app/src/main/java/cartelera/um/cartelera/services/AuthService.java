package cartelera.um.cartelera.services;
import cartelera.um.cartelera.entities.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Christian on 31/01/2019.
 */

public interface AuthService {

    @FormUrlEncoded
    @POST("auth")
    Observable<User> signIn(@Field("email") String email, @Field("password") String password);

}
