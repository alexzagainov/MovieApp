package com.projectapps.movieapp.utils;

import com.projectapps.movieapp.response.MovieCreditsResponse;
import com.projectapps.movieapp.response.MovieDetailResponse;
import com.projectapps.movieapp.response.MovieResponse;
import com.projectapps.movieapp.response.ShowImagesResponse;
import com.projectapps.movieapp.response.TvShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("search/movie")
    Call<MovieResponse> searchMovie(
            @Query("api_key")String key,
            @Query("query")String query,
            @Query("page")int page
    );

    @GET("movie/popular")
    Call<MovieResponse> getPopular(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("movie/{movie_id}?")
    Call<MovieDetailResponse> getGenres(
            @Path("movie_id")int movie_id,
            @Query("api_key")String key
    );

    @GET("discover/movie")
    Call<MovieResponse> discoverMovie(
            @Query("api_key")String key,
            @Query("with_genres")String genre_id
    );

    @GET("movie/{movie_id}/credits")
    Call<MovieCreditsResponse> getActors(
            @Path("movie_id")String movie_id,
            @Query("api_key")String key
    );

    @GET("movie/{movie_id}/similar")
    Call<MovieResponse> getSimilarMovies(
            @Path("movie_id")String movie_id,
            @Query("api_key")String key
    );

    @GET("tv/popular")
    Call<TvShowsResponse> getTvShows(
            @Query("api_key")String key,
            @Query("page")int page
    );

    @GET("tv/{series_id}/images")
    Call<ShowImagesResponse> getShowImages(
            @Path("series_id")String series_id,
            @Query("api_key")String key
    );



}
