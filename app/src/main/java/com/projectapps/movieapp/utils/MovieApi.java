package com.projectapps.movieapp.utils;

import com.projectapps.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key")String key,
            @Query("query")String query,
            @Query("page")int page
    );

    @GET("movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/upcoming")
    Call<MovieSearchResponse> getUpcoming(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/top_rated")
    Call<MovieSearchResponse> getTopRated(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/now_playing")
    Call<MovieSearchResponse> getNowPlaying(
            @Query("api_key")String key,
            @Query("page")int page
    );



}
