package com.udacity.popularmoviesapp.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.holder.MovieViewHolder;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.service.MovieListClickListener;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    // private MovieListClickListener movieListClickListener;



    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int indexPosition) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.movie_cardview, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int indexPosition) {

        movieViewHolder.cvMovieCard.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth()/2, getMeasuredPosterHeight(getScreenWidth()/2)));
        Picasso.with(movieViewHolder.imMoviePoster.getContext()).load(movieImagePathBuilder(movieList.get(indexPosition).getPosterPath()))
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(movieViewHolder.imMoviePoster);



    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private int getMeasuredPosterHeight(int width) {
        return (int) (width * 1.5f);
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static String movieImagePathBuilder(String imagePath) {
        {
            return "https://image.tmdb.org/t/p/" +
                    "w500" +
                    imagePath;
        }


    }
}
