package com.projectapps.movieapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projectapps.movieapp.MainActivity;
import com.projectapps.movieapp.CategoryListAdapter;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryMoviesList extends AppCompatActivity {
    ArrayList<MovieModel> movies;
    RecyclerView recyclerView;
    RecyclerView pagesRecyclerView;

    CategoryListAdapter adapter;

    MovieListViewModel viewModel;

    Button backBtn;

    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_movies);
        type = getIntent().getStringExtra("type");


        movies = new ArrayList<>();
        recyclerView = findViewById(R.id.popularRecyclerViewIn);
        backBtn = findViewById(R.id.backButton);

        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));



        getMovies(type);



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


    }





    void getMovies(String type){

        if(type.equals("Search")) {
            viewModel.searchMovies(getIntent().getStringExtra("search")).observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    for (MovieModel movie : movieModels) {
                        movies.add(movie);
                    }
                    adapter = new CategoryListAdapter(getApplicationContext(), movies);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
        else if(type.equals("Popular")) {

            viewModel.getPopular().observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    for (MovieModel movie : movieModels) {
                        movies.add(movie);
                    }
                    adapter = new CategoryListAdapter(getApplicationContext(), movies);
                    recyclerView.setAdapter(adapter);

                }
            });
        }

        else if(type.equals("Upcoming")) {
            viewModel.getUpcoming().observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    for(MovieModel movie:movieModels){
                        movies.add(movie);
                    }
                    adapter= new CategoryListAdapter(getApplicationContext(),movies);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
        else if(type.equals("TopRated")) {
            viewModel.getTopRated().observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    for(MovieModel movie:movieModels){
                        movies.add(movie);
                    }
                    adapter = new CategoryListAdapter(getApplicationContext(),movies);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
        else if(type.equals("NowPlaying")) {
            viewModel.getNowPlaying().observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    for(MovieModel movie:movieModels){
                        movies.add(movie);
                    }
                    adapter = new CategoryListAdapter(getApplicationContext(),movies);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }
}


