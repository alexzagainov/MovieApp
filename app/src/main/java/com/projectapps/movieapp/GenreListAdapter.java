package com.projectapps.movieapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectapps.movieapp.models.GenreModel;

import java.util.ArrayList;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.GenreHolder>{
    ArrayList<GenreModel> genres;
    Context context;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private CategoryListAdapter.OnItemClickListener mListener;


    public void setOnItemClickListener(CategoryListAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public GenreListAdapter(Context context, ArrayList<GenreModel> genres) {
        this.genres = genres;
        this.context = context;
    }

    public class GenreHolder extends RecyclerView.ViewHolder{
        TextView genreName;
        public GenreHolder(@NonNull View itemView) {
            super(itemView);
            genreName = itemView.findViewById(R.id.genreName);
        }
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.genre_list_item,
                        parent,
                        false);


        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        GenreModel genre = genres.get(position);
        holder.genreName.setText(genre.getName());

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
        return genres.size();
    }
}
