package com.frbentes.omdbchallenge.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frbentes.omdbchallenge.R;
import com.frbentes.omdbchallenge.entity.MovieTable;
import com.frbentes.omdbchallenge.service.dto.MovieDetail;
import com.frbentes.omdbchallenge.util.ImageUtil;
import com.frbentes.omdbchallenge.view.MovieRemovedListener;
import com.frbentes.omdbchallenge.view.activity.HomeActivity;
import com.frbentes.omdbchallenge.view.activity.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frbentes on 14/01/17.
 */
public class MovieSavedAdapter extends RecyclerView.Adapter<MovieSavedViewHolder> {

    private List<MovieTable> moviesList = new ArrayList<>();
    private Context context;
    private MovieRemovedListener listener;

    @Override
    public MovieSavedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item_movie_saved, parent, false);
        return new MovieSavedViewHolder(view);
    }

    public void setListener(MovieRemovedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final MovieSavedViewHolder holder, final int position) {
        MovieTable movie = this.moviesList.get(position);

        final MovieDetail movieDetail = new MovieDetail();
        movieDetail.setImdbID(movie.imdbId);
        movieDetail.setTitle(movie.title);
        movieDetail.setYear(movie.year);
        movieDetail.setRuntime(movie.runTime);
        movieDetail.setGenre(movie.genre);
        movieDetail.setDirector(movie.director);
        movieDetail.setActors(movie.actors);
        movieDetail.setPlot(movie.plot);
        movieDetail.setAwards(movie.awards);
        movieDetail.setImgPoster(movie.poster);

        holder.tvTitle.setText(movieDetail.title);
        holder.tvGenre.setText(movieDetail.genre);
        holder.tvDuration.setText(movieDetail.runtime);

        holder.ivPoster.setImageBitmap(ImageUtil.convertBytesToBitmap(movieDetail.imgPoster));

        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.KEY_MOVIE_DETAIL, movieDetail);
                intent.putExtra(MovieDetailActivity.KEY_SAVED_MOVIE, true);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((HomeActivity) context, holder.ivPoster, "poster");
                context.startActivity(intent, options.toBundle());
            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieTable movie = MovieTable.listAll(MovieTable.class).get(position);
                movie.delete();
                listener.onMovieRemoved();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.moviesList.size();
    }

    @Override
    public void onViewRecycled(MovieSavedViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public void setData(List<MovieTable> items) {
        this.moviesList = items;
    }

}
