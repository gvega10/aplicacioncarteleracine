package cartelera.um.cartelera.activities;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.flow.FlowManager;
import cartelera.um.cartelera.services.ServiceLocator;
import cartelera.um.cartelera.services.ServiceLocatorFactory;

public class SplashScreenActivity extends AppCompatActivity {
    private LottieAnimationView lottieAnimation;
    private ServiceLocator sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimation = findViewById(R.id.lottie_animation);
        sl = getServicelocator();
        lottieAnimation.playAnimation();
        lottieAnimation.setRepeatMode(1);
        lottieAnimation.setRepeatCount(2);
        lottieAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent i = sl.getFlowManager().getIntentResetFlags(SplashScreenActivity.this, FlowManager.NAVIGATION_ACTIVITY);
                startActivity(i);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public ServiceLocator getServicelocator(){
        if(sl == null){
            return ServiceLocatorFactory.getInstance(getApplicationContext());
        }

        return sl;
    }
}
