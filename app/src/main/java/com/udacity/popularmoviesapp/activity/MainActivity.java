package com.udacity.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.model.MovieResults;
import com.udacity.popularmoviesapp.service.GetMovieDataService;
import com.udacity.popularmoviesapp.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        GetMovieDataService service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);

        Call<MovieResults> call = service.getMoviesByPopularity(DB_API_KEY);
        Log.wtf("URL: ",call.request().url()+"");

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
        recyclerView = (RecyclerView)findViewById(R.id.rv_movies);
        movieAdapter = new MovieAdapter(movieList);

       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(movieAdapter);
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
