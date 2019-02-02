package cartelera.um.cartelera.flow;

/**
 * Created by Christian on 15/01/2019.
 */

import android.content.Context;
import android.content.Intent;

public interface FlowManager {


    int NAVIGATION_ACTIVITY = 1;

    int LOG_IN_ACTIVITY = 2;

    int MOVIE_ACTIVITY = 3;

    int CHANGE_PASSWORD = 4;

    int NOTIFICATION_ACTIVITY = 5;

    Intent getIntent(Context context, int flowId);

    Intent getIntentResetFlags(Context context, int flowId);
}
