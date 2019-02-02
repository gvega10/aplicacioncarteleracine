package cartelera.um.cartelera.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cartelera.um.cartelera.activities.MovieActivity;
import cartelera.um.cartelera.activities.NavigationActivity;
import cartelera.um.cartelera.R;
import cartelera.um.cartelera.adapters.MovieArrayAdapter;
import cartelera.um.cartelera.entities.Movie;
import cartelera.um.cartelera.flow.FlowManager;
import cartelera.um.cartelera.listeners.AdapterClickListener;
import cartelera.um.cartelera.services.ServiceLocator;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Christian on 13/01/2019.
 */

public class MovieFragment extends Fragment {
    private ServiceLocator sl;
    private RecyclerView movieList;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager recyclerLayoutManager;
    private List<Movie> moviesDataSet = new ArrayList<>();
    private TextView emptyListMessage;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View menuListView = inflater.inflate(R.layout.movies_layout, container, false);
        sl = ((NavigationActivity) getActivity()).getServicelocator();
        Resources res = getResources();
        TextView toolbarTitle = menuListView.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(res.getString(R.string.title_movies));
        setUpView(menuListView);
        showProgressBar();
        sl.getMovieService()
                .getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(sl.getSchedulers())
                .subscribe(new DisposableObserver<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> movies) {
                        moviesDataSet = movies;
                        setUpMovieList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Error al cargar peliculas", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                        if(moviesDataSet.size() == 0){
                            emptyListMessage.setVisibility(View.VISIBLE);
                            movieList.setVisibility(View.GONE);
                        }else{
                            movieList.setVisibility(View.VISIBLE);
                            emptyListMessage.setVisibility(View.GONE);
                        }
                    }
                });

        return menuListView;
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        movieList.setVisibility(View.GONE);
        emptyListMessage.setVisibility(View.GONE);
    }

    private void setUpMovieList(){
        recyclerLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(200);
        animator.setRemoveDuration(200);
        movieList.setItemAnimator(animator);
        movieList.setLayoutManager(recyclerLayoutManager);
        mAdapter = new MovieArrayAdapter(getActivity(), moviesDataSet, new AdapterClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = sl.getFlowManager().getIntent(getActivity(), FlowManager.MOVIE_ACTIVITY);
                i.putExtra(MovieActivity.MOVIE_INTENT, moviesDataSet.get(position));
                startActivity(i);
            }
        });
        movieList.setAdapter(mAdapter);
    }

    private void setUpView(View view){
        emptyListMessage = view.findViewById(R.id.empty_list);
        movieList =  view.findViewById(R.id.movie_list);
        progressBar =  view.findViewById(R.id.progressbar);
    }

}
