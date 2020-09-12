package com.example.android.yummybakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.RecipeStepsActivity;
import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;
import com.example.android.yummybakingapp.model.Recipes;
import com.example.android.yummybakingapp.model.Steps;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {



    private final Context mContext;
    private List<Recipes> mValues;
    private List<Steps> mSteps;



    //private ListItemClickListener mOnClickListener;
    // private final boolean mTwoPane;

//    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Recipes item = (Recipes) view.getTag();
//            if (mTwoPane) {
//                Bundle arguments = new Bundle();
//                arguments.putInt(ItemDetailFragment.ARG_ITEM_ID, item.getmId());
//                ItemDetailFragment fragment = new ItemDetailFragment();
//                fragment.setArguments(arguments);
//                mParentActivity.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.item_detail_container, fragment)
//                        .commit();
//            } else {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getmId());
//
//                context.startActivity(intent);
//            }
//        }
//    };

    /**
     * Constructor for MovieAdapter that accepts a number of items to display
     and the specification
     * for the ListItemClickListener.
     *
     * @param recipes Items to display in list
     */


    public void setRecipeData(List<Recipes> recipes) {
        mValues = recipes;
        notifyDataSetChanged();
    }


    public interface ListItemClickListener {
        void onListItemClick(int mSteps);
    }

    /**
     * Constructor for MovieAdapter that accepts a number of items to display
     and the specification
     * for the ListItemClickListener.
     *
     * @param recipes Items to display in list
     */


    public RecipeAdapter(Context context,
                        ArrayList<Recipes> recipes) {
        mContext = context;
        mValues = recipes;
        //this.mOnClickListener = mOnClickListener;
        //mTwoPane = twoPane;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at
     the specified
     * position. In this method, we update the contents of the ViewHolder to
     display the correct
     * indices in the list for this particular position, using the "position"
     argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the
    contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        final Recipes recipe = mValues.get(position);
        TextView textViewAgain = holder.mContentView;
        textViewAgain.setText(recipe.getmName());

        TextView servingsTextView = holder.mServingsView;
        servingsTextView.setText(Integer.toString(recipe.getmServings()));

        holder.firstCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(mContext, RecipeStepsActivity.class);
                intent.putExtra("Recipes", recipe);
                mContext.startActivity(intent);

            }
        });
    }


    /**
     * This method simply returns the number of items to display. It is used
     behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our list
     */

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void clear(List<Recipes> data) {
        mValues = data;
        notifyDataSetChanged();
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final TextView mServingsView;
        final TextView mStepsView;
        final CardView firstCardview;


        RecipeViewHolder(View view) {
            super(view);
            mContentView = view.findViewById(R.id.content);
            mServingsView = view.findViewById(R.id.id_text);
            mStepsView = view.findViewById(R.id.steps);
            firstCardview = view.findViewById(R.id.first_cardview);


            //Call setOnClickListener on the view passed into the constructor
            //(use 'this' as the OnClickListener)
            //view.setOnClickListener(this);
        }


//        @Override
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            mOnClickListener.onListItemClick(position);
//        }
    }
    /**
     *
     * This gets called when each new ViewHolder is created. This happens when
     the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and
     allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained
    within.
     * @param viewType  If your RecyclerView has more than one type of item
    (which ours doesn't) you
     *                  can use this viewType integer to provide a different
    layout. See
     *
     *                  for more details.
     * @return A new MovieViewHolder that holds the View for each list item
     */


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item_list_content;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup,
                shouldAttachToParentImmediately);

        RecipeViewHolder vh = new RecipeViewHolder(view);

        return vh;
    }
}


