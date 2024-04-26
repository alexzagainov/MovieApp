package com.projectapps.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.utils.ImageLoader;

import java.util.ArrayList;

public class MainMovieListAdapter extends RecyclerView.Adapter<MainMovieListAdapter.MainViewHolder> {

    private Context context;
    private ArrayList<MovieModel> movieList;
    private OnItemClickListener mListener;

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ImageLoader imageLoader = new ImageLoader();

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MainMovieListAdapter(Context context, ArrayList<MovieModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_movie_list_item, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        MovieModel movie = movieList.get(position);
        imageLoader.loadImage(holder.movieImage, movie.getPoster_path());

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_main_Image);
        }
    }
}
