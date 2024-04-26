package com.projectapps.movieapp.request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.projectapps.movieapp.models.ActorModel;
import com.projectapps.movieapp.models.GenreModel;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.models.ShowImageModel;
import com.projectapps.movieapp.models.TvShowModel;
import com.projectapps.movieapp.response.MovieCreditsResponse;
import com.projectapps.movieapp.response.MovieDetailResponse;
import com.projectapps.movieapp.response.MovieResponse;
import com.projectapps.movieapp.response.ShowImagesResponse;
import com.projectapps.movieapp.response.TvShowsResponse;
import com.projectapps.movieapp.utils.Credentials;
import com.projectapps.movieapp.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> searchMovies;
    private MutableLiveData<List<MovieModel>> popularMovies;
    private MutableLiveData<List<MovieModel>> upcomingMovies;
    private MutableLiveData<List<MovieModel>> topRatedMovies;
    private MutableLiveData<List<MovieModel>> nowPlayingMovies;

    private MutableLiveData<List<TvShowModel>> tvShows;

    private MutableLiveData<List<MovieModel>> similarMovies;
    private MutableLiveData<List<GenreModel>> movieGenres;
    private MutableLiveData<List<ActorModel>> movieActors;

    private MutableLiveData<List<MovieModel>> movieList;
    private static MovieApiClient instance;

    private MutableLiveData<List<ShowImageModel>> showImages;

    MovieApi movieApi = Servicey.getMovieApi();

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    public MovieApiClient() {
        searchMovies = new MutableLiveData<List<MovieModel>>();
        popularMovies = new MutableLiveData<List<MovieModel>>();
        upcomingMovies = new MutableLiveData<List<MovieModel>>();
        topRatedMovies = new MutableLiveData<List<MovieModel>>();
        nowPlayingMovies = new MutableLiveData<List<MovieModel>>();
        movieList = new MutableLiveData<List<MovieModel>>();
        movieGenres = new MutableLiveData<List<GenreModel>>();
        movieActors = new MutableLiveData<List<ActorModel>>();
        similarMovies = new MutableLiveData<List<MovieModel>>();
        tvShows = new MutableLiveData<List<TvShowModel>>();
        showImages = new MutableLiveData<List<ShowImageModel>>();
    }

    //Returning a list of URL paths for images of the tvShow that asked
    public MutableLiveData<List<ShowImageModel>> getTvShowImages(String show_id){
        showImages = new MutableLiveData<List<ShowImageModel>>();
        Call<ShowImagesResponse> responseCall = movieApi.getShowImages(show_id,Credentials.API_KEY);
        responseCall.enqueue(new Callback<ShowImagesResponse>() {

            @Override
            public void onResponse(Call<ShowImagesResponse> call, Response<ShowImagesResponse> response) {
                if(response.code()==200){
                    if (response.body() != null) {
                        List<ShowImageModel> show_images = new ArrayList<>(response.body().getImages());
                        showImages.postValue(show_images);
                    }
                }
            }
            @Override
            public void onFailure(Call<ShowImagesResponse> call, Throwable t) {

            }
        });
        return showImages;
    }

    //Returning a list of popular TV shows
    public MutableLiveData<List<TvShowModel>> getTvShows(){
        Call<TvShowsResponse> responseCall = movieApi.getTvShows(Credentials.API_KEY,1);
        responseCall.enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(Call<TvShowsResponse> call, Response<TvShowsResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<TvShowModel> shows = new ArrayList<>(response.body().getTvShows());
                        tvShows.postValue(shows);
                    }
                }
            }
            @Override
            public void onFailure(Call<TvShowsResponse> call, Throwable t) {

            }
        });
        return tvShows;
    }

    //Returning a list of similar movies of specific movie.
    public MutableLiveData<List<MovieModel>> getSimilarMovies(String movieId){
        Call<MovieResponse> responseCall = movieApi.getSimilarMovies(movieId,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        similarMovies.postValue(movies);
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return similarMovies;
    }

    //Returning a list of actors of specific movie
    public MutableLiveData<List<ActorModel>> getActors(String movieId){
        Call<MovieCreditsResponse> responseCall = movieApi.getActors(movieId,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieCreditsResponse>() {
            @Override
            public void onResponse(Call<MovieCreditsResponse> call, Response<MovieCreditsResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<ActorModel> actors = new ArrayList<>(response.body().getActors());
                        movieActors.postValue(actors);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieCreditsResponse> call, Throwable t) {

            }
        });
        return movieActors;
    }

    //Returning a list of movies (depends on the genre)
    public MutableLiveData<List<MovieModel>> discoverMovies(String genreId){
        Call<MovieResponse> responseCall = movieApi.discoverMovie(Credentials.API_KEY,genreId);
        movieList = new MutableLiveData<List<MovieModel>>();
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        movieList.postValue(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {}
        });
        return movieList;

    }

    //Returning a list of genres of specifi movie
    public MutableLiveData<List<GenreModel>> getGenres(int movieId){
        Call<MovieDetailResponse> responseCall = movieApi.getGenres(movieId,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<GenreModel> genres = new ArrayList<>(response.body().getGenres());
                        movieGenres.postValue(genres);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {

            }
        });
        return movieGenres;
    }

    public MutableLiveData<List<MovieModel>> getMovies(String type, String movieName, int page) {
        switch (type){
            case "Search":
                return (CallMovie(searchMovies,getResponseCall(type,movieName,page)));
            case "Popular":
                return (CallMovie(popularMovies,getResponseCall(type,movieName,page)));
            case "Upcoming":
                return (CallMovie(upcomingMovies,getResponseCall(type,movieName,page)));
            case "TopRated":
                return (CallMovie(topRatedMovies,getResponseCall(type,movieName,page)));
            case "NowPlaying":
                return (CallMovie(nowPlayingMovies,getResponseCall(type,movieName,page)));
            default:
                return (CallMovie(searchMovies,getResponseCall(type,movieName,page)));
        }
    }


    private Call<MovieResponse> getResponseCall(String type, String movieName, int page){
        switch (type){
            case "Search":
                return movieApi.searchMovie(Credentials.API_KEY,movieName,page);
            case "Popular":
                return movieApi.getPopular(Credentials.API_KEY, page);
            case "Upcoming":
                return movieApi.getUpcoming(Credentials.API_KEY, page);
            case "TopRated":
                return movieApi.getTopRated(Credentials.API_KEY, page);
            case "NowPlaying":
                return movieApi.getNowPlaying(Credentials.API_KEY, page);
            default:
                return movieApi.searchMovie(Credentials.API_KEY,movieName,page);
        }
    }
    private MutableLiveData<List<MovieModel>> CallMovie(MutableLiveData<List<MovieModel>> movieList,Call<MovieResponse> responseCall){
            responseCall.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                            movieList.postValue(movies);
                        }
                    } else {
                        try {
                            Log.v("TAG", "Error " + response.errorBody().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {}
            });
            return movieList;
    }
}

