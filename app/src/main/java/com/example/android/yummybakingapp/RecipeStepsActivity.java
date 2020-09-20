package com.example.android.yummybakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yummybakingapp.fragments.RecipeDetailFragment;
import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnStepsClickListener  {

private List<Recipes> mValues;
private List<Steps> mDataset;


private Recipes recipes;
private Steps steps;
    private List<Integer> mImageIds;
    private int mListIndex;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.recipe_instructions_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Only create new fragments when there is no previously saved state
            if(savedInstanceState == null) {

                // Create a new head BodyPartFragment
                RecipeDetailFragment stepinstructionFragment = new RecipeDetailFragment();
                //stepinstructionFragment.setArguments(bundle);

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.step_detail_container, stepinstructionFragment)
                        .commit();

            }

        } else {
            mTwoPane = false;
        }


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
        if (recipes != null) {
            bundle.putParcelable("Recipes", recipes);

        }
        RecipeStepsFragment detailFragment = new  RecipeStepsFragment();
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_steps_fragment, detailFragment).commit();



    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //Define the behavior of onStepSelected
    @Override
    public void onStepSelected(int position) {
        //Handle Communication Between Fragments
        // Create a Toast that displays the position that was clicked
        //Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // Handle the two-pane case and replace existing fragments right when a new image is selected from the master list
        if (mTwoPane) {
            // Create two=pane interaction

            RecipeDetailFragment stepinstructionFragment = new RecipeDetailFragment();

            //stepinstructionFragment.setArguments(bundle);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.step_detail_container, stepinstructionFragment)
                    .commit();


        } else {

        // Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle b = new Bundle();
        b.putParcelable("Recipes", recipes);


        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, RecipeDetailActivity.class);

        intent.putExtras(b);
        intent.putExtra("StepsPosition", position);
        startActivity(intent);
    }
}}
