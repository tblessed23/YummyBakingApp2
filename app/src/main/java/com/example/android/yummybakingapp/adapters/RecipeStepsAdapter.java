package com.example.android.yummybakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.RecipeDetailActivity;
import com.example.android.yummybakingapp.RecipeStepsActivity;
import com.example.android.yummybakingapp.model.Ingredients;
import com.example.android.yummybakingapp.model.Steps;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {
    //private final Context mContext;
    private List<Steps> mDataset;
    private List<Ingredients> mDatasetIngredients;
    private Context mContext;
    private ListItemClickListener mOnClickListener;
    private String mSteps;
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
     * @param steps Items to display in list
     */


    public void setRecipeData(List<Steps> steps) {
        mDataset = steps;

        notifyDataSetChanged();
    }


    public interface ListItemClickListener {
        void onListItemClick(int mDataset);

    }

//    public void setOnItemClickListener(ListItemClickListener clickListener) {
//        RecipeStepsAdapter.clickListener = clickListener;
//    }

    /**
     * Constructor for MovieAdapter that accepts a number of items to display
     and the specification
     * for the ListItemClickListener.
     *
     * @param steps Items to display in list
     */


    public RecipeStepsAdapter(ListItemClickListener mOnClickListener, List<Steps> steps) {
        //mContext = context;
        this.mOnClickListener = mOnClickListener;
        mDataset = steps;


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
    public void onBindViewHolder(final RecipeStepsAdapter.RecipeStepsViewHolder holder, final int position) {
        final Steps steps = mDataset.get(position);
        TextView textViewAgain = holder.mStepsTextView;
        textViewAgain.setText(steps.getmShortDescription());


//        holder.mStepDetailsCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //mSteps= mDataset.get(position).getmDescription();
//               mOnClickListener.onListItemClick(Integer.toString(steps);
//
//            }
//        });

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
       // return mDataset.size();
        return mDataset == null ? 0 : mDataset.size();
    }

    public void clear(List<Steps> data) {
        mDataset = data;
        notifyDataSetChanged();
    }


    class RecipeStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        final TextView mStepsTextView;
//        final TextView mStepDetailsTextView;
        final CardView mStepDetailsCardView;


        RecipeStepsViewHolder(View view) {
            super(view);


            mStepsTextView = view.findViewById(R.id.content_recipesteps_cardview);
//           mStepDetailsTextView = view.findViewById(R.id.step_detail_text_view);
            mStepDetailsCardView = view.findViewById(R.id.card);

            //Call setOnClickListener on the view passed into the constructor
            //(use 'this' as the OnClickListener)
            view.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);


        }
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
    public RecipeStepsAdapter.RecipeStepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.cardview_steps_list;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup,
                shouldAttachToParentImmediately);
        view.setTag(mDataset);
        RecipeStepsAdapter.RecipeStepsViewHolder vh = new RecipeStepsAdapter.RecipeStepsViewHolder(view);

        return vh;
    }
}



