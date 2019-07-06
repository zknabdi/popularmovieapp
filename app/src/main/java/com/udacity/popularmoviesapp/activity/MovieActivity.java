package com.udacity.popularmoviesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;

public class MovieActivity extends AppCompatActivity {

   TextView moveTitle, movieReleased, movieRate, movieDesc;
   ImageView moviePoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        moveTitle = findViewById(R.id.movie_title);


        moviePoster = findViewById(R.id.movie_poster_im);
        movieReleased = findViewById(R.id.movie_released_tv);
        movieRate = findViewById(R.id.movie_rate_tv);
        movieDesc = findViewById(R.id.movie_descriptions_tv);


        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        Picasso.with(this).load(MovieAdapter.movieImagePathBuilder(intent.getExtras().getString("Thumbnail"))).into(moviePoster);
        String releasedDate = intent.getExtras().getString("Released");
        float rateAverage = intent.getExtras().getFloat("RateAverage");
        String description = intent.getExtras().getString("Description");


       moveTitle.setText(title);
       movieReleased.setText(releasedDate);
        movieRate.setText(Float.toString(rateAverage));
        movieDesc.setText(description);


    }
}
