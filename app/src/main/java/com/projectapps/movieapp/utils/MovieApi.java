package com.projectapps.movieapp.utils;

import com.projectapps.movieapp.response.MovieCreditsResponse;
import com.projectapps.movieapp.response.MovieDetailResponse;
import com.projectapps.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("movie/{movie_id}?")
    Call<MovieDetailResponse> getGenres(
            @Path("movie_id")int movie_id,
            @Query("api_key")String key
    );

    @GET("discover/movie")
    Call<MovieSearchResponse> discoverMovie(
            @Query("api_key")String key,
            @Query("with_genres")String genre_id
    );

    @GET("movie/{movie_id}/credits")
    Call<MovieCreditsResponse> getActors(
            @Path("movie_id")String movie_id,
            @Query("api_key")String key
    );

    @GET("movie/{movie_id}/similar")
    Call<MovieSearchResponse> getSimilarMovies(
            @Path("movie_id")String movie_id,
            @Query("api_key")String key
    );



}
