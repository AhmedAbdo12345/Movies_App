package com.example.movies.Activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Adapters.FavouritAdapter;
import com.example.movies.Adapters.FilmAdapter;
import com.example.movies.Adapters.TrailorsAdapter;
import com.example.movies.Database.Database_movies;
import com.example.movies.Database.Favourit_movies;
import com.example.movies.JSON_API.JsonUtils;
import com.example.movies.MoviesModel;
import com.example.movies.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
public class MainActivity extends AppCompatActivity  implements FilmAdapter.ListItemClickListener,TrailorsAdapter.ListItemClickListener , FavouritAdapter.ListItemClickListener {

    final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    final String API_KEY= "?api_key=37789dc48bb9195cdca528bdbc31ce85";
    String urlJson="https://api.themoviedb.org/3/movie/popular?api_key=37789dc48bb9195cdca528bdbc31ce85";

    private FilmAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private TrailorsAdapter trailorAdapter;
    private FavouritAdapter favouritAdapter;
    TextView textView;
    MoviesModel moviesModel;
    MoviesModel moviesModelTrailor;
    String tag3="bv";
    String tag2="bv2";
    Button button;
    int num;
    Database_movies db;

    private AdView mAdView;

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
////        CharSequence ClickHomeButton = getString(R.string.Click_HomeButton_TO_Exit);
////       Toast.makeText(MainActivity.this, ClickHomeButton, Toast.LENGTH_LONG).show();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FechData fechData=new FechData();
//       // textView=findViewById(R.id.idd);
//        fechData.execute();

        recyclerView = (RecyclerView) findViewById(R.id.RV_Movies);
        //  recyclerView2 = (RecyclerView) findViewById(R.id.trailorMovies);
        db = Database_movies.getInstance(getApplicationContext());
        recyclerView2 = (RecyclerView) findViewById(R.id.RV_Movies);



        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("data")) {
                moviesModel =savedInstanceState.getParcelable("data");
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);

                mAdapter = new FilmAdapter(getAction(), moviesModel);

                recyclerView.setAdapter(mAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);


            }}else {

            FechData ff=new FechData();
            // textView=findViewById(R.id.idd);
            ff.execute();
        }


        /*-----------------Google Services Admob-----------------------*/
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private MainActivity getAction() {
        return MainActivity.this;
    }


    /* ----------------------  Menu List Code--------------------------- */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.SortByPopularity: {
              //  urlJson = "http://api.themoviedb.org/3/movie/popular?api_key=37789dc48bb9195cdca528bdbc31ce85";
                urlJson =BASE_URL + "popular" + API_KEY;
                CharSequence PopularityMassage = getString(R.string.Sorted_Popularity);
                Toast.makeText(this, PopularityMassage, Toast.LENGTH_LONG).show();

                FechData ff=new FechData();
                ff.execute();
                break;
            }
            case R.id.SortByRate: {
                //urlJson = "http://api.themoviedb.org/3/movie/top_rated?api_key=37789dc48bb9195cdca528bdbc31ce85";
                urlJson =BASE_URL + "top_rated" + API_KEY;
                CharSequence RateyMassage = getString(R.string.Sorted_Rate);
                Toast.makeText(this, RateyMassage, Toast.LENGTH_LONG).show();
                FechData f2=new FechData();
                f2.execute();
                break;
            }case R.id.Favourite: {
            favouritLoadData();

            CharSequence FavouriteMassage = getString(R.string.Sorted_Favourite);
            Toast.makeText(this, FavouriteMassage, Toast.LENGTH_LONG).show();

            break;

        }

        }
        return  super.onOptionsItemSelected(item);

    }
    public void favouritLoadData(){

        final LiveData<List<Favourit_movies>> list=  db.taskDao().loadAllTasks();

        list.observe(this, new Observer<List<Favourit_movies>>() {
            @Override
            public void onChanged(@Nullable List<Favourit_movies> taskEntries) {


                CharSequence loadDataMassage= getString(R.string.loadDatasuccefuly);
                Toast.makeText(MainActivity.this, loadDataMassage, Toast.LENGTH_LONG).show();

                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                favouritAdapter=new FavouritAdapter(getAction(),taskEntries);

                recyclerView2.setAdapter(favouritAdapter);
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setLayoutManager(layoutManager);

            }
        });

    }
    /* ----------------------  ------------------------------- */
    /*-----------------------Recycle view onclick--------------------------*/

    @Override
    public void onClick(MoviesModel model, int position) {


        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("poster_path" , model.getPoster_path().get(position));
        intent.putExtra("original_title" , model.getOriginal_title().get(position));
        intent.putExtra("vote_count" , model.getVote_count().get(position));
        intent.putExtra("release_date" , model.getRelease_date().get(position));
        intent.putExtra("overview" , model.getOverview().get(position));
        intent.putExtra("id" , model.getMovie_id().get(position));
        startActivity(intent);

    }

    @Override
    public void onClick(List<Favourit_movies> model, int position) {

    }

    /*-------------------------------------------------*/
    public class FechData extends AsyncTask<URL, Void, String> {
        String data = "";

        @Override
        protected String doInBackground(URL... urls) {

            try {

                URL url = new URL(urlJson);
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
            return data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (data != null && !data.equals("")) {
                try {

                    JsonUtils jsonUtils = new JsonUtils();
                    moviesModel = jsonUtils.parseMoviesModelJson(s);
                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);

                    mAdapter = new FilmAdapter(getAction(), moviesModel);

                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);


                    // textView.setText(moviesModel.getPoster_path().get(0));
                } catch (Exception e) {
                    //  textView.setText(e.getMessage());

                }


            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("data",moviesModel);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        moviesModel=savedInstanceState.getParcelable("data");


    }
}
