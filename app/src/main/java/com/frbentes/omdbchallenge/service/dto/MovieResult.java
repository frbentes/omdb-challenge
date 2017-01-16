package com.frbentes.omdbchallenge.service.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by frbentes on 11/01/17.
 */
public class MovieResult {

    @SerializedName("Search")
    public List<Movie> moviesList;

    @SerializedName("totalResults")
    public String totalResults;

    @SerializedName("Response")
    public String response;

    @Override
    public String toString() {
        return "Result{" +
                "Search=" + this.moviesList +
                ", totalResults='" + this.totalResults + '\'' +
                ", Response='" + this.response + '\'' +
                '}';
    }
}
