package com.projectapps.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectapps.movieapp.models.GenreModel;

import java.util.List;

public class MovieDetailResponse {
    @SerializedName("genres")
    @Expose()
    private List<GenreModel> genres;

    public List<GenreModel> getGenres() {
        return genres;
    }
}
