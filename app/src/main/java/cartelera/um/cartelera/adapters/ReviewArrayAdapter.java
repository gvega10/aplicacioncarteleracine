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
import cartelera.um.cartelera.entities.Review;
import cartelera.um.cartelera.listeners.AdapterClickListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Christian on 27/01/2019.
 */

public class ReviewArrayAdapter  extends RecyclerView.Adapter<ReviewArrayAdapter.CustomViewHolder> {
    private Context mContext;
    private List<Review> reviewsDataSet;

    public ReviewArrayAdapter(Context context, List<Review> dataSet) {
        this.mContext = context;
        this.reviewsDataSet = dataSet;
    }

    @Override
    public ReviewArrayAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_item_layout, null);
        final ReviewArrayAdapter.CustomViewHolder viewHolder = new ReviewArrayAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return (null != reviewsDataSet ? reviewsDataSet.size() : 0);
    }

    @Override
    public void onBindViewHolder(final ReviewArrayAdapter.CustomViewHolder customViewHolder, int i) {
        Review currentMovie = reviewsDataSet.get(i);

        String thumbImage = currentMovie.getUser().getThumbImg();
        if(!TextUtils.isEmpty(thumbImage)){
            Picasso.with(mContext).load(thumbImage).into(customViewHolder.userImage);
        }

        Double score = 0d;
        if(currentMovie.getScore() != 0 ){
            score = Double.valueOf(currentMovie.getScore());
        }

        String scoreString = String.valueOf(score);
        customViewHolder.ratingBar.setRating(Float.valueOf(scoreString));
        customViewHolder.userName.setText(currentMovie.getUser().getName());
        customViewHolder.reviewText.setText(currentMovie.getMessage());
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView userImage;
        private RatingBar ratingBar;
        private TextView userName, reviewText;

        private CustomViewHolder(View view) {
            super(view);
            this.userImage = view.findViewById(R.id.user_profile_image);
            this.userName = view.findViewById(R.id.user_name);
            this.reviewText = view.findViewById(R.id.review);
            this.ratingBar = view.findViewById(R.id.rating_bar);
        }
    }

}
