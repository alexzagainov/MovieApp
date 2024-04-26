package com.projectapps.movieapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projectapps.movieapp.models.ActorModel;
import com.projectapps.movieapp.models.GenreModel;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.models.ShowImageModel;
import com.projectapps.movieapp.models.TvShowModel;
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

    public MutableLiveData<List<GenreModel>> getGenres(int movieId){
        return movieRepository.getGenres(movieId);
    }

    public MutableLiveData<List<MovieModel>> discoverMovies(String genre_id){
        return movieRepository.discoverMovies(genre_id);
    }

    public MutableLiveData<List<ActorModel>> getActors(String movieId){
        return movieRepository.getActors(movieId);
    }
    public MutableLiveData<List<MovieModel>> getSimilarMovies(String movieId){
        return movieRepository.getSimilarMovies(movieId);
    }

    public MutableLiveData<List<TvShowModel>> getTvShows(){
        return movieRepository.getTvShows();
    }

    public MutableLiveData<List<ShowImageModel>> getTvShowsImages(String show_id){
        return movieRepository.getTvShowsImages(show_id);
    }
}
