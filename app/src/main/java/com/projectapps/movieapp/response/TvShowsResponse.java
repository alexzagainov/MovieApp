package com.projectapps.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.models.TvShowModel;

import java.util.List;

public class TvShowsResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<TvShowModel> shows;


    public int getTotal_count(){
        return total_count;
    }


    public List<TvShowModel> getTvShows() {
        return shows;
    }

}
