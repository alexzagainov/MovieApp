package com.projectapps.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.models.MovieModel;

import java.util.ArrayList;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.PopularListHolder> {
    Context context;
    ArrayList<MovieModel> movieList;

    public PopularListAdapter(Context context, ArrayList<MovieModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public PopularListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.movie_list_item,
                        parent,
                        false);
        return new PopularListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularListHolder holder, int position) {
        MovieModel movie = movieList.get(position);
        holder.movieName.setText(movie.getTitle());
        holder.movieReleaeDate.setText(movie.getRelease_date());
        loadImage(holder.movieImage, movie.getPoster_path());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class PopularListHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        TextView movieReleaeDate;
        ImageView movieImage;

        public PopularListHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieReleaeDate = itemView.findViewById(R.id.movieReleaseDate);
            movieImage = itemView.findViewById(R.id.movieImage);
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
