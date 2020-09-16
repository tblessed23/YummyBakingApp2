package com.example.android.yummybakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

import java.util.List;


public class RecipeDetailFragment extends Fragment {


    private Recipes recipes;
    private Steps steps;

    private List<Recipes> recipesList;
    private int stepsList;
    private String mStepsAgain;


    TextView stepinstructionTextView;
    Steps stepdetails;
    int recipestepsId;
    String stepsDescription;
    int stepsstepsId;

    private List<Integer> mImageIds;
    private int mListIndex;

    // Tag for logging
    private static final String TAG = "RecipeDetailFragment";


    public RecipeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            steps= getArguments().getParcelable("Steps");

        }

        if (getArguments() != null) {
            recipes = getArguments().getParcelable("Recipes");
        }



        //stepsList = recipes.getmSteps().indexOf(steps);

        assert recipes != null;
        stepdetails = recipes.getmSteps().get(Integer.parseInt(String.valueOf(steps)));


;


//        //go through each item if you have few items within recycler view
//        if(recipestepsId==0){
//            stepdetails = recipes.getmSteps().get(0);
//        }else if(recipestepsId==1){
//            stepdetails = recipes.getmSteps().get(1);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        // Get a reference to the ImageView in the fragment layout
        stepinstructionTextView = (TextView) rootView.findViewById(R.id.step_detail_text_view);



//Set the Text of the Movie Object Variables
//        TextView ingredientsTextView = rootView.findViewById(R.id.step_detail_text_view);
//        ingredientsTextView.setText(stepdetails.getmDescription());

        // If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
      // if(recipes != null){
            // Set the step resource to the list item at the stored index
        stepinstructionTextView.setText(stepdetails.getmDescription());

//            // Set a click listener on the image view
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Increment position as long as the index remains <= the size of the image ids list
//                    if(mListIndex < mImageIds.size()-1) {
//                        mListIndex++;
//                    } else {
//                        // The end of list has been reached, so return to beginning index
//                        mListIndex = 0;
//                    }
//                    // Set the image resource to the new list item
//                    textView.setText(stepdetails.getmDescription());
//                }
//            });

        //} else {
        //    Log.v(TAG, "This fragment has a null list of image id's");
       // }

        return rootView;
    }



    // Setter methods for keeping track of the list images this fragment can display and which image
    // in the list is currently being displayed

    public void setImageIds(List<Recipes> imageIds) {
        recipesList = imageIds;
    }

    public void setListIndex(int index) {
        int stepdetailsone = index;
    }
}