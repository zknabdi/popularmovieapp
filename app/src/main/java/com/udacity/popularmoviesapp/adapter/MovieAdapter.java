package com.udacity.popularmoviesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popularmoviesapp.R;
import com.udacity.popularmoviesapp.holder.MovieViewHolder;
import com.udacity.popularmoviesapp.model.Movie;
import com.udacity.popularmoviesapp.service.ListItemClickListener;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {


    private static final String TAG = MovieAdapter.class.getSimpleName();

    private List<Movie> moveList;
    public ListItemClickListener mOnClickListener;

    public MovieAdapter(List<Movie> moveList, ListItemClickListener mOnClickListener) {
        this.moveList = moveList;
        this.mOnClickListener = mOnClickListener;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewTypeIndex) {
        Context context = viewGroup.getContext();
       int layoutID = R.layout.movie_cardview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToViewGroupImmediately = false;

        View view = inflater.inflate(layoutID, viewGroup,shouldAttachToViewGroupImmediately);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int indexPosition) {

       Movie myMovie = this.moveList.get(indexPosition);
       movieViewHolder.bind(myMovie, mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return this.moveList.size();
    }
}
