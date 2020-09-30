package com.example.android.yummybakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IngredientWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientRemoteViewsFactory(this.getApplicationContext());
    }
}

 class IngredientRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private Recipes recipes;
    private List<Ingredients> ingredients;
    private Gson gson;
    private SharedPreferences pref;
    private Ingredients ingredient;

    public IngredientRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        //Get the id of the most recently chosen recipe id from shared preferences
        SharedPreferences pref= mContext.getSharedPreferences("MyPref",0);
        int recipeId = pref.getInt("recipe_id", 0);

        //If there is a recipe id in preferences, get the recipe ingredients  from preferences
        if(recipeId != 0){
            String json = pref.getString("recipe_list", "");
            Type type = new TypeToken<List<Ingredients>>() {}.getType();
            List<Ingredients> arrayList = gson.fromJson(json, type);
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (ingredients == null)
            return 1; // if the user hasn't specified a recipe, return a message
        else return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget_provider);
//        pref = mContext.getSharedPreferences("MyPref", 0); // 0 - for private mode
//        String json = pref.getString("recipe_list", "");
//       Type type = new TypeToken<List<Ingredients>>() {}.getType();
//        List<Ingredients> arrayList = gson.fromJson(json, type);

        ingredient = ingredients.get(position);

        if (ingredients != null) {



                   StringBuilder stringBuilder = new StringBuilder();
                   for(Ingredients ingredient : ingredients){
            String quantity = String.valueOf(ingredient.getmQuantity());
            String measure = ingredient.getmMeasure();
            String ingredientName = ingredient.getmQuantity();
            String line = "- " + quantity + " " + measure + " " + ingredientName;
            stringBuilder.append( line + "\n");
        }
            remoteViews.setTextViewText(R.id.widget_baking_ingredientlist, stringBuilder.toString());

        } else {
            remoteViews.setTextViewText(R.id.widget_baking_ingredientlist, "Please add a recipe to widget");
        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
