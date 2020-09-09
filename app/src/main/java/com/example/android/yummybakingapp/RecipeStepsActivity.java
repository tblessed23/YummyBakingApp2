package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;

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
    public void onStepSelected(int position) {
        Toast.makeText(this, "Position clicked =" + position, Toast.LENGTH_SHORT).show();
    }
}