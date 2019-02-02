package cartelera.um.cartelera.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cartelera.um.cartelera.entities.Genre;
import cartelera.um.cartelera.R;
import cartelera.um.cartelera.listeners.AdapterClickListener;
import cartelera.um.cartelera.picasso.RoundedCornersTransform;
import jp.wasabeef.picasso.transformations.BlurTransformation;

/**
 * Created by Christian on 12/01/2019.
 */

public class GenreArrayAdapter extends RecyclerView.Adapter<GenreArrayAdapter.CustomViewHolder> {
    private Context mContext;
    private List<Genre> genreDataSet;
    private AdapterClickListener adapterClickListener;

    public GenreArrayAdapter(Context context, List<Genre> dataSet, final AdapterClickListener adapterClickListener) {
        this.mContext = context;
        this.genreDataSet = dataSet;
        this.adapterClickListener = adapterClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genre_item_layout, null);
        final CustomViewHolder viewHolder = new CustomViewHolder(view);
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
        return (null != genreDataSet ? genreDataSet.size() : 0);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int i) {
        Genre movieGenre = genreDataSet.get(i);

        final String movieGenreThumbImg = movieGenre.getThumb_img();
        final String movieGenreImg = movieGenre.getImg();

        if(!TextUtils.isEmpty(movieGenreThumbImg) && !TextUtils.isEmpty(movieGenreImg)){
            Picasso.with(mContext).load(movieGenreImg)
                    .transform(new BlurTransformation(mContext))
                    .transform(new RoundedCornersTransform(30, 0))
                    .into(customViewHolder.backgroundImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(mContext)
                                    .load(movieGenreThumbImg)
                                    .into(customViewHolder.genreImage);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }

        customViewHolder.name.setText(movieGenre.getName());
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView genreImage, backgroundImage;
        private TextView name;

        private CustomViewHolder(View view) {
            super(view);
            this.backgroundImage = view.findViewById(R.id.image_background);
            this.genreImage = view.findViewById(R.id.genre_movie_image);
            this.name = view.findViewById(R.id.genre_name);
        }
    }

}
