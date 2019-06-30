package com.udacity.popularmoviesapp.holder;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.service.ListItemClickListener;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CardView mMoveCardV;
    private ImageView mMoviePoster;
    private MovieAdapter movieAdapter;
    private static String imagePathUrl = "https://image.tmdb.org/t/p/" +"w500";

    public MovieViewHolder(View itemView) {
        super(itemView);
        mMoveCardV = (CardView) itemView.findViewById(R.id.cv_movie_card);
        mMoviePoster = (ImageView) itemView.findViewById(R.id.im_movie_poster);

        itemView.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {

        int clickedMoviePosition = getAdapterPosition();
        movieAdapter.mOnClickListener.onListItemClick(clickedMoviePosition);
    }

    public void bind(Movie myMovie, ListItemClickListener mOnClickListener) {
        mMoveCardV.setLayoutParams(new ViewGroup.LayoutParams(getScreenSizeWidth()/2, getImageHeight(getScreenSizeWidth()/2)));
        Picasso.with(mMoviePoster.getContext()).load(moviePath(myMovie.getPosterPath()))
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(mMoviePoster);
    }

    private String moviePath(String posterPath) {
        return imagePathUrl +posterPath;
    }

    private int getImageHeight(int height) {
        return (int) (height*1.5f);
    }

    private int getScreenSizeWidth(){

        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
