package com.example.android.yummybakingapp.widget;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Recipes;
import com.google.gson.Gson;

public class Preferences {

    public static final String PREFS_NAME = "prefs";

    public static void saveRecipe(Context context, Recipes recipe) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        prefs.putString(context.getString(R.string.preference_recipe_list_key), json);
        prefs.apply();
    }

    public static Recipes loadRecipe(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(context.getString(R.string.preference_recipe_list_key), "");
        return gson.fromJson(json, Recipes.class);


    }
}
