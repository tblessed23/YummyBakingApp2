package com.example.android.yummybakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Ingredients;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class RecipeWidgetService extends IntentService {

    private Context mContext;
    private static List<Ingredients> ingredientsList;

    public static final String ACTION_SHOW_RECIPES = "com.example.android.yummybakingapp.action.show_recipes";

    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }



    @Override
    protected void onHandleIntent(@NonNull Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SHOW_RECIPES.equals(action)) {
                handleActionShowRecipes();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void startActionShowRecipes(Context context) {
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.setAction(ACTION_SHOW_RECIPES);
        context.startService(intent);
    }


    private void handleActionShowRecipes() {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_layout);

        //Now update all widgets
        RecipeWidgetProvider.updateRecipeWidgets(this, appWidgetManager, appWidgetIds);
    }
}


