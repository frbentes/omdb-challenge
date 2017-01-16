package com.frbentes.omdbchallenge.service.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frbentes on 11/01/17.
 */
public class Movie {

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("imdbID")
    public String imdbID;

    @SerializedName("Type")
    public String type;

    @SerializedName("Poster")
    public String poster;

    @Override
    public String toString() {
        return "\nMovie{" +
                "Title='" + this.title + '\'' +
                ", Year='" + this.year + '\'' +
                ", imdbID='" + this.imdbID + '\'' +
                ", Type='" + this.type + '\'' +
                ", Poster='" + this.poster + '\'' +
                '}';
    }

}
