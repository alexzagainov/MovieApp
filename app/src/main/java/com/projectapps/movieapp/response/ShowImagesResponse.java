package com.projectapps.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectapps.movieapp.models.ShowImageModel;

import java.util.List;

public class ShowImagesResponse {
    @SerializedName("backdrops")
    @Expose()
    private List<ShowImageModel> images;

    public List<ShowImageModel> getImages() {
        return images;
    }
}
