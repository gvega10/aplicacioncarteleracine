package cartelera.um.cartelera.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.entities.User;

/**
 * Created by Christian on 25/11/2017.
 */

public class AuthenticationManager {
    private String USERSHAREREF = "currentUser";
    private Context mContext;
    private Gson gson;

    public AuthenticationManager(){}

    public AuthenticationManager(Context context){
        this.mContext = context;
    }

    public User getCurrentUser() {
        String shareUser = getSharedPref().getString(USERSHAREREF, null);
        return getGson().fromJson(shareUser, User.class);
    }

    public void setCurrentUser(User currentUser) {
        String userToSave = getGson().toJson(currentUser);
        SharedPreferences.Editor editor = getSharedPref().edit();
        editor.putString(USERSHAREREF, userToSave);
        editor.commit();
    }

    private Gson getGson(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    private SharedPreferences getSharedPref(){
        return mContext.getSharedPreferences( mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

}
