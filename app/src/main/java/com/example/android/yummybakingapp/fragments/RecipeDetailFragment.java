package com.example.android.yummybakingapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import java.util.ArrayList;
import java.util.List;


public class RecipeDetailFragment extends Fragment {


    private Recipes recipes;
    private Steps steps;
    private String stepsList;
    Steps stepdetails;
    int position;

    Uri videoLink;

    TextView stepinstructionTextView;

    //Exoplayer Variables
    private SimpleExoPlayer player;
    protected PlayerView mPlayerView;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;


    // Tag for logging
    private static final String TAG = "RecipeDetailFragment";
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";


    // Saved instance state keys.
    public static final String EXO_CURRENT_WINDOW = "current_window";
    public static final String EXO_PLAY_WHEN_READY = "play_when_ready";
   public static final String EXO_PLAYBACK_POSITION = "playback_position";


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

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            steps= getArguments().getParcelable("Steps");
            recipes = getArguments().getParcelable("Recipes");
            position = getArguments().getInt("StepsPosition");

            Activity activity = this.getActivity();

            }



            stepdetails = recipes.getmSteps().get(position);

            stepsList=recipes.getmSteps().get(position).getmVideoUrl();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        //Saved Instance State
        if(savedInstanceState != null) {
           currentWindow = savedInstanceState.getInt(EXO_CURRENT_WINDOW);
            playWhenReady = savedInstanceState.getBoolean(EXO_PLAY_WHEN_READY);
            playbackPosition = savedInstanceState.getLong(EXO_PLAYBACK_POSITION);
        }


        // Initialize the player view.
        mPlayerView = (PlayerView) rootView.findViewById(R.id.playerView);

        // Get a reference to the TextView in the fragment layout
        stepinstructionTextView = (TextView) rootView.findViewById(R.id.step_detail_text_view);

        videoLink =  Uri.parse(recipes.getmSteps().get(position).getmVideoUrl());


        //**********Next and Previous Buttons*****************//

        Button nextButton = rootView.findViewById(R.id.nextButton);
        Button previousButton = rootView.findViewById(R.id.previousButton);


        if(recipes != null){

            // Set a click listener on the image view
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (player != null) {
                        player.stop();
                    }

                    position++;
                    if(position == recipes.getmSteps().size()) {
                        position=0;
                    }

                    stepinstructionTextView.setText(recipes.getmSteps().get(position).getmDescription());
                    initializePlayer(Uri.parse(recipes.getmSteps().get(position).getmVideoUrl()));

                }
            });

        } else {
            Log.v(TAG, "This fragment has a null list of image id's");
        }


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player != null) {
                    player.stop();
                }
                if(position < recipes.getmSteps().size()) {
                    position--;
                }

                stepinstructionTextView.setText(recipes.getmSteps().get(position).getmDescription());
                initializePlayer(Uri.parse(recipes.getmSteps().get(position).getmVideoUrl()));





            }
        });



        // If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
       if(recipes != null) {
           // Set the step resource to the list item at the stored index
           stepinstructionTextView.setText(stepdetails.getmDescription());

       }



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer(videoLink);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer(videoLink);
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
    private void initializePlayer(Uri videoLink) {
        player = new SimpleExoPlayer.Builder(getActivity()).build();
        mPlayerView.setPlayer(player);
        MediaSource mediaSource = buildMediaSource(videoLink);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, true, true);
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

    /**
     * Save the current state of this fragment
     */
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putInt(EXO_CURRENT_WINDOW, currentWindow);
        currentState.putBoolean(EXO_PLAY_WHEN_READY, playWhenReady);
        currentState.putLong(EXO_PLAYBACK_POSITION, playbackPosition);
    }
}