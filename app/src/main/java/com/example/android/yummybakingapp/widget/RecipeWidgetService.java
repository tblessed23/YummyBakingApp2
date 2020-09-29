package com.example.android.yummybakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import androidx.annotation.Nullable;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.network.Constants;

import java.util.List;

public class RecipeWidgetService extends IntentService {

    List<Ingredients> ingredientsList;
    String recipeName;
    Recipes recipes;

    public static final String ACTION_SHOW_RECIPES = "com.example.android.yummybakingapp.action.show_recipes";

    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }


    /**
     * Starts this service to perform WaterPlant action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionShowRecipes(Context context) {
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.setAction(ACTION_SHOW_RECIPES);
        context.startService(intent);

    }

//    /**
//     * Starts this service to perform UpdatePlantWidgets action with the given parameters. If
//     * the service is already performing a task this action will be queued.
//     *
//     * @see IntentService
//     */
//    public static void startActionUpdatePlantWidgets(Context context) {
//        Intent intent = new Intent(context, RecipeWidgetService.class);
//        intent.setAction(ACTION_SHOW_RECIPES);
//        context.startService(intent);
//    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SHOW_RECIPES.equals(action)) {
                handleActionShowRecipes();
            }
        }
    }

    private void handleActionShowRecipes() {
        Intent intent = new Intent();

        if (intent != null) {

             recipes = intent.getParcelableExtra("Recipes");
        }

        StringBuilder stringBuilder = new StringBuilder();
        ingredientsList = recipes.getmIngredients();

        for(Ingredients ingredient : ingredientsList){
            String quantity = String.valueOf(ingredient.getmQuantity());
            String measure = ingredient.getmMeasure();
            String ingredientName = ingredient.getmQuantity();
            String line = "- " + quantity + " " + measure + " " + ingredientName;
            stringBuilder.append( line + "\n");
        }
        recipeName = recipes.getmName();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));

        //Now update all widgets
        RecipeWidgetProvider.updateRecipeWidgets(this, appWidgetManager, stringBuilder.toString(), recipeName,appWidgetIds);

    }
}


