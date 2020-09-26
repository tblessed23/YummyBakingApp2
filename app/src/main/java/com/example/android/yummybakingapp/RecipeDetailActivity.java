package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeDetailFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

public class RecipeDetailActivity extends AppCompatActivity {


    private Recipes recipes;
    private Steps steps;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);




            Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        // Using getParcelableExtra(String key) method
        if (intent.hasExtra(getResources().getString(R.string.intent_key_recipes))) {
            recipes = intent.getParcelableExtra(getResources().getString(R.string.intent_key_recipes));
            steps = intent.getParcelableExtra("Steps");
            position = intent.getIntExtra("StepsPosition", 0);
        }


        if (recipes == null) {
            // Movie data unavailable
            closeOnError();
            return;
        }
        Bundle bundle = new Bundle();
        if (recipes != null) {
            bundle.putParcelable("Recipes", recipes);


        }
        if (steps != null) {
            bundle.putParcelable("Steps", steps);
        }


        bundle.putInt("StepsPosition", position);
        ;

        // Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {
        // Create a new head BodyPartFragment
        RecipeDetailFragment stepinstructionFragment = new RecipeDetailFragment();
        stepinstructionFragment.setArguments(bundle);

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
               // .addToBackStack(null)
                .add(R.id.recipe_detail_fragment, stepinstructionFragment)
                .commit();

    }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }




    }
