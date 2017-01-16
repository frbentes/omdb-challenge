package com.frbentes.omdbchallenge.service;

import com.frbentes.omdbchallenge.service.dto.MovieDetail;
import com.frbentes.omdbchallenge.service.dto.MovieResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frbentes on 11/01/17.
 */
public class LookupService {

    private static final String BASE_URL = "http://www.omdbapi.com";
    private static Omdb omdbApi;

    private static void setupApi() {
        if (omdbApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            omdbApi = retrofit.create(Omdb.class);
        }
    }

    public static MovieResult searchByTitle(String title) throws IOException {
        setupApi();

        Call<MovieResult> call = omdbApi.getMovieByTitle(title);

        return call.execute().body();
    }

    public static MovieDetail searchById(String imdbId) throws IOException {
        setupApi();

        Call<MovieDetail> call = omdbApi.getMovieDetailById(imdbId);

        return call.execute().body();
    }

    public static class Result {
        private List<MovieDetail> movieDetailList;
        private String totalResults;
        private String response;

        public Result(MovieResult movieResult) {
            this.movieDetailList = new ArrayList<>();
            this.totalResults = movieResult.totalResults;
            this.response = movieResult.response;
        }

        public void addToList(MovieDetail movieDetail) {
            this.movieDetailList.add(movieDetail);
        }

        public List<MovieDetail> getMovieDetailList() {
            return movieDetailList;
        }

        public String getTotalResults() {
            return totalResults;
        }

        public String getResponse() {
            return response;
        }
    }

}
