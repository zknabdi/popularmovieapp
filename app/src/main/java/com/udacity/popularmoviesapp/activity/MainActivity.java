package com.udacity.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.model.MovieResults;
import com.udacity.popularmoviesapp.service.GetMovieDataService;
import com.udacity.popularmoviesapp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String DB_API_KEY = "";
    private static int currentStatusMenu = 1; //determines the current status of the activity(by popular or top rated)
    private ArrayList<Movie> favouriteMovies;
    private Call<MovieResults> call;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieAdapter movieAdapter;
    private GetMovieDataService service;
    private Toolbar movieToolbar;
    private TextView mToolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieToolbar = findViewById(R.id.movie_toolbar);
        setSupportActionBar(movieToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mToolbarTitle = findViewById(R.id.movie_toolbar_title);

        service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);

        userSelectionLoading(currentStatusMenu);


    }//end onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie_sort_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sort_by_pop_movies:
                currentStatusMenu = 1;
                break;
            case R.id.sort_by_top_movies:
                currentStatusMenu = 2;
                break;
            case R.id.favorite_movies_fb:
                currentStatusMenu = 3;
        }
        Log.i("In side onOptions: ", String.valueOf(currentStatusMenu));
        userSelectionLoading(currentStatusMenu);
        return super.onOptionsItemSelected(item);
    }


    private void userSelectionLoading(int optionSort) {

        switch (optionSort) {
            case 1:
                call = service.getMoviesByPopularity(DB_API_KEY);
                break;
            case 2:
                call = service.getMoviesByTopRated(DB_API_KEY);
                break;
            case 3:
                favouriteMovies = getFavoriteMovies();
                break;

        }

        renderDBPage();
    }//end userSelectionLoading

    private ArrayList<Movie> getFavoriteMovies() {
        return null;
    }

    private void renderDBPage() {
        Log.wtf("URL: ", call.request().url() + "");
        Log.i("In side Render", String.valueOf(currentStatusMenu));
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                generateMoveList(response.body().getMovieList());
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateMoveList(List<Movie> movieList) {
        recyclerView = findViewById(R.id.rv_movies);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter(movieList, this);
        recyclerView.setAdapter(movieAdapter);
    }


}
