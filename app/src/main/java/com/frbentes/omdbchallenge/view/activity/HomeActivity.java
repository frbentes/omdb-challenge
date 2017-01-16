package com.frbentes.omdbchallenge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.frbentes.omdbchallenge.R;
import com.frbentes.omdbchallenge.entity.MovieTable;
import com.frbentes.omdbchallenge.view.MovieRemovedListener;
import com.frbentes.omdbchallenge.view.adapter.MovieSavedAdapter;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements MovieRemovedListener {

    private RecyclerView rvMovie;
    private TextView tvMessage;
    private MovieSavedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.adapter = new MovieSavedAdapter();
        this.adapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.rvMovie = (RecyclerView) findViewById(R.id.rv_movie_saved);
        this.rvMovie.setLayoutManager(layoutManager);
        this.rvMovie.setAdapter(this.adapter);

        this.tvMessage = (TextView) findViewById(R.id.tv_msg_no_movies);
        FloatingActionButton fabNewMovie = (FloatingActionButton) findViewById(R.id.fab_new_movie);
        fabNewMovie.setOnClickListener(clickNew);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listMovies();
    }

    private void listMovies() {
        long count = MovieTable.count(MovieTable.class);
        if (count > 0) {
            List<MovieTable> movies = MovieTable.listAll(MovieTable.class);
            this.adapter.setData(movies);
            this.adapter.notifyDataSetChanged();
            this.tvMessage.setVisibility(View.GONE);
            this.rvMovie.setVisibility(View.VISIBLE);
        } else {
            this.tvMessage.setVisibility(View.VISIBLE);
            this.rvMovie.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMovieRemoved() {
        listMovies();
    }

    private final View.OnClickListener clickNew = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, MovieSearchActivity.class);
            startActivity(intent);
        }
    };

}
