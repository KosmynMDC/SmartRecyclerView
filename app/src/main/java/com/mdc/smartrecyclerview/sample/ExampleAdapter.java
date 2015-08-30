package com.mdc.smartrecyclerview.sample;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdc.smartrecyclerview.SmartRecyclerViewAdapter;

public class ExampleAdapter extends SmartRecyclerViewAdapter {
    private String[] mItems;

    public ExampleAdapter() {
    }

    public void addItems(String[] items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void clear() {
        mItems = null;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.length;
    }

    @Override
    public void onBindViewHolder(int position, RecyclerView.ViewHolder viewHolder) {
        ((ViewHolder) viewHolder).mTextView.setText(mItems[position]);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int type) {
        TextView textView = new TextView(viewGroup.getContext());

        return new ViewHolder(textView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(TextView view) {
            super(view);
            mTextView = view;
        }
    }
}