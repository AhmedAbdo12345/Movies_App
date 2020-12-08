package com.example.movies.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movies.ProgressBar_SplashScreen;
import com.example.movies.R;

public class SplashScreen extends AppCompatActivity {
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        textView=findViewById(R.id.text_splash_screen);
        progressBar=findViewById(R.id.progress_bar);

        progressBar.setMax(100);
        progressBar.setScaleY(1f);
        ProgressBar_Animation();
    }

    public void ProgressBar_Animation(){

        ProgressBar_SplashScreen progressBar_animation=new ProgressBar_SplashScreen(this,progressBar,textView,0,100f);
        progressBar_animation.setDuration(3000);
        progressBar.setAnimation(progressBar_animation);
    }


}
