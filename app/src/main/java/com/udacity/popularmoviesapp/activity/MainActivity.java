package com.udacity.popularmoviesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popularmoviesapp.R;

public class MainActivity extends AppCompatActivity {

    private static int currentMenuMode =1; //determines the current status of the activity(by popular or top rated)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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

        }
        // TODO: Add loading page here'
        Toast.makeText(this,"Sorting by: "+item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
