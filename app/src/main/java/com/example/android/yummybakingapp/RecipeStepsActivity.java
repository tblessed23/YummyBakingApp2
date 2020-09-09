package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Steps;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepsClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_steps);

               getSupportFragmentManager().beginTransaction()
              .replace(R.id.fragment_recipe_steps_id, new RecipeStepsFragment())
                .commit();
    }

    @Override
    public void onStepSelected(Steps step) {
        Toast.makeText(this, "Position clicked =" + step, Toast.LENGTH_SHORT).show();
    }
}