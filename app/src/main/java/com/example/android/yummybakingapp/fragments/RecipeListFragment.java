package com.example.android.yummybakingapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.adapters.RecipeAdapter;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.network.GetDataService;
import com.example.android.yummybakingapp.network.NetworkHelper;
import com.example.android.yummybakingapp.network.RecipesLoader;
import com.example.android.yummybakingapp.network.RetrofitGetData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeListFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
//public class RecipeListFragment extends Fragment  implements LoaderManager.LoaderCallbacks<List<Recipes>> {
//
//    /**
//     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
//     * device.
//     */
//    //private boolean mTwoPane;
//    /**
//     * TextView that is displayed when the list is empty
//     */
//    private TextView mEmptyStateTextView;
//    private RecyclerView recyclerView;
//    /** Adapter for the list of earthquakes */
//    private RecipeAdapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private List<Recipes> mRecipes;
//    private static final int RECIPES_LOADER_ID = 1;
//
//
//
//    public RecipeListFragment() {
//        // Required empty public constructor
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.activity_recipe_list, container, false);
//
//
//        setRecyclerView();
//
//        /*Create handle for the RetrofitInstance interface*/
//
//        GetDataService service = RetrofitGetData.getRetrofitInstance().create(GetDataService.class);
//        Call<List<Recipes>> call = service.getRecipes();
//        call.enqueue(new Callback<List<Recipes>>() {
//            @Override
//            public void onResponse(Call<List<Recipes>> call, Response<List<Recipes>> response) {
//                mRecipes = response.body();
//                mAdapter.setRecipeData(mRecipes);
//            }
//
//            @Override
//            public void onFailure(Call<List<Recipes>> call, Throwable t) {
//
//                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//        return rootView;
//    }
//
//    //This method is only used in Fragments
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if(NetworkHelper.networkIsAvailable(getActivity())){
//
//            LoaderManager loaderManager = LoaderManager.getInstance(this);
//            loaderManager.initLoader(RECIPES_LOADER_ID, null, this);
//        } else {
//            View loadingIndicator = getView().findViewById(R.id.loading_indicator);
//            loadingIndicator.setVisibility(View.GONE);
//            mEmptyStateTextView.setText(R.string.no_internet_connection);
//        }
//    }
//
//
//
//
//    /*This Method sets the data from the Recyclerview*/
//    private void setRecyclerView() {
//        // Find a reference to the {@link RecyclerView} in the layout
//        recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        // Create a new adapter that takes an empty list of earthquakes as input
//        mAdapter = new RecipeAdapter(getActivity(), new ArrayList<Recipes>());
//        // use a grid layout manager
//        layoutManager = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(mAdapter);
//    }
//
//
//    /**
//     * This method will make the error message visible and hide the movies
//     * View.
//     * <p>
//     * Since it is okay to redundantly set the visibility of a View, we don't
//     * need to check whether each view is currently visible or invisible.
//     */
//    private void showErrorMessage() {
//        /* First, hide the currently visible data */
//        recyclerView.setVisibility(View.INVISIBLE);
//        /* Then, show the error */
//        mEmptyStateTextView.setVisibility(View.VISIBLE);
//    }
//
//    //***************************************************Begin Loader***************************************************************//
//    @Override
//    public androidx.loader.content.Loader<List<Recipes>> onCreateLoader(int i, Bundle bundle) {
//        return new RecipesLoader(getActivity());
//    }
//
//    @Override
//    public void onLoadFinished (Loader< List < Recipes >> loader, List < Recipes > recipes){
//        //Hide Progress Bar (loading indicator) to gone
//
//        View loadingIndicator = getActivity().findViewById(R.id.loading_indicator);
//        loadingIndicator.setVisibility(View.GONE);
//
//        // Set empty state text to display "No movies found."
//        if (recipes == null) {
//            showErrorMessage();
//            mEmptyStateTextView.setText(R.string.no_movies);
//        }
//
//        // Clear the adapter of previous movie data
//        mAdapter.clear(new ArrayList<Recipes>());
//
//        // If there is a valid list of {@link Movies}s, then add them to the adapter's
//        // data set. This will trigger the RecyclerView to update.
//        if (recipes != null && !recipes.isEmpty()) {
//            mAdapter.setRecipeData(recipes);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull androidx.loader.content.Loader<List<Recipes>> loader) {
//        // Clear the adapter of previous news data
//        mAdapter.clear(new ArrayList<Recipes>());
//    }
//
//    //*********************************************************End Loader*****************************************//
//}