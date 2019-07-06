package com.udacity.popularmoviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.activity.MovieActivity;
import com.udacity.popularmoviesapp.holder.MovieViewHolder;
import com.udacity.popularmoviesapp.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int indexPosition) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.movie_cardview, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int indexPosition) {

        movieViewHolder.cvMovieCard.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth() / 2, getMeasuredPosterHeight(getScreenWidth() / 2)));
        Picasso.with(movieViewHolder.imMoviePoster.getContext()).load(movieImagePathBuilder(movieList.get(indexPosition).getPosterPath()))
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(movieViewHolder.imMoviePoster);

        movieViewHolder.cvMovieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("Title", movieList.get(indexPosition).getTitle());
                intent.putExtra("Thumbnail", movieList.get(indexPosition).getPosterPath());
                intent.putExtra("Released", movieList.get(indexPosition).getReleaseDate());
                intent.putExtra("RateAverage", movieList.get(indexPosition).getVoteAverage());
                intent.putExtra("Description", movieList.get(indexPosition).getOverview());
                context.startActivity(intent);

            }
        });


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
