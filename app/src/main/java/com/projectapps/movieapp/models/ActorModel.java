package com.projectapps.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorModel implements Parcelable {
    @SerializedName("original_name")
    @Expose
    private String name;
    @SerializedName("profile_path")
    @Expose
    private String profile_path;

    protected ActorModel(Parcel in) {
        name = in.readString();
        profile_path = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public static final Creator<ActorModel> CREATOR = new Creator<ActorModel>() {
        @Override
        public ActorModel createFromParcel(Parcel in) {
            return new ActorModel(in);
        }

        @Override
        public ActorModel[] newArray(int size) {
            return new ActorModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(profile_path);
    }
}
