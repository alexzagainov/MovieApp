package com.projectapps.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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





    MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setMainActivity();

        searchBTN.setOnClickListener(this);
        popularBTN.setOnClickListener(this);
        upcomingBTN.setOnClickListener(this);
        topRatedBTN.setOnClickListener(this);
        nowPlayingBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        if(buttonId == R.id.popularMoviesButton){
            moveToCategoryView("Popular");
        }
        if(buttonId == R.id.upcomingMoviesBTN){
            moveToCategoryView("Upcoming");
        }
        if(buttonId == R.id.topRatedMoviesBTN){
            moveToCategoryView("TopRated");
        }
        if(buttonId == R.id.nowPlayingMoviesBTN){
            moveToCategoryView("NowPlaying");
        }
        if(buttonId == R.id.searchBTN){
            Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
            i.putExtra("type","Search");
            i.putExtra("search",searchBar.getText().toString());
            startActivity(i);
        }
    }

    private void ObserveAnyChange() {

        DisplayMovies(movieListViewModel.getMovies("Popular", "", 1), popularMovies, popularRecycleView);
        DisplayMovies(movieListViewModel.getMovies("Upcoming", "", 1), upcomingMovies, upcomingRecyclerView);
        DisplayMovies(movieListViewModel.getMovies("TopRated", "", 1), topRatedMovies, topRatedRecyclerView);
        DisplayMovies(movieListViewModel.getMovies("NowPlaying", "", 1), nowPlayingMovies, nowPlayingRecyclerView);
    }




    private void moveToCategoryView(String category){
        Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
        i.putExtra("type",category);
        startActivity(i);
    }

    private void DisplayMovies(MutableLiveData<List<MovieModel>> mutableMovielist, ArrayList<MovieModel> movieList, RecyclerView recyclerView){
        mutableMovielist.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                for(MovieModel movie:movieModels){
                    movieList.add(movie);
                }
                mainAdapter = new MainMovieListAdapter(getApplicationContext(),movieList);
                recyclerView.setAdapter(mainAdapter);
            }
        });

    }

    private void setMainActivity(){
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
    }





}