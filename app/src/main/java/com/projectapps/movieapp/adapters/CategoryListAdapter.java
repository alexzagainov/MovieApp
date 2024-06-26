package com.projectapps.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.utils.ImageLoader;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.PopularListHolder> {
    Context context;
    ArrayList<MovieModel> movieList;
    String movieFullDate;
    String movieName;
    private OnItemClickListener mListener;

    private ImageLoader imageLoader = new ImageLoader();

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

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

    public class PopularListHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        ImageView movieImage;

        public PopularListHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }

}
