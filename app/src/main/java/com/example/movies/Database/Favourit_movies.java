package com.example.movies.Database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Favourit_movies {


    @PrimaryKey
    @NonNull  String id;
    String poster;
    String title;
    String vote_count;
    String release_date;
    String overview;



//    public Favourit_movies(@NonNull String id, String poster, String title, String vote_count, String release_date, String overview) {
//        this.id = id;
//        this.poster = poster;
//        this.title = title;
//        this.vote_count = vote_count;
//        this.release_date = release_date;
//        this.overview = overview;
//    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
