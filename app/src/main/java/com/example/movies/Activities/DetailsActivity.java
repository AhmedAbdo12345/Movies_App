package com.example.movies.Activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Adapters.ReviewsAdapter;
import com.example.movies.Adapters.TrailorsAdapter;
import com.example.movies.AppExecutor;
import com.example.movies.Adapters.FavouritAdapter;
import com.example.movies.Database.Database_movies;
import com.example.movies.Database.Favourit_movies;
import com.example.movies.JSON_API.ReviewsApi;
import com.example.movies.JSON_API.TrailersApi;
import com.example.movies.MoviesModel;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailsActivity extends AppCompatActivity implements TrailorsAdapter.ListItemClickListener, ReviewsAdapter.ListItemClickListener{
    TextView title;
    ImageView Image;
    TextView ReleaseDate;
    TextView voteCount;
    TextView Movieoverview;
    /*------------Movie Favourite ditils------------*/
    TextView title2;
    ImageView Image2;
    TextView ReleaseDate2;
    TextView voteCount2;
    TextView Movieoverview2;

    /*-----------------------------------*/
    WebView webView;
 //   ImageView FavButton;
    String Movie_ID;
    String poster_path;
    String Tag = "ASD";
    String Tag1 = "id";
    MoviesModel moviesModelTrailor;
    MoviesModel moviesModelReview;
    Database_movies db;

    boolean flag = false;

    private TrailorsAdapter mAdapter;
    private FavouritAdapter favouritAdapter;
    private ReviewsAdapter reviewsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_Reviews;
    Context context;
    String original_title;
    String vote_count;
    String release_date;
    String overview;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title = findViewById(R.id.title);
        Image = findViewById(R.id.Image);
        ReleaseDate = findViewById(R.id.releaseData);
        voteCount = findViewById(R.id.vote_count);
        Movieoverview = findViewById(R.id.overview);

        /*----------------------------------------------*/
        db = Database_movies.getInstance(getApplicationContext());
        //  webView = findViewById(R.id.webview);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {

            poster_path = intent.getStringExtra("poster_path");
            original_title = intent.getStringExtra("original_title");
            vote_count = intent.getStringExtra("vote_count");
            release_date = intent.getStringExtra("release_date");
            overview = intent.getStringExtra("overview");
            Movie_ID = intent.getStringExtra("id");

            Picasso.get().load("https://image.tmdb.org/t/p/w185//" + poster_path).into(Image);
            /*--------------Accessibility-------------------------*/
            Image.setContentDescription(original_title);
            title.setText(original_title);
            ReleaseDate.setText(release_date);
            voteCount.setText(vote_count + "/10");
            Movieoverview.setText(overview);

        }
        /*---------------------------------------------------*/


        /*-------------------------------------------------*/

        Log.d(Tag, Movie_ID);
        Trailor trailor=new Trailor();
        trailor.execute();

        Reviews reviews=new Reviews();
        reviews.execute();
        recyclerView = (RecyclerView) findViewById(R.id.trailorMovies);

        recyclerView_Reviews = (RecyclerView) findViewById(R.id.reviewMovies);

/*////////////////////////////////////////////////////////////////////*/

    }

    public  void  openweb(String web){

        Uri uri=Uri.parse(web);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }
    }

    private DetailsActivity getAction() {
        return DetailsActivity.this;
    }

    /*-------------------------onclick----------------------------*/


    @Override
    public void onClick(MoviesModel model, int position) {
        // webView.loadUrl("https://www.youtube.com/watch?v="+model.getMovie_key());
//        Intent intent = new Intent();
//        startActivity(intent);
    }

    /*---------favourit button------------------*/
    public void favouritbutton(View view) {

        final Favourit_movies task = new Favourit_movies();
        task.setId(Movie_ID);
        task.setPoster(poster_path);
        task.setTitle(original_title);
        task.setVote_count(vote_count);
        task.setRelease_date(release_date);
        task.setOverview(overview);
        // final Favourit_movies task1 = new Favourit_movies(Movie_ID,poster_path,original_title,vote_count,release_date,overview);

        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // insert new task
                    db.taskDao().insertTask(task);

                    Log.d(Tag1,"succful inset data ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CharSequence SaveMassage = getString(R.string.save_successfully);
                            Toast.makeText(DetailsActivity.this, SaveMassage, Toast.LENGTH_LONG).show();

                        }
                    });

                }catch (Exception ex){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            CharSequence SaveErrorMassage = getString(R.string.error_saved_before);
                            Toast.makeText(DetailsActivity.this, SaveErrorMassage, Toast.LENGTH_LONG).show();

                        }
                    });
                }
                // finish();

            }

        });
      //  Toast.makeText(DetailsActivity.this, "out executor  ", Toast.LENGTH_LONG).show();
/*------------------widget-------------------------*/

    }
    /*---------Unfavourit button------------------*/
    public void UNfavouritbutton(View view) {

        final Favourit_movies task = new Favourit_movies();
        task.setId(Movie_ID);
        task.setPoster(poster_path);
        task.setTitle(original_title);
        task.setVote_count(vote_count);
        task.setRelease_date(release_date);
        task.setOverview(overview);
        // final Favourit_movies task1 = new Favourit_movies(Movie_ID,poster_path,original_title,vote_count,release_date,overview);

        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // insert new task
                    db.taskDao().deleteTask(task);

                    Log.d(Tag1,"succful inset data ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CharSequence DeleteMassage = getString(R.string.Delet_successfully);
                            Toast.makeText(DetailsActivity.this, DeleteMassage, Toast.LENGTH_LONG).show();

                        }
                    });

                }catch (Exception ex){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CharSequence DeleteErrorMassage = getString(R.string.error_Delete_before);
                            Toast.makeText(DetailsActivity.this, DeleteErrorMassage, Toast.LENGTH_LONG).show();

                        }
                    });
                }
                // finish();

            }

        });
       // Toast.makeText(DetailsActivity.this, "out executor  ", Toast.LENGTH_LONG).show();


    }
    /*-------------------------------------------*/



    /*--------------Trailor-----------------------------*/
    public class Trailor extends AsyncTask<URL, Void, String> {
        String data = "";
        String Tag2="xx2";
        String Tag3="xx3";
        Trailor(){}

        @Override
        protected String doInBackground(URL... urls) {

            try {

                URL url = new URL("https://api.themoviedb.org/3/movie/"+Movie_ID+"/videos"+"?api_key=37789dc48bb9195cdca528bdbc31ce85&language=en-US");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }


            } catch (MalformedURLException e) {

                return e.getMessage();
            } catch (IOException e) {
                return e.getMessage();
            }
            Log.d(Tag3,data);
            return data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (data != null && !data.equals("")) {
                try {

                    TrailersApi trailersApi=new  TrailersApi();
                    moviesModelTrailor=  trailersApi.parseMoviesModelJson(s);
                    // webView.loadUrl("https://www.youtube.com/watch?v="+moviesModelTrailor.getMovie_key().toString());
                    //  Log.d(Tag2,data);
                    GridLayoutManager layoutManager = new GridLayoutManager(DetailsActivity.this, 1);

                    mAdapter = new TrailorsAdapter(getAction(), moviesModelTrailor,DetailsActivity.this);

                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                }catch (Exception e)
                {

                }
            }
        }
    }
    /*--------------------------------------------------------------------------------*/
    /*-------------------------------------Reviews-------------------------------------------*/
    public class Reviews extends AsyncTask<URL, Void, String> {
        String data = "";
        String Tag2="xx2";
        String Tag3="xx3";
        Reviews(){}

        @Override
        protected String doInBackground(URL... urls) {

            try {

                URL url = new URL("https://api.themoviedb.org/3/movie/"+Movie_ID+"/reviews"+"?api_key=37789dc48bb9195cdca528bdbc31ce85&language=en-US");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }


            } catch (MalformedURLException e) {

                return e.getMessage();
            } catch (IOException e) {
                return e.getMessage();
            }
            Log.d(Tag3,data);
            return data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (data != null && !data.equals("")) {
                try {

                    ReviewsApi reviewsApi=new  ReviewsApi();
                    moviesModelReview=  reviewsApi.parseMoviesModelJson(s);
                    // webView.loadUrl("https://www.youtube.com/watch?v="+moviesModelTrailor.getMovie_key().toString());
                    //  Log.d(Tag2,data);
                    GridLayoutManager layoutManager = new GridLayoutManager(DetailsActivity.this, 1);

                    reviewsAdapter = new ReviewsAdapter(getAction(), moviesModelReview,DetailsActivity.this);

                    recyclerView_Reviews.setAdapter(reviewsAdapter);
                    recyclerView_Reviews.setHasFixedSize(true);
                    recyclerView_Reviews.setLayoutManager(layoutManager);
                }catch (Exception e)
                {

                }
            }
        }
    }
    /*--------------------------------------------------------------------------------*/
}

