package com.example.android.yummybakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.RecipeListActivity;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.network.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of App Widget functionality. Defines the basic methods that allow you to programmatically
 * interface with the App Widget, based on broadcast events.
 * Through it, you will receive broadcasts when the App Widget is updated, enabled, disabled and deleted.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

//    private static String ingredientList;
//    private static String recipeName;
//
//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//                                String ingredientsList, String recipeName, int appWidgetId) {
//
//
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
//
//        views.setTextViewText(R.id.widget_baking_title, recipeName);
//        views.setTextViewText(R.id.widget_baking_ingredientlist, ingredientsList);
//
//        if (ingredientsList.isEmpty() && recipeName.isEmpty()) {
//            views.setViewVisibility(R.id.widget_default_text, View.VISIBLE);
//        }
//        else {
//            views.setViewVisibility(R.id.widget_default_text, View.GONE);
//        }
////        StringBuilder stringBuilder = new StringBuilder();
////        for(Ingredients ingredient : ingredientList){
////            String quantity = String.valueOf(ingredient.getmQuantity());
////            String measure = ingredient.getmMeasure();
////            String ingredientName = ingredient.getmQuantity();
////            String line = "- " + quantity + " " + measure + " " + ingredientName;
////            stringBuilder.append( line + "\n");
////        }
//
////        views.setTextViewText(R.id.widget_baking_title, "Cheesecake");
////        views.setTextViewText(R.id.widget_baking_ingredientlist, stringBuilder.toString());
//
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//
//    }
//
//    //***This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute
//    // in the AppWidgetProviderInfo (see Adding the AppWidgetProviderInfo Metadata above). This method is
//    // also called when the user adds the App Widget, so it should perform the essential setup, such as
//    // define event handlers for Views and start a temporary Service, if necessary. ****
//
//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        //Start the intent service update widget action, the service takes care of updating the widgets UI
//        RecipeWidgetService.startActionShowRecipes(context);
//    }
//
//    /**
//     * Updates all widget instances given the widget Ids and display information
//     *
//     * @param context          The calling context
//     * @param appWidgetManager The widget manager
//     * @param ingredientsList           The image resource for single plant mode
//     * @param recipeName         The database ID for that plant
//     * @param appWidgetIds     Array of widget Ids to be updated
//     */
//    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
//                                           String ingredientsList, String recipeName, int[] appWidgetIds) {
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, ingredientsList, recipeName, appWidgetId);
//        }
//    }
//
////    @Override
////    public void onReceive(Context context, Intent intent) {
////        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
////        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidgetProvider.class));
////
////        final String action = intent.getAction();
////
////        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE")) {
////            ingredientList = intent.getStringExtra(Constants.INGREDIENTS_LIST_WIDGET);
////            recipeName = intent.getStringExtra(Constants.RECIPE_NAME_WIDGET);
////            onUpdate(context, appWidgetManager, appWidgetIds);
////            super.onReceive(context, intent);
////        }
////    }
//



}

