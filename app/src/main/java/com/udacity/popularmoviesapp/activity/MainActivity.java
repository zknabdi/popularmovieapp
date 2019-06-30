package com.udacity.popularmoviesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.model.MovieResults;
import com.udacity.popularmoviesapp.service.GetMovieDataService;
import com.udacity.popularmoviesapp.service.MovieListClickListener;
import com.udacity.popularmoviesapp.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static int currentMenuMode =1; //determines the current status of the activity(by popular or top rated)

    private static final String DB_API_KEY = "d23914181a70b399fef78701d2e07cb3";
    private static int numMoviePage; //ever four poster occupies a page
    private static int INITIAL_PAGE = 1;
    private MovieAdapter movieAdapter;
    private Call<MovieResults> movieResultsCall;
    private RecyclerView recyclerView;
    private List<Movie> movieList;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.rv_movies);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return 1;
            }
        });
       recyclerView.setLayoutManager(gridLayoutManager);

       loadMoviePage(INITIAL_PAGE);

    }

    private void loadMoviePage(final int initialPage) {

        GetMovieDataService movieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        /*if(currentMenuMode ==1){
            movieResultsCall = movieDataService.getMoviesByPopularity(initialPage, DB_API_KEY);
        }else if(initialPage ==2){
            movieResultsCall = movieDataService.getMoviesByTopRated(initialPage, DB_API_KEY);
        }*/

        switch (currentMenuMode){
            case 1:
                movieResultsCall = movieDataService.getMoviesByPopularity(initialPage, DB_API_KEY);
                break;
            case 2:
                movieResultsCall = movieDataService.getMoviesByTopRated(initialPage, DB_API_KEY);
                break;
        }
        movieResultsCall.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if(initialPage ==1){
                    movieList= response.body().getMovieList();
                    numMoviePage = response.body().getTotalPages();

                    movieAdapter = new MovieAdapter(movieList, new MovieListClickListener() {
                        @Override
                        public void onMovieListClick(Movie movie) {
                            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("movie",movie);
                            intent.putExtras(bundle);
                            startActivity(intent);

/*                            intent.putExtra(MovieActivity.EXTRA_POSITION, position);
                            startActivity(intent);*/
                        }
                    });
                    recyclerView.setAdapter(movieAdapter);

                }//end if
                else{
                    List<Movie> movies = response.body().getMovieList();
                    for(Movie movie: movies){
                        movieList.add(movie);
                        movieAdapter.notifyItemInserted(movieList.size() -1);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {

                Toast.makeText(MainActivity.this, "There's something wrong... try again", Toast.LENGTH_SHORT).show();
            }
        });

    }//end load

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sort_by_pop_movies:
                currentMenuMode = 1;
                break;
            case R.id.sort_by_top_movies:
                currentMenuMode = 2;
                break;

        }//end switch
        // TODO: Add loading page here'
        loadMoviePage(INITIAL_PAGE);
        Toast.makeText(this,"Sorting by: "+item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
