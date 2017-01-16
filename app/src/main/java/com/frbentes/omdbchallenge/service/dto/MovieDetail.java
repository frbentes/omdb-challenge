package com.frbentes.omdbchallenge.service.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frbentes on 11/01/17.
 */
public class MovieDetail implements Parcelable {

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Rated")
    public String rated;

    @SerializedName("Released")
    public String released;

    @SerializedName("Runtime")
    public String runtime;

    @SerializedName("Genre")
    public String genre;

    @SerializedName("Director")
    public String director;

    @SerializedName("Writer")
    public String writer;

    @SerializedName("Actors")
    public String actors;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Language")
    public String language;

    @SerializedName("Country")
    public String country;

    @SerializedName("Awards")
    public String awards;

    @SerializedName("Poster")
    public String poster;

    @SerializedName("Metascore")
    public String metascore;

    @SerializedName("imdbRating")
    public String imdbRating;

    @SerializedName("imdbVotes")
    public String imdbVotes;

    @SerializedName("imdbID")
    public String imdbID;

    @SerializedName("Type")
    public String type;

    @SerializedName("Response")
    public String response;

    public byte[] imgPoster;

    @Override
    public String toString() {
        return "Detail{" +
                "Title='" + this.title + '\'' +
                ", Year='" + this.year + '\'' +
                ", Rated='" + this.rated + '\'' +
                ", Released='" + this.released + '\'' +
                ", Runtime='" + this.runtime + '\'' +
                ", Genre='" + this.genre + '\'' +
                ", Director='" + this.director + '\'' +
                ", Writer='" + this.writer + '\'' +
                ", Actors='" + this.actors + '\'' +
                ", Plot='" + this.plot + '\'' +
                ", Language='" + this.language + '\'' +
                ", Country='" + this.country + '\'' +
                ", Awards='" + this.awards + '\'' +
                ", Poster='" + this.poster + '\'' +
                ", Metascore='" + this.metascore + '\'' +
                ", imdbRating='" + this.imdbRating + '\'' +
                ", imdbVotes='" + this.imdbVotes + '\'' +
                ", imdbID='" + this.imdbID + '\'' +
                ", Type='" + this.type + '\'' +
                ", Response='" + this.response + '\'' +
                '}';
    }

    public MovieDetail() {
    }

    protected MovieDetail(Parcel in) {
        this.title = in.readString();
        this.year = in.readString();
        this.rated = in.readString();
        this.released = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.director = in.readString();
        this.writer = in.readString();
        this.actors = in.readString();
        this.plot = in.readString();
        this.language = in.readString();
        this.country = in.readString();
        this.awards = in.readString();
        this.poster = in.readString();
        this.metascore = in.readString();
        this.imdbRating = in.readString();
        this.imdbVotes = in.readString();
        this.imdbID = in.readString();
        this.type = in.readString();
        this.response = in.readString();
        this.imgPoster = new byte[in.readInt()];
        in.readByteArray(this.imgPoster);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.rated);
        dest.writeString(this.released);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.director);
        dest.writeString(this.writer);
        dest.writeString(this.actors);
        dest.writeString(this.plot);
        dest.writeString(this.language);
        dest.writeString(this.country);
        dest.writeString(this.awards);
        dest.writeString(this.poster);
        dest.writeString(this.metascore);
        dest.writeString(this.imdbRating);
        dest.writeString(this.imdbVotes);
        dest.writeString(this.imdbID);
        dest.writeString(this.type);
        dest.writeString(this.response);
        dest.writeInt(this.imgPoster.length);
        dest.writeByteArray(this.imgPoster);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public byte[] getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(byte[] imgPoster) {
        this.imgPoster = imgPoster;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieDetail> CREATOR = new Parcelable.Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel in) {
            return new MovieDetail(in);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

}
