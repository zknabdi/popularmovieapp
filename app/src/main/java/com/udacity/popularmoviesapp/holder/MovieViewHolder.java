package com.udacity.popularmoviesapp.holder;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.adapter.MovieAdapter;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.service.MovieListClickListener;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private CardView mMoveCardV;
    private ImageView mMoviePoster;
    private MovieAdapter movieAdapter;
    private static String imagePathUrl = "https://image.tmdb.org/t/p/" + "w500";

    public MovieViewHolder(View itemView) {
        super(itemView);
        mMoveCardV = (CardView) itemView.findViewById(R.id.cv_movie_card);
        mMoviePoster = (ImageView) itemView.findViewById(R.id.im_movie_poster);


    }


//    @Override
//    public void onClick(View v) {
//
//        int clickedMoviePosition = getAdapterPosition();
//        movieAdapter.mOnClickListener.onMovieListClick(clickedMoviePosition);
//    }

    public void bind(final Movie myMovie, final MovieListClickListener mOnClickListener) {
        mMoveCardV.setLayoutParams(new ViewGroup.LayoutParams(getScreenSizeWidth() / 2, getImageHeight(getScreenSizeWidth()/2)));
        Picasso.with(mMoviePoster.getContext()).load(moviePath(myMovie.getPosterPath()))
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .into(mMoviePoster);
       // moveSetClickListener(itemView, mOnClickListener, myMovie);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mOnClickListener.onMovieListClick(myMovie);
            }
        });
    }

//    private void moveSetClickListener(final View itemView, final MovieListClickListener movieListClickListener, final Movie movie) {
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                movieListClickListener.onMovieListClick(movie);
//            }
//        });
//    }

    private String moviePath(String posterPath) {
        return imagePathUrl + posterPath;
    }

    private int getImageHeight(int height) {
        return (int) (height * 1.5f);
    }

    private int getScreenSizeWidth() {

        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
