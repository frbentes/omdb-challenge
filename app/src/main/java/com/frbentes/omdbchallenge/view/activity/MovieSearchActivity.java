package com.frbentes.omdbchallenge.view.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.frbentes.omdbchallenge.R;
import com.frbentes.omdbchallenge.service.LookupService;
import com.frbentes.omdbchallenge.service.RetrofitLoader;
import com.frbentes.omdbchallenge.service.dto.MovieDetail;
import com.frbentes.omdbchallenge.util.CoreUtil;
import com.frbentes.omdbchallenge.view.adapter.MovieFoundAdapter;

import java.util.ArrayList;

public class MovieSearchActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<LookupService.Result> {

    private static final int LOADER_ID = 1;
    private static final String KEY_MOVIE_TITLE = "movie_title";
    private static final String KEY_PROGRESS_VISIBILITY = "progress_visibility";
    private static final String KEY_TITLE = "title";

    private Button btnSearch;
    private EditText edtTitle;
    private RecyclerView rvMovie;
    private ProgressBar pbHome;

    private MovieFoundAdapter adapter;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        this.edtTitle = (EditText) findViewById(R.id.edt_title);
        this.btnSearch = (Button) findViewById(R.id.btn_search);
        this.rvMovie = (RecyclerView) findViewById(R.id.rv_movie);
        this.pbHome = (ProgressBar) findViewById(R.id.pb_home);

        this.btnSearch.setOnClickListener(clickSearch);
        this.edtTitle.setOnEditorActionListener(actionSearch);

        this.adapter = new MovieFoundAdapter();
        this.rvMovie.setAdapter(adapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(getResources().
                getInteger(R.integer.grid_column_count), StaggeredGridLayoutManager.VERTICAL);
        this.rvMovie.setItemAnimator(null);
        this.rvMovie.setLayoutManager(layoutManager);
        getSupportLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_MOVIE_TITLE, mTitle);
        outState.putInt(KEY_PROGRESS_VISIBILITY, pbHome.getVisibility());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int pbVisibility = savedInstanceState.getInt(KEY_PROGRESS_VISIBILITY);
        if (pbVisibility == View.VISIBLE) {
            pbHome.setVisibility(View.VISIBLE);
        }
        this.mTitle = savedInstanceState.getString(KEY_MOVIE_TITLE);
        if (mTitle != null) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TITLE, this.mTitle);
            getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);
        }
    }

    @Override
    public Loader<LookupService.Result> onCreateLoader(int id, Bundle args) {
        return new RetrofitLoader(MovieSearchActivity.this, args.getString(KEY_TITLE));
    }

    @Override
    public void onLoadFinished(Loader<LookupService.Result> loader, LookupService.Result data) {
        pbHome.setVisibility(View.GONE);
        rvMovie.setVisibility(View.VISIBLE);
        if (data != null) {
            if (data.getResponse().equals("True")) {
                adapter.setData(data.getMovieDetailList());
                adapter.notifyDataSetChanged();
            } else {
                Snackbar.make(rvMovie, getResources().getString(R.string.snackbar_movie_not_found),
                        Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(rvMovie, getResources().getString(R.string.snackbar_data_not_received),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<LookupService.Result> loader) {
        adapter.setData(new ArrayList<MovieDetail>());
        adapter.notifyDataSetChanged();
    }

    private void startSearch() {
        if (CoreUtil.isNetworkAvailable(getApplicationContext())) {
            CoreUtil.hideSoftKeyboard(MovieSearchActivity.this);
            String movieTitle = edtTitle.getText().toString().trim();
            if (!movieTitle.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString(KEY_TITLE, movieTitle);
                getSupportLoaderManager().restartLoader(LOADER_ID, bundle, this);
                this.mTitle = movieTitle;
                this.pbHome.setVisibility(View.VISIBLE);
                this.rvMovie.setVisibility(View.GONE);
            } else {
                Snackbar.make(rvMovie, getResources().getString(R.string.snackbar_empty_title),
                        Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(rvMovie, getResources().getString(R.string.snackbar_connection_failed),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    private final View.OnClickListener clickSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startSearch();
        }
    };

    private final TextView.OnEditorActionListener actionSearch = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
                startSearch();
                handled = true;
            }
            return handled;
        }
    };

}
