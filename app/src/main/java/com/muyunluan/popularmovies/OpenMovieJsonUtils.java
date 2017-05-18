package com.muyunluan.popularmovies;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 5/16/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public final class OpenMovieJsonUtils {

    private static final String TAG = OpenMovieJsonUtils.class.getSimpleName();

    public static ArrayList<MovieFlavor> getMovieStringsFromJson(Context context, String moiveJsonStr) throws JSONException {
        final String KEY_RESULTS = "results";

        final String KEY_ORIGINAL_TITILE = "original_title";
        final String KEY_POSTER_IMAGE_THUMBNAIL = "poster_path";
        final String KEY_SYNOPSIS = "overview";
        final String KEY_RATING = "vote_average";
        final String KEY_RELEASE_DATE = "release_date";

        JSONObject movieJson = new JSONObject(moiveJsonStr);

        JSONArray resultsArray = movieJson.getJSONArray(KEY_RESULTS);
        int arrLen = resultsArray.length();
        ArrayList<MovieFlavor> parsedMovieData = new ArrayList<>();

        for (int i = 0; i < arrLen; i++) {
            JSONObject movieObject = resultsArray.getJSONObject(i);
            String originalTitleStr = movieObject.getString(KEY_ORIGINAL_TITILE);
            String posterImagStr = movieObject.getString(KEY_POSTER_IMAGE_THUMBNAIL);
            String synopsisStr = movieObject.getString(KEY_SYNOPSIS);
            Double ratingStr = movieObject.getDouble(KEY_RATING);
            String releaseDateStr = movieObject.getString(KEY_RELEASE_DATE);
            MovieFlavor movieFlavor = new MovieFlavor(originalTitleStr, posterImagStr, synopsisStr, ratingStr, releaseDateStr);
            //Log.i(TAG, "getMovieStringsFromJson: MovieFlavor object - " + movieFlavor.toString());
            parsedMovieData.add(i, movieFlavor);
        }
        return parsedMovieData;
    }
}
