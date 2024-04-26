package com.projectapps.movieapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projectapps.movieapp.MainActivity;
import com.projectapps.movieapp.adapters.CategoryListAdapter;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.models.TvShowModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryMoviesList extends AppCompatActivity {
    ArrayList<MovieModel> movies;
    ArrayList<MovieModel> tvShows;

    ArrayList<MovieModel> genreMovieList;
    RecyclerView recyclerView;
    TextView category;
    RecyclerView pagesRecyclerView;

    CategoryListAdapter adapter;

    MovieListViewModel viewModel;

    Button backBtn;

    String type;
    String genre_id;
    String category_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initiateView();


        if (type != null && !type.equals("Search")) {
            DisplayMovies(viewModel.getMovies(type, "", 1), movies, recyclerView);//displaying movies of the chosen category
        } else {
            if (genre_id != null) {//searching movies by genre
                genreMovieList = new ArrayList<>();//clearing the list
                DisplayMovies(viewModel.discoverMovies(genre_id), genreMovieList, recyclerView);//displaying all movies of the genre
            } else {
                //User searching for a movie
                DisplayMovies(viewModel.getMovies(type, getIntent().getStringExtra("search"), 1), movies, recyclerView);
            }
        }
    }







    private void DisplayMovies(MutableLiveData<List<MovieModel>> mutableMovielist, ArrayList<MovieModel> movieList, RecyclerView recyclerView) {
        mutableMovielist.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                movieList.clear();
                for (MovieModel movie : movieModels) {
                    movieList.add(movie);
                }
                adapter = new CategoryListAdapter(getApplicationContext(), movieList);
                adapter.setOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        moveToMovieDetails(movieList, position);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private void initiateView() {
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        category_name = getIntent().getStringExtra("category_name");
        genre_id = getIntent().getStringExtra("genre_id");
        setContentView(R.layout.activity_category_movies);
        type = getIntent().getStringExtra("type");
        category = findViewById(R.id.categoryTitle);

        category.setText(category_name);

        tvShows = new ArrayList<>();


        movies = new ArrayList<>();
        recyclerView = findViewById(R.id.popularRecyclerViewIn);
        backBtn = findViewById(R.id.backButton);


        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void moveToMovieDetails(ArrayList<MovieModel> moviesList, int position) {
        Intent i = new Intent(getApplicationContext(), MovieDetails.class);
        i.putExtra("ImagePath", moviesList.get(position).getPoster_path());
        i.putExtra("MovieName", moviesList.get(position).getTitle());
        i.putExtra("Movie_id", moviesList.get(position).getMovie_id());
        i.putExtra("MovieRating", Double.toString(moviesList.get(position).getVote_avarage()));
        i.putExtra("MovieOverview", moviesList.get(position).getMovie_overview());
        startActivity(i);
    }
}


