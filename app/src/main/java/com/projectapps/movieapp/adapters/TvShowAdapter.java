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
import com.projectapps.movieapp.models.TvShowModel;
import com.projectapps.movieapp.utils.ImageLoader;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowHolder>{
    Context context;
    ArrayList<TvShowModel> showList;
    String showName;
    private CategoryListAdapter.OnItemClickListener mListener;

    private ImageLoader imageLoader = new ImageLoader();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(CategoryListAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public TvShowAdapter(Context context, ArrayList<TvShowModel> showList) {
        this.context = context;
        this.showList = showList;
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.movie_list_item,
                        parent,
                        false);
        return new TvShowAdapter.TvShowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowHolder holder, int position) {
        TvShowModel show = showList.get(position);
        showName = show.getTitle();
        if(showName.length() >= 13){
            showName = showName.substring(0,10)+"...";
        }

        holder.movieName.setText(showName);
        imageLoader.loadImage(holder.movieImage, show.getPoster_path());


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
        return showList.size();
    }

    public class TvShowHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        ImageView movieImage;

        public TvShowHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}

