package com.frbentes.omdbchallenge.service;

import com.frbentes.omdbchallenge.service.dto.MovieDetail;
import com.frbentes.omdbchallenge.service.dto.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by frbentes on 11/01/17.
 */
public interface Omdb {

    @GET("/?type=movie")
    Call<MovieResult> getMovieByTitle(@Query("s") String title);

    @GET("/?plot=full")
    Call<MovieDetail> getMovieDetailById(@Query("i") String imdbId);

}
