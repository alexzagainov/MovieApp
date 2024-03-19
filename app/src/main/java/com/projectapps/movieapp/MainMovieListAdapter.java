package com.projectapps.movieapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.models.MovieModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainMovieListAdapter extends RecyclerView.Adapter<MainMovieListAdapter.MainViewHolder> {

    Context context;
    ArrayList<MovieModel> movieList;

    public MainMovieListAdapter(Context context, ArrayList<MovieModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_movie_list_item
                        ,parent
                        ,false);

        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        MovieModel movie = movieList.get(position);
        loadImage(holder.movieImage,movie.getPoster_path());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_main_Image);
        }
    }


    private static void loadImage(ImageView imageView, String imageUrl){
        // Basic Url: "https://image.tmdb.org/t/p/w500"
        String imagePath = "https://image.tmdb.org/t/p/w500"+imageUrl;

        Glide.with(imageView.getContext())
                .load(imagePath)
                .into(imageView);
    }

}
