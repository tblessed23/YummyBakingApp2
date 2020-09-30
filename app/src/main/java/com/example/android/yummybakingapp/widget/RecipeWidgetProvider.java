package com.example.android.yummybakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.RecipeDetailActivity;
import com.example.android.yummybakingapp.RecipeListActivity;
import com.example.android.yummybakingapp.RecipeStepsActivity;

/**
 * Implementation of App Widget functionality. Defines the basic methods that allow you to programmatically
 * interface with the App Widget, based on broadcast events.
 * Through it, you will receive broadcasts when the App Widget is updated, enabled, disabled and deleted.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = getIngredientsGridRemoteView(context);

        //Get the id and name of the last chosen recipe from the preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.preference_name), Context.MODE_PRIVATE);
        ;
        String recipeName = sharedPreferences.getString(String.valueOf(R.string.preference_recipe_name_key), "");
        views.setTextViewText(R.id.widget_baking_title, recipeName);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    //***This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute
    // in the AppWidgetProviderInfo (see Adding the AppWidgetProviderInfo Metadata above). This method is
    // also called when the user adds the App Widget, so it should perform the essential setup, such as
    // define event handlers for Views and start a temporary Service, if necessary. ****

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Start the intent service update widget action, the service takes care of updating the widgets UI
        RecipeWidgetService.startActionShowRecipes(context);
    }

    /**
     * Updates all widget instances given the widget Ids and display information
     *
     * @param context          The calling context
     * @param appWidgetManager The widget manager
     * @param appWidgetIds     Array of widget Ids to be updated
     */
    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static RemoteViews getIngredientsGridRemoteView(Context context){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

        // Set the IngredientWidgetService intent to act as the adapter for the GridView
        Intent intent = new Intent(context, IngredientWidgetService.class);
        views.setRemoteAdapter(R.id.widget_layout, intent);

        // Set the PlantDetailActivity intent to launch when clicked
        Intent appIntent = new Intent(context, RecipeListActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_layout, appPendingIntent);

        // Handle empty gardens
        views.setEmptyView(R.id.widget_layout,R.id.widget_default_text);

        return views;
    }


}

