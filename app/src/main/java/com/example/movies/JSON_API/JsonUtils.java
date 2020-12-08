package com.example.movies.JSON_API;

import com.example.movies.MoviesModel;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonUtils {

    public static MoviesModel parseMoviesModelJson(String json) throws Exception {
        MoviesModel S = null;
        ArrayList<String> poster_path = new ArrayList<>();
        ArrayList<String> original_title = new ArrayList<>();
        ArrayList<String> overview = new ArrayList<>();
        ArrayList<String> release_date = new ArrayList<>();
        ArrayList<String> vote_count = new ArrayList<>();
        ArrayList<String> movie_id = new ArrayList<>();
        try {

            JSONObject Movie = new JSONObject(json);
            JSONArray result = Movie.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject j = (JSONObject) result.get(i);

                poster_path.add(j.getString("poster_path"));
                original_title.add(j.getString("original_title"));
                overview.add(j.getString("overview"));
                release_date.add(j.getString("release_date"));
                vote_count.add(j.getString("vote_count"));
                movie_id.add(j.getString("id"));
            }


            S = new MoviesModel(poster_path, original_title, overview, release_date, vote_count,movie_id);


        } catch (Exception ex) {

            throw ex;
        }
        MoviesModel film = new MoviesModel();

        film.setPoster_path(poster_path);
        film.setOriginal_title(original_title);
        film.setOverview(overview);
        film.setRelease_date(release_date);
        film.setVote_count(vote_count);
        film.setMovie_id(movie_id);
        return film;
    }
}
