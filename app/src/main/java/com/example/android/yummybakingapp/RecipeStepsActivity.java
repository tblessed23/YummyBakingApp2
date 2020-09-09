package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepsClickListener {

    //Variables to store the values of the Steps
    private Recipes recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        Bundle data = getIntent().getExtras();
        recipe = (Recipes) data.getParcelable("Recipe");

       Bundle bundle = new Bundle();
        if (!(recipe == null))
            bundle.putParcelable("Recipe", recipe);

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