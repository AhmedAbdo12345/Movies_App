package com.example.movies.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Activities.DetailsActivity;
import com.example.movies.Activities.MainActivity;
import com.example.movies.MoviesModel;
import com.example.movies.R;

public class ReviewsAdapter extends   RecyclerView.Adapter<com.example.movies.Adapters.ReviewsAdapter.NumberViewHolder>  {




    MoviesModel moviesModel;
    Context context;

    final private com.example.movies.Adapters.ReviewsAdapter.ListItemClickListener mOnClickListener ;
    private static int viewHolderCount;
    public interface ListItemClickListener {

        void onClick(MoviesModel model, int position);
    }
    public ReviewsAdapter( com.example.movies.Adapters.ReviewsAdapter.ListItemClickListener listener) {

        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public  ReviewsAdapter(MainActivity mainActivity, com.example.movies.Adapters.ReviewsAdapter.ListItemClickListener mOnClickListener, MoviesModel moviesModel)
    {
        this.mOnClickListener = mOnClickListener;
        this.moviesModel=moviesModel;
    }

    private static final String TAG = com.example.movies.Adapters.TrailorsAdapter.class.getSimpleName();


    public ReviewsAdapter(com.example.movies.Adapters.ReviewsAdapter.ListItemClickListener mOnClickListener, MoviesModel moviesModel , Context context) {
        this.mOnClickListener = mOnClickListener;
        this.moviesModel=moviesModel;
        this.context=context;
    }

    @Override
    public com.example.movies.Adapters.ReviewsAdapter.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.reviews_show_movies;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        com.example.movies.Adapters.ReviewsAdapter.NumberViewHolder viewHolder = new com.example.movies.Adapters.ReviewsAdapter.NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final com.example.movies.Adapters.ReviewsAdapter.NumberViewHolder holder, final int position) {
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri=moviesModel.getMovie_review().get(position).toString();
                DetailsActivity detailsActivity=(DetailsActivity) context ;
                detailsActivity.openweb(uri);
            }
        });


    }


    @Override
    public int getItemCount() {
        if (moviesModel !=null)
            return moviesModel.getMovie_review().size();

        else
            return 5;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;

        public NumberViewHolder(View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.reviewButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onClick(moviesModel , getAdapterPosition());
        }
    }

}
