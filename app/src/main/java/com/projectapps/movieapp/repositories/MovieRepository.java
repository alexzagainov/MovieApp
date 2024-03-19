package com.projectapps.movieapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.request.MovieApiClient;
import com.projectapps.movieapp.utils.MovieApi;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;


    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    public MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public MutableLiveData<List<MovieModel>> searchMovies(String movieName){
        return movieApiClient.searchMovies(movieName);}

    public MutableLiveData<List<MovieModel>> getPopular() {
        return movieApiClient.getPopular();
    }
    public MutableLiveData<List<MovieModel>> getUpcoming() {
        return movieApiClient.getUpcoming();
    }

    public MutableLiveData<List<MovieModel>> getTopRated() {
        return movieApiClient.getTopRated();
    }
    public MutableLiveData<List<MovieModel>> getNowPlaying() {
        return movieApiClient.getNowPlaying();
    }

    public MutableLiveData<List<MovieModel>> getMovies(String type, String movieName, int page) {
        return movieApiClient.getMovies(type,movieName,page);
    }
}
