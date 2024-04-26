package com.projectapps.movieapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projectapps.movieapp.R;
import com.projectapps.movieapp.adapters.CategoryListAdapter;
import com.projectapps.movieapp.adapters.TvShowAdapter;
import com.projectapps.movieapp.models.TvShowModel;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TvShow extends AppCompatActivity {

    ArrayList<TvShowModel> tvShows;
    Button backButton;
    RecyclerView recyclerViewTvShows;

    TvShowAdapter showAdapter;

    MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show);
        initiateView();
        getTvShows();
    }





    private void  initiateView(){
        tvShows = new ArrayList<>();
        backButton = findViewById(R.id.backButtonTvShow);
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerViewTvShows = findViewById(R.id.recyclerViewTvShows);
    }



    private void getTvShows(){
        viewModel.getTvShows().observe(this, new Observer<List<TvShowModel>>() {
            @Override
            public void onChanged(List<TvShowModel> tvShowModels) {
                for(TvShowModel show:tvShowModels){
                    tvShows.add(show);
                }
                showAdapter = new TvShowAdapter(getApplicationContext(),tvShows);
                recyclerViewTvShows.setAdapter(showAdapter);
                showAdapter.setOnItemClickListener(new CategoryListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        moveToShowDetails(position);
                    }
                });
                recyclerViewTvShows.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            }
        });
    }

    private void moveToShowDetails(int position){
        Intent i = new Intent(getApplicationContext(), ShowDetails.class);
        i.putExtra("Show_name",tvShows.get(position).getTitle());
        i.putExtra("show_avarage",(""+tvShows.get(position).getVote_avarage()).substring(0,3));
        i.putExtra("show_overview",tvShows.get(position).getShow_overview());
        i.putExtra("image_path",tvShows.get(position).getPoster_path());
        i.putExtra("show_id",""+tvShows.get(position).getShow_id());
        startActivity(i);
    }
}