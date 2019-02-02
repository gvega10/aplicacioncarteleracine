package cartelera.um.cartelera.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import javax.security.auth.login.LoginException;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.auth.AuthenticationManager;
import cartelera.um.cartelera.auth.AuthenticationManagerFactory;
import cartelera.um.cartelera.entities.Review;
import cartelera.um.cartelera.entities.User;
import cartelera.um.cartelera.services.ServiceLocator;
import cartelera.um.cartelera.services.ServiceLocatorFactory;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LogInActivity extends AppCompatActivity {
    private EditText passwordEditText;
    private AppCompatAutoCompleteTextView emailTextView;
    private Button logInButton;
    private ServiceLocator sl;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setToolbar();
        setUpView();
        sl = getServicelocator();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextView.getText().toString();
                String password = passwordEditText.getText().toString();

                sl.getAuthService()
                        .signIn(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(sl.getSchedulers())
                        .subscribe(new DisposableObserver<User>() {
                            @Override
                            public void onNext(User userSigned) {
                                AuthenticationManager Auth = AuthenticationManagerFactory.getIntance(LogInActivity.this);
                                Auth.setCurrentUser(userSigned);
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(LogInActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });

            }
        });
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.arrow_left);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpView(){
        passwordEditText = findViewById(R.id.password_input);
        emailTextView = findViewById(R.id.email_input);
        logInButton = findViewById(R.id.log_in_button);
    }

    public ServiceLocator getServicelocator(){
        if(sl == null){
            return ServiceLocatorFactory.getInstance(getApplicationContext());
        }

        return sl;
    }

}
