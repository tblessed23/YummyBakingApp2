package com.example.android.yummybakingapp.network;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import com.example.android.yummybakingapp.model.Recipes;

import java.io.IOException;
import java.util.List;

public class RecipesLoader extends AsyncTaskLoader<List<Recipes>> {
    List<Recipes> users;
    GetDataService mGetDataService = RetrofitGetData.getRetrofitInstance().create(GetDataService.class);

    public RecipesLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Recipes> loadInBackground() {

        try {
            return mGetDataService.getRecipes().execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void deliverResult(List<Recipes> data) {
        super.deliverResult(data);
        users = data;
    }
}







