package com.frbentes.omdbchallenge.service;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.frbentes.omdbchallenge.service.dto.Movie;
import com.frbentes.omdbchallenge.service.dto.MovieResult;

import java.io.IOException;

/**
 * Created by frbentes on 11/01/17.
 */
public class RetrofitLoader extends AsyncTaskLoader<LookupService.Result> {
    private static final String TAG = "RetrofitLoader";

    private final String mTitle;
    private LookupService.Result mData;

    public RetrofitLoader(Context context, String title) {
        super(context);
        this.mTitle = title;
    }

    @Override
    public LookupService.Result loadInBackground() {
        try {
            MovieResult movieResult =  LookupService.searchByTitle(mTitle);
            LookupService.Result dataResult = new LookupService.Result(movieResult);
            if (movieResult.moviesList != null) {
                for (Movie movie: movieResult.moviesList) {
                    dataResult.addToList(LookupService.searchById(movie.imdbID));
                }
            }
            return dataResult;
        } catch(final IOException e) {
            Log.e(TAG, "Error from api access", e);
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(this.mData);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        this.mData = null;
    }

    @Override
    public void deliverResult(LookupService.Result data) {
        if (isReset()) {
            return;
        }

        LookupService.Result oldData = this.mData;
        this.mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

}
