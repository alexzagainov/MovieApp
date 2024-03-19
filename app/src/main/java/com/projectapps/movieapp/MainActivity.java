package com.projectapps.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;
import com.projectapps.movieapp.views.CategoryMoviesList;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ImageView welcomImage;
    Button popularBTN;
    Button upcomingBTN;
    Button topRatedBTN;
    Button nowPlayingBTN;
    Button searchBTN;

    EditText searchBar;

    RecyclerView popularRecycleView;
    RecyclerView topRatedRecyclerView;
    RecyclerView nowPlayingRecyclerView;
    RecyclerView upcomingRecyclerView;



    ArrayList<MovieModel> searchMovies;
    ArrayList<MovieModel> popularMovies;
    ArrayList<MovieModel> upcomingMovies;
    ArrayList<MovieModel> topRatedMovies;
    ArrayList<MovieModel> nowPlayingMovies;

    MainMovieListAdapter mainAdapter;


    /*MovieAdapter popularMovieAdapter;
    MovieAdapter upcomingMovieAdapter;
    MovieAdapter topRatedMovieAdapter;
    MovieAdapter nowPlayingMovieAdapter;*/


    MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomImage = findViewById(R.id.main_welcome_image);
        welcomImage.setImageResource(R.drawable.movie_app);


        popularBTN = findViewById(R.id.popularMoviesButton);
        upcomingBTN =findViewById(R.id.upcomingMoviesBTN);
        topRatedBTN =findViewById(R.id.topRatedMoviesBTN);
        nowPlayingBTN =findViewById(R.id.nowPlayingMoviesBTN);
        searchBTN = findViewById(R.id.searchBTN);

        searchBar = findViewById(R.id.searchBar);

        popularRecycleView = findViewById(R.id.popularRecyclerView);
        upcomingRecyclerView = findViewById(R.id.upcomingRecyclerView);
        topRatedRecyclerView = findViewById(R.id.topRatedRecyclerView);
        nowPlayingRecyclerView = findViewById(R.id.nowPlayingRecyclerView);
        movieListViewModel =new ViewModelProvider(this).get(MovieListViewModel.class);

         searchMovies = new ArrayList<>();
         popularMovies = new ArrayList<>();
         upcomingMovies = new ArrayList<>();
         topRatedMovies = new ArrayList<>();
         nowPlayingMovies = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

        popularRecycleView.setLayoutManager(layoutManager1);
        nowPlayingRecyclerView.setLayoutManager(layoutManager2);
        upcomingRecyclerView.setLayoutManager(layoutManager3);
        topRatedRecyclerView.setLayoutManager(layoutManager4);

        ObserveAnyChange();

        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
                i.putExtra("type","Search");
                i.putExtra("search",searchBar.getText().toString());
                startActivity(i);
            }
        });

        popularBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCategoryView("Popular");
            }
        });

        upcomingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCategoryView("Upcoming");
            }
        });

        topRatedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCategoryView("TopRated");
            }
        });

        nowPlayingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCategoryView("NowPlaying");
            }
        });

    }


    private void ObserveAnyChange(){
        movieListViewModel.getPopular().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                for(MovieModel movie:movieModels){
                    popularMovies.add(movie);
                }
                mainAdapter = new MainMovieListAdapter(getApplicationContext(),popularMovies);
                popularRecycleView.setAdapter(mainAdapter);

            }
        });

        movieListViewModel.getUpcoming().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                for(MovieModel movie:movieModels){
                    upcomingMovies.add(movie);
                }
                mainAdapter = new MainMovieListAdapter(getApplicationContext(),upcomingMovies);
                upcomingRecyclerView.setAdapter(mainAdapter);
            }
        });
        movieListViewModel.getNowPlaying().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                for(MovieModel movie:movieModels){
                    nowPlayingMovies.add(movie);
                }
                mainAdapter = new MainMovieListAdapter(getApplicationContext(),nowPlayingMovies);
                nowPlayingRecyclerView.setAdapter(mainAdapter);
            }
        });
        movieListViewModel.getTopRated().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                for(MovieModel movie:movieModels){
                    topRatedMovies.add(movie);
                }
                mainAdapter = new MainMovieListAdapter(getApplicationContext(),topRatedMovies);
                topRatedRecyclerView.setAdapter(mainAdapter);
            }
        });


    }

    void getMovieListTo(ArrayList<MovieModel> movieList){

    }

    void moveToCategoryView(String category){
        Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
        i.putExtra("type",category);
        startActivity(i);
    }


}