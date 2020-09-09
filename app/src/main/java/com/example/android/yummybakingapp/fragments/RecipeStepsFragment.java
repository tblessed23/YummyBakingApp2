package com.example.android.yummybakingapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeStepsFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeStepsFragment extends Fragment {

String steps;
TextView stepsTextview;
private List<Recipes> mValues;
private List<Steps> mSteps;

//Define a new interface that triggers a callback to the host activity
OnStepsClickListener mStepsListener;


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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param //param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment RecipeStepsFragment.
     */
//    // TODO: Rename and change types and number of parameters
//    public static RecipeStepsFragment newInstance(String param1, String param2) {
//        RecipeStepsFragment fragment = new RecipeStepsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                if (getArguments() != null) {
            steps = getArguments().getString("Steps");
       }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

//        // Get a reference to the CardView in the fragment layout
       final CardView cardView = (CardView) rootView.findViewById(R.id.fragment_recipe_steps_id);
      //  final GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        // If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
//        if(mImageIds != null){
//            // Set the image resource to the list item at the stored index
//            imageView.setImageResource(mImageIds.get(mListIndex));
//
//        } else {
//            Log.v(TAG, "This fragment has a null list of image id's");
//        }



//        stepsTextview = (GridView) rootView.findViewById(R.id.images_grid_view);
//        stepsTextview.setText(steps);

        // Set a click listener on the cardView and trigger the callback onStepSelected when an item is clicked
        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepsListener.onStepSelected(position);
            }


        });

//        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                // Trigger the callback method and pass in the position that was clicked
//                mStepsListener.onStepSelected(position);
//            }
//        });

//        cardView.setOnClickListener(new AdapterView.OnClickListener() {
//            @Override
//            public void onClick(AdapterView<?> adapterView, View v, View view, int position, long l) {
//                // Trigger the callback method and pass in the position that was clicked
//                mStepsListener.onStepSelected(position);
//            }
//        });

//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                // Trigger the callback method and pass in the position that was clicked
//                mStepsListener.onStepSelected(position);
//            }
//        });


        return rootView;
    }
}