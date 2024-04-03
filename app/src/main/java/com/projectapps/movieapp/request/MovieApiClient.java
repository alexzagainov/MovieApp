package com.projectapps.movieapp.request;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.projectapps.movieapp.models.ActorModel;
import com.projectapps.movieapp.models.GenreModel;
import com.projectapps.movieapp.models.MovieModel;
import com.projectapps.movieapp.response.MovieCreditsResponse;
import com.projectapps.movieapp.response.MovieDetailResponse;
import com.projectapps.movieapp.response.MovieSearchResponse;
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

    private MutableLiveData<List<MovieModel>> similarMovies;
    private MutableLiveData<List<GenreModel>> movieGenres;
    private MutableLiveData<List<ActorModel>> movieActors;

    private MutableLiveData<List<MovieModel>> movieList;
    private static MovieApiClient instance;

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

    }

    public MutableLiveData<List<MovieModel>> getSimilarMovies(String movieId){
        Call<MovieSearchResponse> responseCall = movieApi.getSimilarMovies(movieId,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        for (MovieModel movie : movies) {
                            Log.v("Similar Movie", "movie name " + movie.getTitle());
                        }
                        similarMovies.postValue(movies);
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
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
        return similarMovies;
    }

    public MutableLiveData<List<ActorModel>> getActors(String movieId){
        Call<MovieCreditsResponse> responseCall = movieApi.getActors(movieId,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieCreditsResponse>() {
            @Override
            public void onResponse(Call<MovieCreditsResponse> call, Response<MovieCreditsResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<ActorModel> actors = new ArrayList<>(response.body().getActors());
                        for(ActorModel actor: actors){
                            Log.v("Actors","actor name: "+actor.getName());
                        }
                        movieActors.postValue(actors);
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
            public void onFailure(Call<MovieCreditsResponse> call, Throwable t) {

            }
        });
        return movieActors;
    }

    public MutableLiveData<List<MovieModel>> discoverMovies(String genreId){
        Call<MovieSearchResponse> responseCall = movieApi.discoverMovie(Credentials.API_KEY,genreId);
        movieList = new MutableLiveData<List<MovieModel>>();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
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
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {}
        });
        return movieList;

    }

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
                } else {
                    try {
                        Log.v("TAG", "Error " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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


    private Call<MovieSearchResponse> getResponseCall(String type,String movieName,int page){
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
    private MutableLiveData<List<MovieModel>> CallMovie(MutableLiveData<List<MovieModel>> movieList,Call<MovieSearchResponse> responseCall){
            responseCall.enqueue(new Callback<MovieSearchResponse>() {
                @Override
                public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
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
                public void onFailure(Call<MovieSearchResponse> call, Throwable t) {}
            });
            return movieList;
    }


}

