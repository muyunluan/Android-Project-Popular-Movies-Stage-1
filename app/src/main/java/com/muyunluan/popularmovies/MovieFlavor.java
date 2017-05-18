package com.muyunluan.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fei Deng on 5/12/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieFlavor implements Parcelable {
    private String mTitle;
    private String mImageSource;
    private String mSynopsis; //overview
    private Double mRating;
    private String mReleaseDate;
    private int    mImage;

    public MovieFlavor(String mTitle, String mImageSource, String mSynopsis, Double mRating, String mReleaseDate) {
        this.mTitle = mTitle;
        this.mImageSource = mImageSource;
        this.mSynopsis = mSynopsis;
        this.mRating = mRating;
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public String toString() {
        return "MovieFlavor{" +
                "mTitle='" + mTitle + '\'' +
                ", mImageSource='" + mImageSource + '\'' +
                ", mSynopsis='" + mSynopsis + '\'' +
                ", mRating=" + mRating +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mImage=" + mImage +
                '}';
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImageSource() {
        return mImageSource;
    }

    public void setmImageSource(String mImageSource) {
        this.mImageSource = mImageSource;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }

    public void setmSynopsis(String mSynopsis) {
        this.mSynopsis = mSynopsis;
    }

    public Double getmRating() {
        return mRating;
    }

    public void setmRating(Double mRating) {
        this.mRating = mRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mImageSource);
        dest.writeString(this.mSynopsis);
        dest.writeDouble(this.mRating);
        dest.writeString(this.mReleaseDate);
    }

    public static final Parcelable.Creator<MovieFlavor> CREATOR = new Parcelable.Creator<MovieFlavor>() {

        @Override
        public MovieFlavor createFromParcel(Parcel source) {
            return new MovieFlavor(source);
        }

        @Override
        public MovieFlavor[] newArray(int size) {
            return new MovieFlavor[size];
        }
    };

    private MovieFlavor(Parcel in) {
        this.mTitle = in.readString();
        this.mImageSource = in.readString();
        this.mSynopsis = in.readString();
        this.mRating = in.readDouble();
        this.mReleaseDate = in.readString();
    }
}
