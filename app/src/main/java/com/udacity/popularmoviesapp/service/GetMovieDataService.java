package com.udacity.popularmoviesapp.service;

import com.udacity.popularmoviesapp.model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieDataService {

    @GET("movie/popular")
    Call<MovieResults> getMoviesByPopularity(@Query("page") int page,@Query("db_api_key") String db_api_key);

    @GET("movie/top_rated")
    Call<MovieResults> getMoviesByTopRated(@Query("page") int page,@Query("db_api_key") String db_api_key);
}
