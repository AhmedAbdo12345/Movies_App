package com.example.movies.JSON_API;

import com.example.movies.MoviesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReviewsApi {

    public static MoviesModel parseMoviesModelJson(String json) throws Exception {
        MoviesModel S = null;
        // String homepage = null;
        ArrayList<String> Movie_review=new ArrayList<>();
        try {



            JSONObject Movie = new JSONObject(json);
            JSONArray result = Movie.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject j = (JSONObject) result.get(i);

                Movie_review.add(j.getString("url"));

                S = new MoviesModel(Movie_review);


            }} catch (Exception ex) {

            throw ex;
        }
        MoviesModel film = new MoviesModel();

        film.setMovie_review(Movie_review);

        return film;
    }
}
