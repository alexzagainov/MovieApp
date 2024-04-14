package com.projectapps.movieapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.adapters.ActorsListAdapter;
import com.projectapps.movieapp.adapters.CategoryListAdapter;
import com.projectapps.movieapp.adapters.GenreListAdapter;
import com.projectapps.movieapp.MainActivity;
import com.projectapps.movieapp.adapters.MainMovieListAdapter;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.ActorModel;
import com.projectapps.movieapp.models.GenreModel;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity implements View.OnClickListener {

    ImageView movieImage;
    TextView movieName, movieRating, movieOverview;

    ArrayList<GenreModel> genres;
    ArrayList<ActorModel> actors;
    ArrayList<MovieModel> similarMovies;
    MovieListViewModel viewModel;
    RecyclerView genreRecyclerView;
    RecyclerView actorsRecyclerView;
    RecyclerView similarRecyclerView;

    GenreListAdapter genreAdapter;
    ActorsListAdapter actorsListAdapter;
    MainMovieListAdapter similarMoviesAdapter;

    Button backButton;
    int genre_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        setContentView(R.layout.activity_movie_details);

        movieImage = findViewById(R.id.detailMovieImage);
        movieName = findViewById(R.id.detailMovieName);
        movieRating = findViewById(R.id.detailMovieRating);
        movieOverview = findViewById(R.id.detailMovieOverview);
        backButton = findViewById(R.id.backButton);

        genreRecyclerView = findViewById(R.id.genreRecyclerView);
        actorsRecyclerView = findViewById(R.id.actorsRecycleView);
        similarRecyclerView = findViewById(R.id.similarMoviesRecyclerView);
        RecyclerView.LayoutManager genreLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager actorsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager similarMoviesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        genreRecyclerView.setLayoutManager(genreLayoutManager);
        actorsRecyclerView.setLayoutManager(actorsLayoutManager);
        similarRecyclerView.setLayoutManager(similarMoviesLayoutManager);

        Intent i = getIntent();
        int movie_id = i.getIntExtra("Movie_id", 0);

        viewModel.getSimilarMovies(""+movie_id).observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                similarMovies = new ArrayList<>();
                for (MovieModel movie : movieModels) {
                    similarMovies.add(movie);
                    Log.v("Similar Movie", "movie name " + movie.getTitle());
                }
                similarMoviesAdapter = new MainMovieListAdapter(getApplicationContext(),similarMovies);
                similarRecyclerView.setAdapter(similarMoviesAdapter);
                similarMoviesAdapter.setOnItemClickListener(new MainMovieListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                        i.putExtra("ImagePath", similarMovies.get(position).getPoster_path());
                        i.putExtra("MovieName",similarMovies.get(position).getTitle());
                        i.putExtra("MovieRating",Double.toString(similarMovies.get(position).getVote_avarage()));
                        i.putExtra("MovieOverview",similarMovies.get(position).getMovie_overview());
                        i.putExtra("Movie_id",similarMovies.get(position).getMovie_id());
                        startActivity(i);
                    }
                });
            }
        });

        viewModel.getGenres(movie_id).observe(this, new Observer<List<GenreModel>>() {
            @Override
            public void onChanged(List<GenreModel> genreModels) {
                genres = new ArrayList<>();
                for (GenreModel genre : genreModels) {
                    genres.add(genre);
                    Log.v("GENRE", "genre name " + genre.getName());
                }
                genreAdapter = new GenreListAdapter(getApplicationContext(), genres);
                genreAdapter.setOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        genre_id = genres.get(position).getId();
                        Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
                        i.putExtra("genre_id", "" + genre_id);
                        startActivity(i);
                    }
                });
                genreRecyclerView.setAdapter(genreAdapter);

            }
        });

        viewModel.getActors("" + movie_id).observe(this, new Observer<List<ActorModel>>() {
            @Override
            public void onChanged(List<ActorModel> actorModels) {
                actors = new ArrayList<>();
                for (ActorModel actor : actorModels) {
                    actors.add(actor);
                }
                actorsListAdapter = new ActorsListAdapter(getApplicationContext(), actors);
                actorsRecyclerView.setAdapter(actorsListAdapter);
            }
        });


        loadImage(movieImage, i.getStringExtra("ImagePath"));
        movieName.setText(i.getStringExtra("MovieName"));
        movieRating.setText(i.getStringExtra("MovieRating").substring(0, 3));
        movieOverview.setText(i.getStringExtra("MovieOverview"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private static void loadImage(ImageView imageView, String imageUrl) {
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

    @Override
    public void onClick(View v) {

    }
}