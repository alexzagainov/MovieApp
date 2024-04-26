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
import com.projectapps.movieapp.models.ShowImageModel;
import com.projectapps.movieapp.utils.ImageLoader;

import java.util.List;

public class ShowImagesAdapter extends RecyclerView.Adapter<ShowImagesAdapter.ShowImagesHolder> {
    List<ShowImageModel> images;
    Context context;

    private ImageLoader imageLoader = new ImageLoader();

    public ShowImagesAdapter(List<ShowImageModel> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.show_image_item,
                        parent,
                        false);
        return new ShowImagesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowImagesHolder holder, int position) {
        ShowImageModel image = images.get(position);
        imageLoader.loadImage(holder.imageView,image.getImage_url());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ShowImagesHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ShowImagesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.show_image);

        }
    }
}
