package cartelera.um.cartelera.flow;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cartelera.um.cartelera.activities.LogInActivity;
import cartelera.um.cartelera.activities.MovieActivity;
import cartelera.um.cartelera.activities.NavigationActivity;

/**
 * Created by Christian on 15/01/2019.
 */

public class FlowManagerImpl implements FlowManager{

    public FlowManagerImpl() {
    }

    public Intent getIntent(Context context, int flowId) {
        Intent intent = null;
        switch (flowId) {
            case NAVIGATION_ACTIVITY:
                intent = createNavigationIntent(context);
                break;
            case LOG_IN_ACTIVITY:
                intent = createLogInActivity(context);
                break;
            case MOVIE_ACTIVITY:
                intent = createMovieActivity(context);
                break;
            case CHANGE_PASSWORD:
                intent = createPasswordActivity(context);
                break;
            case NOTIFICATION_ACTIVITY:
                intent = createNotificationActivity(context);
                break;
            default:
                Log.e("FlowManager"," flow not found, id ");
                Log.e("FlowManager", "ERROR GETTING INTENT");
                break;
        }
        if (intent == null) {
            // intent = ....
        }
        return intent;
    }

    public Intent getIntentResetFlags(Context context, int flowId) {
        Intent intent = getIntent(context, flowId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    // Intent implementation

    private Intent createNavigationIntent(Context context) {
        return new Intent(context, NavigationActivity.class);
    }

    private Intent createMovieActivity(Context context) {
        return new Intent(context, MovieActivity.class);
    }

    private Intent createLogInActivity(Context context) {
        return new Intent(context, LogInActivity.class);
    }

    private Intent createPasswordActivity(Context context) {
        // return new Intent(context, SignInActivity.class);
        return new Intent();
    }


    private Intent createNotificationActivity(Context context) {
        // return new Intent(context, SignInActivity.class);
        return new Intent();
    }
}