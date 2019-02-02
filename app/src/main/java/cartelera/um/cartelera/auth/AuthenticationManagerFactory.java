package cartelera.um.cartelera.auth;

import android.content.Context;

/**
 * Created by Christian on 25/11/2017.
 */

public class AuthenticationManagerFactory {
    private static AuthenticationManager mAuthManager;

    public static AuthenticationManager getIntance(Context context){
        if(mAuthManager == null){
            mAuthManager = new AuthenticationManager(context);
        }
        return mAuthManager;
    }

    public AuthenticationManagerFactory(){

    }
}
