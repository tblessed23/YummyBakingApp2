package com.example.android.yummybakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.adapters.RecipeAdapter;
import com.example.android.yummybakingapp.adapters.RecipeStepsAdapter;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;
import com.example.android.yummybakingapp.widget.RecipeWidgetService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeStepsFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeStepsFragment extends Fragment  implements RecipeStepsAdapter.ListItemClickListener {

String steps;
TextView stepsTextview;
private List<Recipes> mValues;
private List<Steps> mSteps;
private RecyclerView recyclerView;
private RecyclerView.LayoutManager layoutManager;
private RecipeStepsAdapter mAdapter;
private Recipes recipes;
private Steps stepsAgain;

TextView ingredientsTextView;
List<Ingredients> ingredientsList;


//Define a new interface that triggers a callback to the host activity
OnStepsClickListener mStepsListener;

// Tag for logging
    private static final String TAG = "RecipeStepsFragment";




    @Override
    public void onListItemClick(int mDataset) {
        mStepsListener.onStepSelected(mDataset);
    }




    // OnStepsClickListener interface, calls a method in the host activity named onStepSelected
    public interface OnStepsClickListener {

        void onStepSelected(int position);

    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mStepsListener = (OnStepsClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepsClickListener");
        }
    }

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            recipes = getArguments().getParcelable("Recipes");
        }

        assert recipes != null;
        ingredientsList = recipes.getmIngredients();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

//Set the Text of the Movie Object Variables
        ingredientsTextView = rootView.findViewById(R.id.ingredients_TextView);
        ingredientsTextView.setText(TextUtils.join("", ingredientsList));

        // Find a reference to the {@link RecyclerView} in the layout
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new RecipeStepsAdapter(this, recipes.getmSteps());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


//                StringBuilder stringBuilder = new StringBuilder();
//        for(Ingredients ingredient : ingredientsList){
//            String quantity = String.valueOf(ingredient.getmQuantity());
//            String measure = ingredient.getmMeasure();
//            String ingredientName = ingredient.getmQuantity();
//            String line = "- " + quantity + " " + measure + " " + ingredientName;
//            stringBuilder.append( line + "\n");
//       }
        RecipeWidgetService.startActionShowRecipes(getActivity());

        return rootView;
    }


}