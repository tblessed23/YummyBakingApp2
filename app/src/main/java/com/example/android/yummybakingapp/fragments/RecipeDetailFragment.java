package com.example.android.yummybakingapp.fragments;

import android.annotation.SuppressLint;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


import java.util.ArrayList;
import java.util.List;


public class RecipeDetailFragment extends Fragment {


    private Recipes recipes;
    private Steps steps;
    Steps stepdetails;
    int position;
    private List<Recipes> recipesList;

    TextView stepinstructionTextView;

    //Exoplayer Variables
    private SimpleExoPlayer player;
    protected PlayerView mPlayerView;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;


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
            recipes = getArguments().getParcelable("Recipes");
            position = getArguments().getInt("StepsPosition");

        }


            stepdetails = recipes.getmSteps().get(position);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        // Initialize the player view.
        mPlayerView = (PlayerView) rootView.findViewById(R.id.playerView);

        // Get a reference to the ImageView in the fragment layout
        stepinstructionTextView = (TextView) rootView.findViewById(R.id.step_detail_text_view);

        //**********Next and Previous Buttons*****************//

        Button nextButton = rootView.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
               stepinstructionTextView.setText(recipes.getmSteps().get(position).getmDescription());
                mPlayerView.setPlayer(player);
                position++;
        }
        });

        Button previousButton = rootView.findViewById(R.id.previousButton);



        // If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
       if(recipes != null) {
           // Set the step resource to the list item at the stored index
           stepinstructionTextView.setText(stepdetails.getmDescription());

       }





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

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    //*****************************Begin Exoplayer*****************************************************//
    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(getActivity()).build();
        mPlayerView.setPlayer(player);
        Uri uri = Uri.parse(stepdetails.getmVideoUrl());
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }
    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(getActivity(), "YummyBakingApp");
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getActivity(), userAgent);
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    //*********************************End Exoplayer********************************************//

    // Setter methods for keeping track of the list images this fragment can display and which image
    // in the list is currently being displayed

    public void setImageIds(List<Recipes> imageIds) {
        recipesList = imageIds;
    }

    public void setListIndex(int position) {
        int stepdetails = position;
    }
}