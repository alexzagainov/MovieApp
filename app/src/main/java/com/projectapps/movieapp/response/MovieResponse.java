package com.projectapps.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectapps.movieapp.models.MovieModel;

import java.util.List;

//This class is for getting multiple movies(Movies list) - popular movies
public class MovieResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;


    public int getTotal_count(){
        return total_count;
    }


    public List<MovieModel> getMovies() {
        return movies;
    }

}
