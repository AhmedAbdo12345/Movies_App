package com.example.movies;

import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MoviesModel implements Parcelable {

    private ArrayList<String> poster_path;
    private ArrayList<String> original_title;
    private ArrayList<String> overview ;
    private ArrayList<String> release_date ;
    private ArrayList<String> vote_count;
    private ArrayList<String> movie_id;

    public MoviesModel(){

    }


    public MoviesModel(ArrayList<String> poster_path, ArrayList<String> original_title, ArrayList<String> overview, ArrayList<String> release_date, ArrayList<String> vote_count,ArrayList<String> movie_id) {
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.movie_id=movie_id;
    }

    protected MoviesModel(Parcel in) {
        poster_path = in.createStringArrayList();
        original_title = in.createStringArrayList();
        overview = in.createStringArrayList();
        release_date = in.createStringArrayList();
        vote_count = in.createStringArrayList();
        movie_id = in.createStringArrayList();
        movie_key = in.createStringArrayList();
        movie_review = in.createStringArrayList();
    }

    public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

    public ArrayList<String> getPoster_path() {
        return poster_path;
    }

    public ArrayList<String> getOriginal_title() {
        return this.original_title;
    }

    public ArrayList<String> getOverview() {
        return overview;
    }

    public ArrayList<String> getRelease_date() {
        return release_date;
    }

    public ArrayList<String> getVote_count() {
        return vote_count;
    }

    public ArrayList<String> getMovie_id() {
        return movie_id;
    }

    public void setPoster_path(ArrayList<String> poster_path) {
        this.poster_path = poster_path;
    }

    public void setOriginal_title(ArrayList<String> original_title) {
        this.original_title = original_title;
    }

    public void setOverview(ArrayList<String> overview) {
        this.overview = overview;
    }

    public void setRelease_date(ArrayList<String> release_date) {
        this.release_date = release_date;
    }

    public void setVote_count(ArrayList<String> vote_count) {
        this.vote_count = vote_count;
    }

    public void setMovie_id(ArrayList<String> movie_id) {
        this.movie_id = movie_id;
    }


    /*---------------------------Trailor Model--------------*/


    private ArrayList<String> movie_key;

    public MoviesModel(ArrayList<String> movie_key) {
        this.movie_key = movie_key;
    }

    public ArrayList<String> getMovie_key() {
        return movie_key;
    }

    public void setMovie_key(ArrayList<String> movie_key) {
        this.movie_key = movie_key;
    }

    /*----------------------------------------------*/
    /*-----------------Reviews Model-----------------------------*/

    private ArrayList<String> movie_review;


//    public MoviesModel(ArrayList<String> movie_review) {
//        this.movie_review = movie_review;
//    }

    public ArrayList<String> getMovie_review() {
        return movie_review;
    }

    public void setMovie_review(ArrayList<String> movie_review) {
        this.movie_review = movie_review;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(poster_path);
        dest.writeStringList(original_title);
        dest.writeStringList(overview);
        dest.writeStringList(release_date);
        dest.writeStringList(vote_count);
        dest.writeStringList(movie_id);
        dest.writeStringList(movie_key);
        dest.writeStringList(movie_review);
    }

    /*----------------------------------------------*/

}
