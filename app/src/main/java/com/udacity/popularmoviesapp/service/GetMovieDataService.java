package com.udacity.popularmoviesapp.service;

import com.udacity.popularmoviesapp.model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieDataService {
//http://api.themoviedb.org/3/movie/popular?

    @GET("movie/popular")
    Call<MovieResults> getMoviesByPopularity(@Query("api_key") String db_api_key);

    @GET("movie/top_rated")
    Call<MovieResults> getMoviesByTopRated(@Query("api_key") String db_api_key);
}
