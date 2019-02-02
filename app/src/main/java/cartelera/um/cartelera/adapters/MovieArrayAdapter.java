package cartelera.um.cartelera.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.entities.Movie;
import cartelera.um.cartelera.listeners.AdapterClickListener;

/**
 * Created by Christian on 17/01/2019.
 */

public class MovieArrayAdapter extends RecyclerView.Adapter<MovieArrayAdapter.CustomViewHolder> {
    private Context mContext;
    private List<Movie> moviesDataSet;
    private AdapterClickListener adapterClickListener;

    public MovieArrayAdapter(Context context, List<Movie> dataSet, final AdapterClickListener adapterClickListener) {
        this.mContext = context;
        this.moviesDataSet = dataSet;
        this.adapterClickListener = adapterClickListener;
    }

    @Override
    public MovieArrayAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item_layout, null);
        final MovieArrayAdapter.CustomViewHolder viewHolder = new MovieArrayAdapter.CustomViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return (null != moviesDataSet ? moviesDataSet.size() : 0);
    }

    @Override
    public void onBindViewHolder(final MovieArrayAdapter.CustomViewHolder customViewHolder, int i) {
        Movie currentMovie = moviesDataSet.get(i);
        final String movieImg = currentMovie.getThumb_img();
        Resources res = mContext.getResources();

        if(!TextUtils.isEmpty(movieImg)){
            Picasso.with(mContext).load(movieImg)
                    .into(customViewHolder.image, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(mContext)
                                    .load(movieImg)
                                    .into(customViewHolder.image);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }

        if(currentMovie.isPremiere()){
           // customViewHolder.premiereContainer.setVisibility(View.VISIBLE);
        }

        Double score = 0d;
        if(currentMovie.getScore() != 0 ){
            score = Double.valueOf(currentMovie.getScore());
        }

        String scoreString = String.valueOf(score);
        customViewHolder.ratingBar.setRating(Float.valueOf(scoreString));
        customViewHolder.ratingText.setText(scoreString);
        customViewHolder.genre.setText(res.getString(R.string.movie_genre, currentMovie.getGenre().getName()));
        customViewHolder.duration.setText(res.getString(R.string.movie_duration, String.valueOf(currentMovie.getDuration())));
        customViewHolder.name.setText(currentMovie.getName());
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private RelativeLayout premiereContainer;
        private RatingBar ratingBar;
        private TextView name, genre, duration, ratingText;

        private CustomViewHolder(View view) {
            super(view);
            this.image = view.findViewById(R.id.image);
            //this.premiereContainer = view.findViewById(R.id.premiere_container);
            this.name = view.findViewById(R.id.name);
            this.genre = view.findViewById(R.id.movie_genre);
            this.duration = view.findViewById(R.id.movie_duration);
            this.ratingBar = view.findViewById(R.id.rating_bar);
            this.ratingText = view.findViewById(R.id.rating_text);
        }
    }

}
