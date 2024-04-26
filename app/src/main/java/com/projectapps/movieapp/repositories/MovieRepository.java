package com.projectapps.movieapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projectapps.movieapp.models.ActorModel;
import com.projectapps.movieapp.models.GenreModel;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.models.ShowImageModel;
import com.projectapps.movieapp.models.TvShowModel;
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


    public MutableLiveData<List<MovieModel>> getMovies(String type, String movieName, int page) {
        return movieApiClient.getMovies(type,movieName,page);
    }

    public MutableLiveData<List<GenreModel>> getGenres(int movieId){
        return movieApiClient.getGenres(movieId);
    }

    public MutableLiveData<List<MovieModel>> discoverMovies(String genre_id){
        return movieApiClient.discoverMovies(genre_id);
    }

    public MutableLiveData<List<ActorModel>> getActors(String movieId){
        return movieApiClient.getActors(movieId);
    }

    public MutableLiveData<List<MovieModel>> getSimilarMovies(String movieId){
        return movieApiClient.getSimilarMovies(movieId);
    }
    public MutableLiveData<List<TvShowModel>> getTvShows(){
        return movieApiClient.getTvShows();
    }
    public MutableLiveData<List<ShowImageModel>> getTvShowsImages(String show_id){
        return movieApiClient.getTvShowImages(show_id);
    }
}
