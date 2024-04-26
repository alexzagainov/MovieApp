package com.projectapps.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.projectapps.movieapp.adapters.MainMovieListAdapter;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;
import com.projectapps.movieapp.views.CategoryMoviesList;
import com.projectapps.movieapp.views.MovieDetails;
import com.projectapps.movieapp.views.TvShow;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
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

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainActivity();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




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
            if(!searchBar.getText().toString().isEmpty()) {
                Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
                i.putExtra("type", "Search");
                i.putExtra("search", searchBar.getText().toString());
                i.putExtra("category_name","Search results");
                startActivity(i);
            }else{
                Toast.makeText(this, "No text entered", Toast.LENGTH_SHORT).show();
            }
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
        i.putExtra("category_name",category);
        startActivity(i);
    }

    private void moveToGenreList(int genre_id,String genre_name){
        Intent i = new Intent(getApplicationContext(), CategoryMoviesList.class);
        i.putExtra("genre_id", "" + genre_id);
        i.putExtra("category_name",genre_name);
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
                mainAdapter.setOnItemClickListener(new MainMovieListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                        i.putExtra("ImagePath", movieList.get(position).getPoster_path());
                        i.putExtra("MovieName",movieList.get(position).getTitle());
                        i.putExtra("MovieRating",Double.toString(movieList.get(position).getVote_avarage()));
                        i.putExtra("MovieOverview",movieList.get(position).getMovie_overview());
                        i.putExtra("Movie_id",movieList.get(position).getMovie_id());
                        startActivity(i);
                    }
                });
                recyclerView.setAdapter(mainAdapter);
            }
        });

    }

    private void setMainActivity(){
        setContentView(R.layout.activity_main);

        welcomImage = findViewById(R.id.main_welcome_image);
        welcomImage.setImageResource(R.drawable.mainmoviephoto1);


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

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation drawer item clicks here
        int itemId = item.getItemId();

        if (itemId == R.id.nav_action) {
            moveToGenreList(28,"Action");
        } else if (itemId == R.id.nav_comedy) {
            moveToGenreList(35,"Comedy");
        } else if (itemId == R.id.nav_adventure) {
            moveToGenreList(12,"Adventure");
        } else if (itemId == R.id.nav_tv_shows) {
            Intent i = new Intent(getApplicationContext(), TvShow.class);
            i.putExtra("category_name","Tv Shows");
            i.putExtra("type","Tv Shows");
            startActivity(i);
        }

        // Close the drawer after handling item click
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}