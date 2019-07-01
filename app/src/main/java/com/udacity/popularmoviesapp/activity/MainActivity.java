package com.udacity.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.model.MovieResults;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private static int currentMenuMode = 1; //determines the current status of the activity(by popular or top rated)

    private static final String DB_API_KEY = "";
    private static int numMoviePage; //ever four poster occupies a page
    private static int INITIAL_PAGE = 1;
    private Call<MovieResults> call;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie_sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Test Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.sort_by_pop_movies:
                return true;
            case R.id.sort_by_top_movies:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
