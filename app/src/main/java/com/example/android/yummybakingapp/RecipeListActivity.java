package com.example.android.yummybakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yummybakingapp.adapters.RecipeAdapter;
import com.example.android.yummybakingapp.fragments.RecipeListFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.network.GetDataService;
import com.example.android.yummybakingapp.network.NetworkHelper;
import com.example.android.yummybakingapp.network.RecipesLoader;
import com.example.android.yummybakingapp.network.RetrofitGetData;

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
public class RecipeListActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);



        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_list, new RecipeListFragment())
                .commit();



    }







}