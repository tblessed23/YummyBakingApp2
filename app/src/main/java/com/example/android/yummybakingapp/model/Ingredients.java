package com.example.android.yummybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class Ingredients implements Parcelable {


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        public Ingredients[] newArray(int size) {
            return new Ingredients[0];
        }
    };

    @SerializedName("quantity")
    private String quantity;
    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;



    /**
     * No args constructor for use in serialization
     *
     * @param ingredients
     */
    public Ingredients(String ingredients) {
    }

    //Regular Constructor
    public Ingredients(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;

    }

    public String getmQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getmMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure= measure;
    }

    public String getmIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }




    //Parceling constructor
    public Ingredients(Parcel in) {
        this.quantity= in.readString();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }

    @Override
    public String toString() {
        return  "- " + quantity + measure + " "
                 + ingredient + "\n";

    }
}



