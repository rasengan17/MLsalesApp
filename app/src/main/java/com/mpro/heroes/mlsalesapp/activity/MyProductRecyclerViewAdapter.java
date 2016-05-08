package com.mpro.heroes.mlsalesapp.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpro.heroes.mlsalesapp.R;
import com.mpro.heroes.mlsalesapp.activity.ProductFragment.OnListFragmentInteractionListener;
import com.mpro.heroes.mlsalesapp.activity.dummy.DummyContent.DummyItem;
import com.mpro.heroes.mlsalesapp.activity.helper.ItemTouchHelperAdapter;
import com.mpro.heroes.mlsalesapp.model.CatalogItem;

import java.util.Collections;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProductRecyclerViewAdapter extends RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private final List<CatalogItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int mItemCounter;

    public MyProductRecyclerViewAdapter(List<CatalogItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.catalogItem = mValues.get(position);
        holder.mProductName.setText(mValues.get(position).getProductName());
        holder.mQuantity.setText(Integer.toString(mValues.get(position).getQuantity()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mValues, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mValues, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mValues.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mProductName;
        public final TextView mQuantity;
        public CatalogItem catalogItem;
        public int mIdItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mProductName = (TextView) view.findViewById(R.id.id);
            mQuantity = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mQuantity.getText() + "'";
        }
    }
}
