package com.udacity.popularmoviesapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;

public class MovieActivity extends AppCompatActivity {

   TextView moveTitle, movieReleased, movieRate, movieDesc;
   ImageView moviePoster;
    Toolbar toolbar;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        moveTitle = findViewById(R.id.movie_title);


        moviePoster = findViewById(R.id.movie_poster_im);
        movieReleased = findViewById(R.id.movie_released_tv);
        movieRate = findViewById(R.id.movie_rate_tv);
        movieDesc = findViewById(R.id.movie_descriptions_tv);
        toolbar = findViewById(R.id.movie_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//               onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
}
