package com.projectapps.movieapp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.R;

public class ImageLoader {
    public ImageLoader(){}

    public static void loadImage(ImageView imageView, String imageUrl) {
        // Basic Url: "https://image.tmdb.org/t/p/w500"
        if (imageUrl == null) {
            imageView.setImageResource(R.drawable.no_image_available);
        } else {
            String imagePath = "https://image.tmdb.org/t/p/w500" + imageUrl;

            Glide.with(imageView.getContext())
                    .load(imagePath)
                    .into(imageView);
        }
    }
}
