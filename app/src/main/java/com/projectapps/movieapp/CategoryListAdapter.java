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

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.PopularListHolder> {
    Context context;
    ArrayList<MovieModel> movieList;
    String movieFullDate;
    String movieName;

    public CategoryListAdapter(Context context, ArrayList<MovieModel> movieList) {
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
        movieName = movie.getTitle();
        movieFullDate = movie.getRelease_date();
        if(movieName.length() >= 13){
            movieName = movieName.substring(0,10)+"...";
        }
        if(!movieFullDate.isEmpty()){
            if(movieFullDate.length()>4){
                movieFullDate = movieFullDate.substring(0,4);
            }
        }


        holder.movieName.setText(movieName+"\n("+ movieFullDate+")");

        loadImage(holder.movieImage, movie.getPoster_path());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class PopularListHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        ImageView movieImage;

        public PopularListHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }


    private static void loadImage(ImageView imageView, String imageUrl){
        // Basic Url: "https://image.tmdb.org/t/p/w500"
        if(imageUrl== null){
            imageView.setImageResource(R.drawable.no_image_available);
        }else {
            String imagePath = "https://image.tmdb.org/t/p/w500" + imageUrl;

            Glide.with(imageView.getContext())
                    .load(imagePath)
                    .into(imageView);
        }

    }
}
