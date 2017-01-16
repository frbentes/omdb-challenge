package com.frbentes.omdbchallenge.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frbentes.omdbchallenge.R;

/**
 * Created by frbentes on 14/01/17.
 */
public class MovieSavedViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivPoster;
    public TextView tvTitle;
    public TextView tvGenre;
    public TextView tvDuration;
    public Button btnRemove;
    public RelativeLayout rlContent;

    public MovieSavedViewHolder(final View view) {
        super(view);

        this.ivPoster = (ImageView) view.findViewById(R.id.iv_movie_poster);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_movie_title);
        this.tvGenre = (TextView) view.findViewById(R.id.tv_movie_genre);
        this.tvDuration = (TextView) view.findViewById(R.id.tv_movie_duration);
        this.btnRemove = (Button) view.findViewById(R.id.btn_remove);
        this.rlContent = (RelativeLayout) view.findViewById(R.id.rl_content);
    }

}
