package com.example.android.yummybakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.yummybakingapp.R;
import com.example.android.yummybakingapp.model.Steps;
import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {

    private List<Steps> mDataset;

    //private final Context mContext;
    private ListItemClickListener mOnClickListener;

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



    /**
     * Constructor for MovieAdapter that accepts a number of items to display
     and the specification
     * for the ListItemClickListener.
     *
     * @param steps Items to display in list
     */


    public RecipeStepsAdapter(ListItemClickListener mOnClickListener, List<Steps> steps) {
        this.mOnClickListener = mOnClickListener;
        mDataset = steps;
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
        return mDataset == null ? 0 : mDataset.size();
    }

    public void clear(List<Steps> data) {
        mDataset = data;
        notifyDataSetChanged();
    }


    class RecipeStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        final TextView mStepsTextView;
        final CardView mStepDetailsCardView;


        RecipeStepsViewHolder(View view) {
            super(view);


            mStepsTextView = view.findViewById(R.id.content_recipesteps_cardview);
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



