package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepsClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);



        getSupportFragmentManager().beginTransaction()
              .replace(R.id.detail_fragment, new RecipeStepsFragment())
                .commit();
    }

    //Define the behavior of onStepSelected
    @Override
    public void onStepSelected(Steps step) {
        Toast.makeText(this, "Position clicked =", Toast.LENGTH_SHORT).show();
    }
}