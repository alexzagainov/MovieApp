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

    public MutableLiveData<List<MovieModel>> getMovies(String type, String movieName, int page) {
        return movieRepository.getMovies(type,movieName,page);


    }
}
