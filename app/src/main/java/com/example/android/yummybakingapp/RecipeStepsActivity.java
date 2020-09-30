package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;

import android.os.Bundle;

import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeDetailFragment;
import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Recipes;


import com.example.android.yummybakingapp.widget.RecipeWidgetService;



public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepsClickListener  {



private Recipes recipes;

private boolean mTwoPane;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);


        //Intent for recipe data

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        // Using getParcelableExtra(String key) method
        if (intent.hasExtra(getResources().getString(R.string.intent_key_recipes))) {
            recipes = intent.getParcelableExtra(getResources().getString(R.string.intent_key_recipes));
        }



        if (recipes == null) {
            // Movie data unavailable
            closeOnError();
            return;
        }

        // Determine if you're creating a two-pane or single-pane display

       if(findViewById(R.id.recipe_detail_fragment) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            Bundle bundle = new Bundle();
            if (recipes != null) {
                bundle.putParcelable(getResources().getString(R.string.intent_key_recipes), recipes);

            }

        } else {
            mTwoPane = false;


        }

        //Pass data to the RecipeStepsFragment to Show Recipe Steps, Single-Pane
        Bundle bundle = new Bundle();
        if (recipes != null) {
            bundle.putParcelable(getResources().getString(R.string.intent_key_recipes), recipes);

        }
        RecipeStepsFragment detailFragment = new  RecipeStepsFragment();
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_steps_fragment, detailFragment).commit();

        RecipeWidgetService.startActionShowRecipes(getApplicationContext());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //Define the behavior of onStepSelected
    @Override
    public void onStepSelected(int position) {
        //*****Handle Communication Between Fragments*****//


        // Handle the two-pane case and replace existing fragments
        if (mTwoPane) {
           // Create two=pane interaction

            Bundle bundle = new Bundle();
            // Put this information in a Bundle and attach it to an Intent that will launch an RecipeDetailFragment
            bundle.putParcelable(getResources().getString(R.string.intent_key_recipes), recipes);
            bundle.putInt(getResources().getString(R.string.intent_key_steps_position), position);

            RecipeDetailFragment stepinstructionFragment = new RecipeDetailFragment();
            stepinstructionFragment.setArguments(bundle);

            //Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_detail_fragment, stepinstructionFragment)
                    .commit();

        } else {

        // Put this information in a Bundle and attach it to an Intent that will launch an RecipeDetailActivity
        Bundle b = new Bundle();
        b.putParcelable(getResources().getString(R.string.intent_key_recipes), recipes);


        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, RecipeDetailActivity.class);

        intent.putExtras(b);
        intent.putExtra(getResources().getString(R.string.intent_key_steps_position), position);
        startActivity(intent);
    }
}}
