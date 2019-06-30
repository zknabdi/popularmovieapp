package com.udacity.popularmoviesapp.service;

import com.udacity.popularmoviesapp.model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieDataService {

    @GET("popular")
    Call<MovieResults> getMoviesByPopularity(@Query("result") int result,@Query("db_api_key") String db_api_key);

    @GET("top_rated")
    Call<MovieResults> getMoviesByTopRated(@Query("result") int result,@Query("db_api_key") String db_api_key);
}
