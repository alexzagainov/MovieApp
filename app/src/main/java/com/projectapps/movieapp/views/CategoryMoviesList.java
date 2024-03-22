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
import android.widget.ImageButton;
import android.widget.TextView;

import com.projectapps.movieapp.MainActivity;
import com.projectapps.movieapp.CategoryListAdapter;
import com.projectapps.movieapp.MainMovieListAdapter;
import com.projectapps.movieapp.R;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_movies);
        type = getIntent().getStringExtra("type");
        category =findViewById(R.id.categoryTitle);

        category.setText(type);


        movies = new ArrayList<>();
        recyclerView = findViewById(R.id.popularRecyclerViewIn);
        backBtn = findViewById(R.id.backButton);

        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        if(!type.equals("Search")) {
            DisplayMovies(viewModel.getMovies(type, "", 1), movies, recyclerView);
        }else{
            DisplayMovies(viewModel.getMovies(type, getIntent().getStringExtra("search"), 1), movies, recyclerView);
        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


    }


    private void DisplayMovies(MutableLiveData<List<MovieModel>> mutableMovielist, ArrayList<MovieModel> movieList, RecyclerView recyclerView){
        mutableMovielist.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                for(MovieModel movie:movieModels){
                    movieList.add(movie);
                }
                adapter = new CategoryListAdapter(getApplicationContext(),movieList);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}


