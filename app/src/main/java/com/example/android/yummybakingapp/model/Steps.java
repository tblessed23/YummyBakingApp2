package com.example.android.yummybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Steps implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        public Steps[] newArray(int size) {
            return new Steps[0];
        }
    };

    @SerializedName("id")
    public static int id;
    @SerializedName("shortDescription")
    public static String shortDescription;
    @SerializedName("description")
    public static String description;
    @SerializedName("videoURL")
    public static String videoURL;
    @SerializedName("thumbnailURL")
    public static String thumbnailURL;

    /**
     * No args constructor for use in serialization
     *
     * @param steps
     */
    public Steps(String steps) {
    }

    //Regular Constructor
    public Steps(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL= videoURL;
        this.thumbnailURL = thumbnailURL;

    }

    public int getmId() {
        return id;
    }

    public void setmId(int id) {
        this.id = id;
    }

    public String getmShortDescription() {
        return shortDescription;
    }

    public void setmShortDescription(String shortDescription) {
        this.shortDescription= shortDescription;
    }

    public String getmDescription() {
        return description;
    }

    public void setmDescription(String description) {
        this.description = description;
    }

    public String getmVideoUrl() {
        return videoURL;
    }

    public void setmVideoUrl(String video) {
        this.videoURL = video;
    }

    public String getmThumbnailUrl() {
        return thumbnailURL;
    }

    public void setmThumbnailUrl(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }






    //Parceling constructor
    public Steps(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoURL = in.readString();
        this.thumbnailURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);
        dest.writeString(this.thumbnailURL);
    }
}


