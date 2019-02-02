package cartelera.um.cartelera.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.auth.AuthenticationManagerFactory;
import cartelera.um.cartelera.dialogs.NeedLogInDialog;
import cartelera.um.cartelera.entities.User;
import cartelera.um.cartelera.fragments.AccountFragment;
import cartelera.um.cartelera.fragments.MovieFragment;
import cartelera.um.cartelera.services.ServiceLocator;
import cartelera.um.cartelera.services.ServiceLocatorFactory;

public class NavigationActivity extends AppCompatActivity {
    private ServiceLocator sl;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    changeFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    User userLoged = AuthenticationManagerFactory.getIntance(NavigationActivity.this).getCurrentUser();
                    if(userLoged != null){
                        fragment = new AccountFragment();
                        changeFragment(fragment);
                    }else {
                        FragmentManager fm = getSupportFragmentManager();
                        NeedLogInDialog needLogInDialog = NeedLogInDialog.newInstance();
                        needLogInDialog.show(fm, "NewProductDialog");
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        sl = ServiceLocatorFactory.getInstance(NavigationActivity.this);
        changeFragment(new MovieFragment());
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public ServiceLocator getServicelocator(){
        if(sl == null){
            return ServiceLocatorFactory.getInstance(getApplicationContext());
        }

        return sl;
    }
}
