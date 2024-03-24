package com.projectapps.movieapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.MainActivity;
import com.projectapps.movieapp.R;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage;
    TextView movieName, movieRating, movieOverview;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieImage = findViewById(R.id.detailMovieImage);
        movieName = findViewById(R.id.detailMovieName);
        movieRating = findViewById(R.id.detailMovieRating);
        movieOverview = findViewById(R.id.detailMovieOverview);
        backButton = findViewById(R.id.backButton);

        Intent i = getIntent();
        loadImage(movieImage, i.getStringExtra("ImagePath"));
        movieName.setText(i.getStringExtra("MovieName"));
        movieRating.setText(i.getStringExtra("MovieRating").substring(0,3));
        movieOverview.setText(i.getStringExtra("MovieOverview"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


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