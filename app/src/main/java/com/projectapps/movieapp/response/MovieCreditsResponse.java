package com.projectapps.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectapps.movieapp.models.ActorModel;

import java.util.List;

import retrofit2.Call;

public class MovieCreditsResponse {
    @SerializedName("cast")
    @Expose
    private List<ActorModel> cast;

    public List<ActorModel> getActors() {
        return cast;
    }
}
