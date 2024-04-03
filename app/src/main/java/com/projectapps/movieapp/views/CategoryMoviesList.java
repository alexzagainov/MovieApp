package com.projectapps.movieapp.views;

import androidx.annotation.NonNull;
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
import com.projectapps.movieapp.CategoryListAdapter;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.GenreModel;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryMoviesList extends AppCompatActivity {
    ArrayList<MovieModel> movies;
    RecyclerView recyclerView;
    TextView category;
    RecyclerView pagesRecyclerView;

    CategoryListAdapter adapter;

    MovieListViewModel viewModel;

    Button backBtn;

    String type;
    String genre_id;

    ArrayList<MovieModel> genreMovieList;
    int movies_page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        genre_id = getIntent().getStringExtra("genre_id");
        /*if(genre_id!=null || !genre_id.equals("")){
           genreMovieList = new ArrayList<>();
            viewModel.discoverMovies(genre_id).observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    for(MovieModel movie:movieModels){
                        genreMovieList.add(movie);
                    }
                    adapter = new CategoryListAdapter(getApplicationContext(),genreMovieList);
                    adapter.setOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                            i.putExtra("ImagePath", genreMovieList.get(position).getPoster_path());
                            i.putExtra("MovieName",genreMovieList.get(position).getTitle());
                            i.putExtra("MovieRating",Double.toString(genreMovieList.get(position).getVote_avarage()));
                            i.putExtra("MovieOverview",genreMovieList.get(position).getMovie_overview());
                            startActivity(i);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
            });
            }

         */
        setContentView(R.layout.activity_category_movies);
        type = getIntent().getStringExtra("type");
        category = findViewById(R.id.categoryTitle);

        category.setText(type);


        movies = new ArrayList<>();
        recyclerView = findViewById(R.id.popularRecyclerViewIn);
        backBtn = findViewById(R.id.backButton);


        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        if (type != null && !type.equals("Search")) {
            DisplayMovies(viewModel.getMovies(type, "", 1), movies, recyclerView);
        } else {
            if (genre_id != null) {
                genreMovieList = new ArrayList<>();
                viewModel.discoverMovies(genre_id).observe(this, new Observer<List<MovieModel>>() {
                    @Override
                    public void onChanged(List<MovieModel> movieModels) {
                        for (MovieModel movie : movieModels) {
                            genreMovieList.add(movie);
                        }
                        adapter = new CategoryListAdapter(getApplicationContext(), genreMovieList);
                        adapter.setOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                                i.putExtra("ImagePath", genreMovieList.get(position).getPoster_path());
                                i.putExtra("MovieName", genreMovieList.get(position).getTitle());
                                i.putExtra("MovieRating", Double.toString(genreMovieList.get(position).getVote_avarage()));
                                i.putExtra("MovieOverview", genreMovieList.get(position).getMovie_overview());
                                startActivity(i);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }
                });
            }else{
            DisplayMovies(viewModel.getMovies(type, getIntent().getStringExtra("search"), 1), movies, recyclerView);
            }
        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
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
                        Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                        i.putExtra("ImagePath", movieList.get(position).getPoster_path());
                        i.putExtra("MovieName", movieList.get(position).getTitle());
                        i.putExtra("MovieRating", Double.toString(movieList.get(position).getVote_avarage()));
                        i.putExtra("MovieOverview", movieList.get(position).getMovie_overview());
                        startActivity(i);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

    }
}


