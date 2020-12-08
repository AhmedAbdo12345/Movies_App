package com.example.movies.JSON_API;

import com.example.movies.MoviesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrailersApi  {


    public static MoviesModel parseMoviesModelJson(String json) throws Exception {
        MoviesModel S = null;
        // String homepage = null;
        ArrayList<String> Movie_Key=new ArrayList<>();
        try {

//            JSONObject Movie = new JSONObject(json);
//            homepage = Movie.getString("homepage");

            JSONObject Movie = new JSONObject(json);
            JSONArray result = Movie.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject j = (JSONObject) result.get(i);

                Movie_Key.add(j.getString("key"));

                S = new MoviesModel(Movie_Key);


            }} catch (Exception ex) {

            throw ex;
        }
        MoviesModel film = new MoviesModel();

        film.setMovie_key(Movie_Key);
        return film;
    }
}