package cartelera.um.cartelera.services;

import android.content.Context;

/**
 * Created by Christian on 15/01/2019.
 */

public class ServiceLocatorFactory {
    private static ServiceLocator mServiceLocator = null;

    public static ServiceLocator getInstance(Context context) {
        if (mServiceLocator == null) {
            mServiceLocator = new ServiceLocatorImpl(context);
        }
        return mServiceLocator;
    }

    public ServiceLocatorFactory() {
    }
}
