package com.example.android.yummybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.yummybakingapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipes implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredients> ingredients;
    @SerializedName("steps")
    @Expose
    private List<Steps> steps;
    @SerializedName("servings")
    @Expose
    private int servings;
    @SerializedName("image")
    @Expose
    private String image;

    public Recipes(){}

    protected Recipes(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();
        this.ingredients = new ArrayList<>();
        in.readTypedList(this.ingredients, Ingredients.CREATOR);
        this.steps = new ArrayList<>();
        in.readTypedList(this.steps, Steps.CREATOR);
    }

    public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String AllIngredient(){
        String AllIngredients =ingredients.get(0).string();
        for (int i=1;i<ingredients.size();i++){
            AllIngredients= AllIngredients+"\n"+ingredients.get(i).string();
        }
        return AllIngredients;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeString(image);
        parcel.writeTypedList(ingredients);
        parcel.writeTypedList(steps);
    }

//    public static final List<Recipes> ITEMS = new ArrayList<Recipes>();
//
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Recipes createFromParcel(Parcel in) {
//            return new Recipes(in);
//        }
//
//        public Recipes[] newArray(int size) {
//            return new Recipes[0];
//        }
//    };
//
//    @SerializedName("id")
//    private int id;
//
//    @SerializedName("name")
//    private String name;
//
//    @SerializedName("ingredients")
//    private List<Ingredients> ingredients = new ArrayList<>();
//
//    @SerializedName("steps")
//    private List<Steps> steps = new ArrayList<>();
//
//    @SerializedName("servings")
//    private int servings;
//
//
//
//    /**
//     * No args constructor for use in serialization
//     *
//     * @param recipes
//     */
//    public Recipes (String recipes) {
//    }
//
//    //Regular Constructor
//    public Recipes(int id, String name, List<Ingredients> ingredients,  List<Steps> steps, int servings) {
//        this.id = id;
//        this.name = name;
//        this.ingredients = ingredients;
//        this.steps = steps;
//        this.servings = servings;
//    }
//
//
//    public int getmId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getmName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Ingredients> getmIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<Ingredients> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public List<Steps> getmSteps() {
//        return steps;
//    }
//
//    public void setSteps(List<Steps> steps) {
//        this.steps = steps;
//    }
//
//    public int getmServings() {
//        return servings;
//    }
//
//    public void setmServings(int servings) {
//        this.servings = servings;
//    }
//
//
//
//
//    //Parceling constructor
//    public Recipes(Parcel in) {
//        this.id = in.readInt();
//        this.name= in.readString();
//        this.ingredients = new ArrayList<>();
//        in.readList(ingredients,Ingredients.class.getClassLoader());
//        this.steps = new ArrayList<>();
//        in.readList(steps,Steps.class.getClassLoader());
//        this.servings = in.readInt();
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
//        dest.writeString(this.name);
//        dest.writeList(this.ingredients);
//        dest.writeList(this.steps);
//        dest.writeInt(this.servings);
//
//    }
}




