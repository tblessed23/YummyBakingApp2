package com.example.android.yummybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.yummybakingapp.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipes extends ArrayList<String> implements Parcelable {

    public static final List<Recipes> ITEMS = new ArrayList<Recipes>();

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        public Recipes[] newArray(int size) {
            return new Recipes[0];
        }
    };

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private List<Ingredients> ingredients;

    @SerializedName("steps")
    private List<Steps> steps;

    @SerializedName("servings")
    private int servings;



    /**
     * No args constructor for use in serialization
     *
     * @param recipes
     */
    public Recipes (String recipes) {
    }

    //Regular Constructor
    public Recipes(int id, String name, List<Ingredients> ingredients,  List<Steps> steps, int servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }


    public int getmId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getmIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getmSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public int getmServings() {
        return servings;
    }

    public void setmServings(int servings) {
        this.servings = servings;
    }




    //Parceling constructor
    public Recipes(Parcel in) {

        ingredients = new ArrayList<Ingredients>();
        steps = new ArrayList<Steps>();

        this.id = in.readInt();
        this.name= in.readString();
        in.readList(ingredients, R.class.getClassLoader());
        in.readList(steps, R.class.getClassLoader());
        this.servings = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeList(this.ingredients);
        dest.writeList(this.steps);
        dest.writeInt(this.servings);

    }
}


