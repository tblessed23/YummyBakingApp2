package com.example.android.yummybakingapp.widget;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Preferences {

    private static SharedPreferences pref;


    public static Recipes loadRecipe(Context context) {

        pref = context.getSharedPreferences("prefs", 0); // 0 - for private mode
        Gson gson = new Gson();
        String json = pref.getString("recipe_list", "");
        Type type = new TypeToken<List<Ingredients>>() {}.getType();
        return gson.fromJson(json, Recipes.class);


    }
}
