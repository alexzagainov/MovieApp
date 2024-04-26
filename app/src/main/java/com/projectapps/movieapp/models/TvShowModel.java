package com.projectapps.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowModel implements Parcelable {
    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("id")
    @Expose
    private int show_id;
    @SerializedName("vote_average")
    @Expose
    private double vote_average;
    @SerializedName("overview")
    @Expose
    private String show_overview;

    protected TvShowModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        show_id = in.readInt();
        vote_average = in.readFloat();
        show_overview = in.readString();
    }

    public static final Parcelable.Creator<TvShowModel> CREATOR = new Parcelable.Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel in) {
            return new TvShowModel(in);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getShow_id() {
        return show_id;
    }

    public double getVote_avarage() {
        return vote_average;
    }

    public String getShow_overview() {
        return show_overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeInt(show_id);
        dest.writeDouble(vote_average);
        dest.writeString(show_overview);
    }
}
