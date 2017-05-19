package com.muyunluan.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Fei Deng on 5/12/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private GridView mGridView;
    private MovieFlavorAdapter movieFlavorAdapter;
    private ArrayList<MovieFlavor> retrievedMovieData;

    //TODO: Please update your own API Key
    private static final String YOUR_API_KEY = "";
    private static final String KEY_SAVED_MOVIES = "saved_movies";
    private static final String KEY_MOVIE_OBJET = "movie_object";
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (null != savedInstanceState && savedInstanceState.containsKey(KEY_SAVED_MOVIES)) {
            retrievedMovieData.clear();
            retrievedMovieData = savedInstanceState.getParcelableArrayList(KEY_SAVED_MOVIES);
            updateMovies();
        } else {
            getMovies();
        }

        mGridView = (GridView) view.findViewById(R.id.discovery_grid);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                intent.putExtra(KEY_MOVIE_OBJET, movieFlavorAdapter.getItem(position));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_SAVED_MOVIES, retrievedMovieData);
        super.onSaveInstanceState(outState);
    }

    private void getMovies() {
        new MovieQureyTask(false).execute(YOUR_API_KEY);
    }

    private void updateMovies() {
        movieFlavorAdapter.clear();
        for(MovieFlavor movieFlavor : retrievedMovieData) {
            movieFlavorAdapter.add(movieFlavor);
        }
    }

    public class MovieQureyTask extends AsyncTask<String, Void, ArrayList<MovieFlavor>> {

        private boolean mIsRating = false;
        public MovieQureyTask(boolean isRating) {
            mIsRating = isRating;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<MovieFlavor> doInBackground(String... params) {

            if (0 == params.length) {
                return null;
            }

            String movieUrlStr = params[0];
            URL url = NetworkUtils.buildUrl(movieUrlStr, mIsRating);
            //Log.i(TAG, "doInBackground: get URL - " + url.toString());
            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(url);
                 retrievedMovieData = OpenMovieJsonUtils.getMovieStringsFromJson(getActivity(),jsonMovieResponse);
                return retrievedMovieData;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieFlavor> movieFlavors) {
            if (!movieFlavors.isEmpty()) {
                //Log.i(TAG, "onPostExecute: get MovieFlavors length - " + movieFlavors.size());
                movieFlavorAdapter = new MovieFlavorAdapter(getActivity(), movieFlavors);
                mGridView.setAdapter(movieFlavorAdapter);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_sort_most_popular:
                new MovieQureyTask(false).execute(YOUR_API_KEY);
                return true;
            case R.id.menu_sort_top_rated:
                new MovieQureyTask(true).execute(YOUR_API_KEY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
