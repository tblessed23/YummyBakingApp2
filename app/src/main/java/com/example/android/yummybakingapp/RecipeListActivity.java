package com.example.android.yummybakingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yummybakingapp.adapters.RecipeAdapter;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.network.GetDataService;
import com.example.android.yummybakingapp.network.NetworkHelper;
import com.example.android.yummybakingapp.network.RecipesLoader;
import com.example.android.yummybakingapp.network.RetrofitGetData;
import com.example.android.yummybakingapp.network.SimpleIdlingResource;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link } representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Recipes>> {

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;
    private RecyclerView recyclerView;

    /** Adapter for the list of recipes */
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Recipes> mRecipes;
    private static final int RECIPES_LOADER_ID = 1;

    // The Idling Resource which will be null in production.
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        setRecyclerView();

        /*Create handle for the RetrofitInstance interface*/

        GetDataService service = RetrofitGetData.getRetrofitInstance().create(GetDataService.class);
        Call<List<Recipes>> call = service.getRecipes();
        call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(Call<List<Recipes>> call, Response<List<Recipes>> response) {
                mRecipes = response.body();
                mAdapter.setRecipeData(mRecipes);
            }

            @Override
            public void onFailure(Call<List<Recipes>> call, Throwable t) {

                Toast.makeText(RecipeListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        if(NetworkHelper.networkIsAvailable(this)){

            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.initLoader(RECIPES_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        // Get the IdlingResource instance
        getIdlingResource();
    }



    /*This Method sets the data from the Recyclerview*/
    private void setRecyclerView() {
        // Find a reference to the {@link RecyclerView} in the layout
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new RecipeAdapter(this, new ArrayList<Recipes>());
        // use a grid layout manager
        layoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * This method will make the error message visible and hide the movies
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        recyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mEmptyStateTextView.setVisibility(View.VISIBLE);
    }



    //***************************************************Begin Loader***************************************************************//
    @Override
    public androidx.loader.content.Loader<List<Recipes>> onCreateLoader(int i, Bundle bundle) {
        return new RecipesLoader(this);
    }

    @Override
    public void onLoadFinished (Loader< List < Recipes >> loader, List < Recipes > recipes){
        //Hide Progress Bar (loading indicator) to gone
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No recipes found."
        if (recipes == null) {
            showErrorMessage();
            mEmptyStateTextView.setText(R.string.no_movies);
        }

        // Clear the adapter of previous recipe data
        mAdapter.clear(new ArrayList<Recipes>());

        // If there is a valid list of {@link Recipes}s, then add them to the adapter's
        // data set. This will trigger the RecyclerView to update.
        if (recipes != null && !recipes.isEmpty()) {
            mAdapter.setRecipeData(recipes);
        }
    }

    @Override
    public void onLoaderReset(@NonNull androidx.loader.content.Loader<List<Recipes>> loader) {
        // Clear the adapter of previous recipe data
        mAdapter.clear(new ArrayList<Recipes>());
    }

    private void closeOnError() {
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //*********************************************************End Loader*****************************************//
}