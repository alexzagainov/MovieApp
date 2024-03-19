package com.projectapps.movieapp.request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.projectapps.movieapp.models.MovieModel;
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
    private static MovieApiClient instance;

    MovieApi movieApi = Servicey.getMovieApi();

    public static MovieApiClient getInstance(){
        if(instance == null){
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
    }

    public MutableLiveData<List<MovieModel>> searchMovies(String movieName) {
        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.API_KEY,movieName,1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    if(response.body() != null) {
                        Log.v("Tag", "Search result MOVIES");
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        for (MovieModel movie : movies) {
                            Log.v("Tag", "The release date: " + movie.getRelease_date());
                        }
                        searchMovies.postValue(movies);
                    }
                }else{
                    try {
                        Log.v("TAG","Error "+ response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
        return searchMovies;
    }
    public MutableLiveData<List<MovieModel>> getPopular() {
        Call<MovieSearchResponse> responseCall = movieApi.getPopular(Credentials.API_KEY,1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    if(response.body() != null) {
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        popularMovies.postValue(movies);
                    }
                }else{
                    try {
                        Log.v("TAG","Error "+ response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
        return popularMovies;
    }
    public MutableLiveData<List<MovieModel>> getUpcoming() {
        Call<MovieSearchResponse> responseCall = movieApi.getUpcoming(Credentials.API_KEY,1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    if(response.body() != null) {
                        Log.v("Tag", "Upcoming MOVIES");
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        for (MovieModel movie : movies) {
                            Log.v("Tag", "The release date: " + movie.getRelease_date());
                        }
                        upcomingMovies.postValue(movies);
                    }
                }else{
                    try {
                        Log.v("TAG","Error "+ response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
        return upcomingMovies;
    }

    public MutableLiveData<List<MovieModel>> getTopRated() {
        Call<MovieSearchResponse> responseCall = movieApi.getTopRated(Credentials.API_KEY,1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    if(response.body() != null) {
                        Log.v("Tag", "Top Rated MOVIES");
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        for (MovieModel movie : movies) {
                            Log.v("Tag", "The release date: " + movie.getRelease_date());
                        }
                        topRatedMovies.postValue(movies);
                    }
                }else{
                    try {
                        Log.v("TAG","Error "+ response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
        return topRatedMovies;
    }
    public MutableLiveData<List<MovieModel>> getNowPlaying() {
        Call<MovieSearchResponse> responseCall = movieApi.getNowPlaying(Credentials.API_KEY,1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    if(response.body() != null) {
                        Log.v("Tag", "Now Playing MOVIES");
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        for (MovieModel movie : movies) {
                            Log.v("Tag", "The release date: " + movie.getRelease_date());
                        }
                        nowPlayingMovies.postValue(movies);
                    }
                }else{
                    try {
                        Log.v("TAG","Error "+ response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
        return nowPlayingMovies;
    }



}
