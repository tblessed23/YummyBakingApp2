package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepsClickListener {

private String recipes;
    private List<Recipes> mValues;
    private String recipeJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

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
        Bundle bundle = new Bundle();
        bundle.putString("recipejson", recipes);
        RecipeStepsFragment detailFragment = new  RecipeStepsFragment();
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment, detailFragment).commit();

//        getSupportFragmentManager().beginTransaction()
//              .replace(R.id.detail_fragment, new RecipeStepsFragment())
//                .commit();
    }

//    public static RecipeStepsFragment newInstance(Recipes recipes) {
//        final Recipes recipes1 = recipes;
//        RecipeStepsFragment fragment = new RecipeStepsFragment();
//        Bundle args = new Bundle();
//        args.putString("Recipes", String.valueOf(recipes1));
//        fragment.setArguments(args);
//        return fragment;
//    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //Define the behavior of onStepSelected
    @Override
    public void onStepSelected(Steps step) {
        Toast.makeText(this, "Position clicked =", Toast.LENGTH_SHORT).show();
    }
}