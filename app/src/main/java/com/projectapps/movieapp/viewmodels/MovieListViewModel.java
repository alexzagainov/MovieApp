package com.projectapps.movieapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.repositories.MovieRepository;
import com.projectapps.movieapp.request.Servicey;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MovieRepository movieRepository;
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public MutableLiveData<List<MovieModel>> searchMovies(String movieName) {
        return movieRepository.searchMovies(movieName);
    }
    public MutableLiveData<List<MovieModel>> getPopular() {
        return movieRepository.getPopular();
    }
    public MutableLiveData<List<MovieModel>> getUpcoming() {
        return movieRepository.getUpcoming();
    }

    public MutableLiveData<List<MovieModel>> getTopRated() {
        return movieRepository.getTopRated();
    }
    public MutableLiveData<List<MovieModel>> getNowPlaying() {
        return movieRepository.getNowPlaying();
    }
}
