package com.example.android.yummybakingapp.widget;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Recipes;
import com.google.gson.Gson;


public class Preferences {

    private static SharedPreferences pref;

    public static Recipes loadRecipe(Context context) {
       pref = context.getSharedPreferences(context.getString(R.string.preference_name), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(context.getString(R.string.preference_recipe_list_key), "");
        return gson.fromJson(json, Recipes.class);
    }
}
