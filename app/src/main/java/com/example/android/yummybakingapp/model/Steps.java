package com.example.android.yummybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Steps implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    protected Steps(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnailURL);

    }

//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Steps createFromParcel(Parcel in) {
//            return new Steps(in);
//        }
//
//        public Steps[] newArray(int size) {
//            return new Steps[0];
//        }
//    };
//
//    @SerializedName("id")
//    private int id;
//    @SerializedName("shortDescription")
//    private String shortDescription;
//    @SerializedName("description")
//    private String description;
//    @SerializedName("videoURL")
//    private String videoURL;
//    @SerializedName("thumbnailURL")
//    private String thumbnailURL;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     * @param steps
//     */
//    public Steps(String steps) {
//    }
//
//    //Regular Constructor
//    public Steps(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
//        this.id = id;
//        this.shortDescription = shortDescription;
//        this.description = description;
//        this.videoURL= videoURL;
//        this.thumbnailURL = thumbnailURL;
//
//    }
//
//    public int getmId() {
//        return id;
//    }
//
//    public void setmId(int id) {
//        this.id = id;
//    }
//
//    public String getmShortDescription() {
//        return shortDescription;
//    }
//
//    public void setmShortDescription(String shortDescription) {
//        this.shortDescription= shortDescription;
//    }
//
//    public String getmDescription() {
//        return description;
//    }
//
//    public void setmDescription(String description) {
//        this.description = description;
//    }
//
//    public String getmVideoUrl() {
//        return videoURL;
//    }
//
//    public void setmVideoUrl(String video) {
//        this.videoURL = video;
//    }
//
//    public String getmThumbnailUrl() {
//        return thumbnailURL;
//    }
//
//    public void setmThumbnailUrl(String thumbnail) {
//        this.thumbnailURL = thumbnail;
//    }
//
//
//    //Parceling constructor
//    public Steps(Parcel in) {
//        this.id = in.readInt();
//        this.shortDescription = in.readString();
//        this.description = in.readString();
//        this.videoURL = in.readString();
//        this.thumbnailURL = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.id);
//        dest.writeString(this.shortDescription);
//        dest.writeString(this.description);
//        dest.writeString(this.videoURL);
//        dest.writeString(this.thumbnailURL);
//    }
}


