package com.frbentes.omdbchallenge.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.frbentes.omdbchallenge.R;
import com.frbentes.omdbchallenge.entity.MovieTable;
import com.frbentes.omdbchallenge.service.dto.MovieDetail;
import com.frbentes.omdbchallenge.util.CoreUtil;
import com.frbentes.omdbchallenge.util.ImageUtil;
import com.frbentes.omdbchallenge.view.activity.MovieSearchActivity;
import com.frbentes.omdbchallenge.view.activity.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frbentes on 12/01/17.
 */
public class MovieFoundAdapter extends RecyclerView.Adapter<MovieFoundViewHolder> {

    private List<MovieDetail> moviesList = new ArrayList<>();
    private Context context;

    @Override
    public MovieFoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item_movie, parent, false);
        return new MovieFoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieFoundViewHolder holder, int position) {
        final MovieDetail movieDetail = this.moviesList.get(position);
        holder.tvTitle.setText(movieDetail.title);
        holder.tvDirector.setText(movieDetail.director);
        holder.tvYear.setText(movieDetail.year);

        final String posterUrl;
        if (!movieDetail.poster.equals("N/A")) {
            posterUrl = movieDetail.poster;
        } else {
            posterUrl = CoreUtil.NO_PICTURE_URL;
        }

        holder.ivPoster.layout(0, 0, 0, 0);
        ImageUtil.loadImage(this.context, posterUrl, R.drawable.film, holder.ivPoster,
                new ImageUtil.OnLoadImageListener() {
            @Override
            public void onImageLoadSuccess(Bitmap resource) {
                movieDetail.setImgPoster(ImageUtil.getBytesFromBitmap(resource));
            }

            @Override
            public void onImageLoadFail() {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.film);
                movieDetail.setImgPoster(ImageUtil.getBytesFromBitmap(bitmap));
            }
        });
        holder.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.KEY_MOVIE_DETAIL, movieDetail);
                intent.putExtra(MovieDetailActivity.KEY_POSTER_URL, posterUrl);

                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((MovieSearchActivity) context, holder.ivPoster,
                                "poster");
                context.startActivity(intent, options.toBundle());
            }
        });
        holder.rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieTable movie = new MovieTable(movieDetail.imdbID, movieDetail.title,
                        movieDetail.year, movieDetail.runtime, movieDetail.genre,
                        movieDetail.director, movieDetail.actors, movieDetail.plot,
                        movieDetail.awards, movieDetail.imgPoster);
                movie.save();

                holder.rlAdd.setBackgroundColor(ContextCompat.getColor(context, R.color.table_caption_text));
                holder.rlAdd.setClickable(false);
                holder.tvAdd.setText(context.getString(R.string.item_saved));
                holder.tvAdd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tag_check, 0, 0, 0);
            }
        });

        List<MovieTable> list = MovieTable.find(MovieTable.class, "imdb_id = ?", movieDetail.imdbID);
        if (list.size() > 0) {
            holder.rlAdd.setBackgroundColor(ContextCompat.getColor(context, R.color.table_caption_text));
            holder.rlAdd.setClickable(false);
            holder.tvAdd.setText(context.getString(R.string.item_saved));
            holder.tvAdd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tag_check, 0, 0, 0);
        } else {
            holder.rlAdd.setBackgroundColor(ContextCompat.getColor(context, R.color.accent));
            holder.rlAdd.setClickable(true);
            holder.tvAdd.setText(context.getString(R.string.item_add));
            holder.tvAdd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tag_add, 0, 0, 0);
        }
    }

    @Override
    public int getItemCount() {
        return this.moviesList.size();
    }

    @Override
    public void onViewRecycled(MovieFoundViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.clear(holder.ivPoster);
    }

    public void setData(List<MovieDetail> items) {
        this.moviesList = items;
    }

}
