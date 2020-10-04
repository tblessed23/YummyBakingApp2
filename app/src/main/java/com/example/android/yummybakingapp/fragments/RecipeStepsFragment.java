package com.example.android.yummybakingapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.adapters.RecipeStepsAdapter;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
TextView testtwoingredientsTextView;
TextView testingredientsTextView;
SharedPreferences pref;

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
            recipes = getArguments().getParcelable(getResources().getString(R.string.intent_key_recipes));
        }

        assert recipes != null;
        ingredientsList = recipes.getmIngredients();

      pref = getContext().getSharedPreferences(String.valueOf(R.string.preference_name), Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        //Set the Text of the Recipe Object Variables
        ingredientsTextView = rootView.findViewById(R.id.ingredients_TextView);
        ingredientsTextView.setText(TextUtils.join("", ingredientsList));

        testingredientsTextView = rootView.findViewById(R.id.ingredientstestname_TextView);
        testingredientsTextView.setText(pref.getString("recipe_name", null));

        SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        Gson gson = new Gson();
        String json = pref.getString("recipe_list", "");
        Type type = new TypeToken<List<Ingredients>>() {}.getType();
        List<Ingredients> arrayList = gson.fromJson(json, type);

        //Set the Text of the Movie Object Variables
        testtwoingredientsTextView = rootView.findViewById(R.id.ingredientstest_TextView);
        assert arrayList != null;
        testtwoingredientsTextView.setText(TextUtils.join("", arrayList));
        // Find a reference to the {@link RecyclerView} in the layout
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new RecipeStepsAdapter(this, recipes.getmSteps());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }


}