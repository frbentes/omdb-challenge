package com.frbentes.omdbchallenge.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frbentes.omdbchallenge.R;

/**
 * Created by frbentes on 12/01/17.
 */
public class MovieFoundViewHolder extends RecyclerView.ViewHolder {

    public View view;
    public ImageView ivPoster;
    public TextView tvTitle;
    public TextView tvDirector;
    public TextView tvYear;
    public RelativeLayout rlAdd;
    public TextView tvAdd;

    public MovieFoundViewHolder(final View view) {
        super(view);

        this.view = view;
        this.ivPoster = (ImageView) view.findViewById(R.id.iv_thumbnail);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.tvDirector = (TextView) view.findViewById(R.id.tv_director);
        this.tvYear = (TextView) view.findViewById(R.id.tv_year);
        this.rlAdd = (RelativeLayout) view.findViewById(R.id.rl_add);
        this.tvAdd = (TextView) view.findViewById(R.id.tv_add);
    }

}
