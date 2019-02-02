package cartelera.um.cartelera.activities;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.adapters.ReviewArrayAdapter;
import cartelera.um.cartelera.auth.AuthenticationManagerFactory;
import cartelera.um.cartelera.dialogs.NeedLogInDialog;
import cartelera.um.cartelera.dialogs.ReviewDialog;
import cartelera.um.cartelera.entities.Movie;
import cartelera.um.cartelera.entities.Review;
import cartelera.um.cartelera.entities.User;
import cartelera.um.cartelera.listeners.AddReviewListener;
import cartelera.um.cartelera.services.ServiceLocator;
import cartelera.um.cartelera.services.ServiceLocatorFactory;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieActivity extends AppCompatActivity implements AddReviewListener {
    public static final String MOVIE_INTENT = "movie_intents";
    private RecyclerView reviewList;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager recyclerLayoutManager;
    private List<Review> reviewsDataSet = new ArrayList<>();
    private ImageView movieImage;
    private Toolbar toolbar;
    private TextView score, title, sinopsis, genre, duration, addReview;
    private RatingBar ratingBar;
    private ServiceLocator sl;
    private Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        sl = ServiceLocatorFactory.getInstance(MovieActivity.this);
        setUpView();
        Resources res = MovieActivity.this.getResources();
        currentMovie = getIntent().getExtras().getParcelable(MOVIE_INTENT);
        setToolbar(currentMovie.getName());
        title.setText(currentMovie.getName());
        sinopsis.setText(currentMovie.getSynopsis());

        Double scoreValue = 0d;
        if(currentMovie.getScore() != 0 ){
            scoreValue = Double.valueOf(currentMovie.getScore());
        }

        if(currentMovie.isPremiere()){
           // premiereContainer.setVisibility(View.VISIBLE);
        }

        String scoreString = String.valueOf(scoreValue);
        score.setText(scoreString);
        ratingBar.setRating(Float.valueOf(scoreString));
        genre.setText(res.getString(R.string.movie_genre, currentMovie.getGenre().getName()));
        duration.setText(res.getString(R.string.movie_duration, String.valueOf(currentMovie.getDuration())));

        if(!TextUtils.isEmpty(currentMovie.getImg())){
            Picasso.with(MovieActivity.this).load(currentMovie.getImg()).into(movieImage);
        }

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userLoged = AuthenticationManagerFactory.getIntance(MovieActivity.this).getCurrentUser();
                if(userLoged != null){
                    new ReviewDialog().show(getSupportFragmentManager(), "AddReviewDialog");
                }else {
                    FragmentManager fm = getSupportFragmentManager();
                    NeedLogInDialog needLogInDialog = NeedLogInDialog.newInstance();
                    needLogInDialog.show(fm, "NewProductDialog");
                }
            }
        });


        getServicelocator().getMovieService()
                .getMovieReviews(currentMovie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(getServicelocator().getSchedulers())
                .subscribe(new DisposableObserver<List<Review>>() {
                    @Override
                    public void onNext(List<Review> reviews) {
                        reviewsDataSet = reviews;
                        setUpReviewList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MovieActivity.this, "Error al cargar reviews", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public ServiceLocator getServicelocator(){
        if(sl == null){
            return ServiceLocatorFactory.getInstance(getApplicationContext());
        }

        return sl;
    }

    private void setUpView(){
        addReview = findViewById(R.id.add_review);
        movieImage = findViewById(R.id.movie_image);
        title =  findViewById(R.id.movie_title);
        genre =  findViewById(R.id.movie_genre);
        score =  findViewById(R.id.movie_score);
        duration = findViewById(R.id.movie_duration);
        sinopsis =  findViewById(R.id.movie_sinopsis);
        ratingBar = findViewById(R.id.rating_bar);
        reviewList = findViewById(R.id.review_list);
    }

    private void setUpReviewList(){
        recyclerLayoutManager = new LinearLayoutManager(MovieActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(200);
        animator.setRemoveDuration(200);
        reviewList.setItemAnimator(animator);
        reviewList.setLayoutManager(recyclerLayoutManager);
        mAdapter = new ReviewArrayAdapter(MovieActivity.this, reviewsDataSet);
        reviewList.setAdapter(mAdapter);
    }

    private void setToolbar(String toolbarTitle) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(toolbarTitle);
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

    @Override
    public void addReview(Review review) {

        reviewsDataSet.add(review);
        mAdapter.notifyDataSetChanged();
        /*getServicelocator().getMovieService()
                .addReview(currentMovie.getId(),review.getMessage(), review.getScore())
                .subscribeOn(Schedulers.io())
                .observeOn(getServicelocator().getSchedulers())
                .subscribe(new DisposableObserver<Review>() {
                    @Override
                    public void onNext(Review review) {
                        reviewsDataSet.add(review);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MovieActivity.this, "Error al cargar review", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });*/
    }

    @Override
    public void editReview(Review review) {

    }
}
