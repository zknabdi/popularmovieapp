package com.udacity.popularmoviesapp.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.udacity.popularmoviesapp.R;

public class MovieViewHolder  extends RecyclerView.ViewHolder {
     public ImageView imMoviePoster;
     public CardView cvMovieCard;


    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imMoviePoster = itemView.findViewById(R.id.im_movie_poster);
        cvMovieCard = itemView.findViewById(R.id.cv_movie_card);
    }


}
