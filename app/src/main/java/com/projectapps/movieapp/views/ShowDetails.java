package com.projectapps.movieapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectapps.movieapp.R;
import com.projectapps.movieapp.adapters.ShowImagesAdapter;
import com.projectapps.movieapp.models.ShowImageModel;
import com.projectapps.movieapp.utils.ImageLoader;
import com.projectapps.movieapp.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowDetails extends AppCompatActivity {
    private ImageView imageView;
    private TextView showName;
    private TextView showAvarage;
    private TextView showOverView;

    private Button backButton;

    private RecyclerView recyclerView;

    private MovieListViewModel viewModel;

    private ArrayList<ShowImageModel> images;
    private ShowImagesAdapter adapter;
    private RecyclerView.LayoutManager imagesManager;
    private Intent intent;

    private ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        initiateTheView();
        getTvShowsImages();

    }



    private void initiateTheView(){

        showName = findViewById(R.id.tvShowNameDetail);
        showAvarage =findViewById(R.id.detailShowRating);
        showOverView = findViewById(R.id.detailShowOverview);
        imageView = findViewById(R.id.detailShowImage);
        backButton = findViewById(R.id.backButtonShowDetails);
        images = new ArrayList<>();

        intent = getIntent();
        showName.setText(intent.getStringExtra("Show_name"));
        showAvarage.setText(intent.getStringExtra("show_avarage"));
        showOverView.setText(intent.getStringExtra("show_overview"));
        imageLoader = new ImageLoader();
        imageLoader.loadImage(imageView,intent.getStringExtra("image_path"));
        recyclerView = findViewById(R.id.imagesRecyclerView);

        imagesManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        viewModel= new ViewModelProvider(this).get(MovieListViewModel.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getTvShowsImages(){
        viewModel.getTvShowsImages(intent.getStringExtra("show_id")).observe(this, new Observer<List<ShowImageModel>>() {
            @Override
            public void onChanged(List<ShowImageModel> showImageModels) {
                images.clear();
                for(ShowImageModel image: showImageModels){
                    images.add(image);
                    Log.v("Images", image.getImage_url());
                }
                adapter = new ShowImagesAdapter(images,getApplicationContext());
                recyclerView.setLayoutManager(imagesManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}