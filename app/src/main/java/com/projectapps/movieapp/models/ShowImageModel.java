package com.projectapps.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowImageModel implements Parcelable {
    @SerializedName("file_path")
    @Expose
    private String image_url;

    public String getImage_url() {
        return image_url;
    }
    protected ShowImageModel(Parcel in) {
        image_url = in.readString();
    }

    public static final Creator<ShowImageModel> CREATOR = new Creator<ShowImageModel>() {
        @Override
        public ShowImageModel createFromParcel(Parcel in) {
            return new ShowImageModel(in);
        }

        @Override
        public ShowImageModel[] newArray(int size) {
            return new ShowImageModel[size];
        }
    };


    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(image_url);
    }
}
