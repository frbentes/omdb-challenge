package com.frbentes.omdbchallenge.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frbentes.omdbchallenge.R;
import com.frbentes.omdbchallenge.service.dto.MovieDetail;
import com.frbentes.omdbchallenge.util.ImageUtil;
import com.frbentes.omdbchallenge.util.StringUtil;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String KEY_MOVIE_DETAIL = "movie_detail";
    public static final String KEY_POSTER_URL = "poster_url";
    public static final String KEY_SAVED_MOVIE = "is_saved_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        final MovieDetail movieDetail = getIntent().getParcelableExtra(KEY_MOVIE_DETAIL);
        final Boolean isSavedMovie = getIntent().getBooleanExtra(KEY_SAVED_MOVIE, false);

        if (!isSavedMovie) {
            final String posterUrl = getIntent().getStringExtra(KEY_POSTER_URL);
            Glide.with(this).load(posterUrl).into((ImageView) findViewById(R.id.iv_detail_poster));
        } else {
            ((ImageView) findViewById(R.id.iv_detail_poster)).setImageBitmap(ImageUtil
                    .convertBytesToBitmap(movieDetail.getImgPoster()));
        }

        ((TextView) findViewById(R.id.tv_detail_title)).setText(movieDetail.title);
        ((TextView) findViewById(R.id.tv_detail_year)).setText(movieDetail.year);
        ((TextView) findViewById(R.id.tv_detail_genre)).setText(movieDetail.genre);
        ((TextView) findViewById(R.id.tv_detail_duration)).setText(movieDetail.runtime);
        ((TextView) findViewById(R.id.tv_detail_awards)).setText(movieDetail.awards);

        final SpannableString director = StringUtil.formatText(
                getString(R.string.movie_detail_label_director), movieDetail.director);
        ((TextView) findViewById(R.id.tv_detail_director)).setText(director);

        final SpannableString actors = StringUtil.formatText(
                getString(R.string.movie_detail_label_actors), movieDetail.actors);
        ((TextView) findViewById(R.id.tv_detail_actors)).setText(actors);

        final SpannableString plot = StringUtil.formatText(
                getString(R.string.movie_detail_label_plot), movieDetail.plot);
        ((TextView) findViewById(R.id.tv_detail_plot)).setText(plot);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
