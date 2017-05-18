package com.muyunluan.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieFlavor movieFlavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if (null != getIntent() && getIntent().hasExtra("movie_object")) {
            movieFlavor = getIntent().getParcelableExtra("movie_object");
        }

        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText(movieFlavor.getmTitle());

        ImageView posterImg = (ImageView) findViewById(R.id.img_poster);
        Picasso.with(getApplicationContext()).load(NetworkUtils.buildImageUrlStr(movieFlavor.getmImageSource())).into(posterImg);

        TextView ratingTv = (TextView) findViewById(R.id.tv_rating);
        ratingTv.setText(String.valueOf(movieFlavor.getmRating()));

        TextView releaseTv = (TextView) findViewById(R.id.tv_release_date);
        releaseTv.setText(movieFlavor.getmReleaseDate());

        TextView overviewTv = (TextView) findViewById(R.id.tv_overview);
        overviewTv.setText(movieFlavor.getmSynopsis());

    }
}
