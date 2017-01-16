package com.frbentes.omdbchallenge.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by frbentes on 15/01/17.
 */
public class MovieTable extends SugarRecord {
    @Unique
    public String imdbId;
    public String title;
    public String year;
    public String runTime;
    public String genre;
    public String director;
    public String actors;
    public String plot;
    public String awards;
    public byte[] poster;

    public MovieTable() {
    }

    public MovieTable(String imdbId, String title, String year, String runTime, String genre,
                      String director, String actors, String plot, String awards, byte[] poster) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.awards = awards;
        this.poster = poster;
    }
}
