package com.example.android.yummybakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.RecipeStepsActivity;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.widget.RecipeWidgetService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {


    SharedPreferences pref;
    private final Context mContext;
    private List<Recipes> mValues;
    private List<Ingredients> mIngredients;


    public void setRecipeData(List<Recipes> recipes) {
        mValues = recipes;
        notifyDataSetChanged();
    }


    public interface ListItemClickListener {
        void onListItemClick(int mSteps);
    }

    /**
     * Constructor for RecipeAdapter that accepts a number of items to display
     and the specification
     * for the ListItemClickListener.
     *
     * @param recipes Items to display in list
     */


    public RecipeAdapter(Context context,
                        ArrayList<Recipes> recipes) {
        mContext = context;
        mValues = recipes;

    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at
     the specified
     * position. In this method, we update the contents of the ViewHolder to
     display the correct
     * indices in the list for this particular position, using the "position"
     argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the
    contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        final Recipes recipe = mValues.get(position);
        TextView textViewAgain = holder.mContentView;
        textViewAgain.setText(recipe.getmName());

        TextView servingsTextView = holder.mServingsView;
        servingsTextView.setText(Integer.toString(recipe.getmServings()));

        holder.firstCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set-Up Shared Preferences
                pref = mContext.getSharedPreferences(String.valueOf(R.string.preference_name), 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                //Store Data: Shared Preferences
                Gson gson = new Gson();

                String json = gson.toJson(recipe.getmIngredients());

                editor.putString(String.valueOf(R.string.preference_recipe_list_key), json);
                editor.putString(String.valueOf(R.string.preference_recipe_name_key), recipe.getmName()); // Storing boolean - true/false
                editor.putInt(String.valueOf(R.string.preference_recipe_id_key), recipe.getmId()); // Storing string
                editor.apply(); // commit changes

                RecipeWidgetService.startActionShowRecipes(mContext);

                Intent intent =  new Intent(mContext, RecipeStepsActivity.class);
                intent.putExtra(mContext.getResources().getString(R.string.intent_key_recipes), recipe);
                mContext.startActivity(intent);



            }
        });
    }


    /**
     * This method simply returns the number of items to display. It is used
     behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our list
     */

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void clear(List<Recipes> data) {
        mValues = data;
        notifyDataSetChanged();
    }


    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final TextView mServingsView;
        final TextView mStepsView;
        final CardView firstCardview;


        RecipeViewHolder(View view) {
            super(view);
            mContentView = view.findViewById(R.id.content);
            mServingsView = view.findViewById(R.id.id_text);
            mStepsView = view.findViewById(R.id.steps);
            firstCardview = view.findViewById(R.id.first_cardview);
        }
    }
    /**
     *
     * This gets called when each new ViewHolder is created. This happens when
     the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and
     allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained
    within.
     * @param viewType  If your RecyclerView has more than one type of item
    (which ours doesn't) you
     *                  can use this viewType integer to provide a different
    layout. See
     *
     *                  for more details.
     * @return A new RecipeViewHolder that holds the View for each list item
     */


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item_list_content;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup,
                shouldAttachToParentImmediately);

        RecipeViewHolder vh = new RecipeViewHolder(view);

        return vh;
    }
}


