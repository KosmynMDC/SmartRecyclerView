package com.mdc.smartrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * RecyclerView that is supporting the empty view, when the adapter is empty,
 * just like old {@link android.widget.ListView#setEmptyView(View)}.
 *
 * @author Cosmin Mihu
 * @date 30.08.2015
 */
public class SmartRecyclerView extends RecyclerView {
    private View mEmptyView;

    private AdapterDataObserver mAdapterDataObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && mEmptyView != null) {
                int count = adapter instanceof SmartRecyclerViewAdapter
                        && ((SmartRecyclerViewAdapter) adapter).getHeaderSize() == 0
                        && ((SmartRecyclerViewAdapter) adapter).getFooterSize() == 0
                        ? ((SmartRecyclerViewAdapter) adapter).getCount() : adapter.getItemCount();
                if (count == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    SmartRecyclerView.this.setVisibility(View.GONE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                    SmartRecyclerView.this.setVisibility(View.VISIBLE);
                }
            }

        }
    };

    public SmartRecyclerView(Context context) {
        super(context);
    }

    public SmartRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(mAdapterDataObserver);
        }

        mAdapterDataObserver.onChanged();
    }

    /**
     * Sets the view to show if the adapter is empty.
     *
     * @param emptyView The view to show if the adapter is empty.
     */
    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    /**
     * When the current adapter is empty, the recycler view can display a special view
     * called the empty view. The empty view is used to provide feedback to the user
     * that no data is available.
     *
     * @return The view to show if the adapter is empty.
     */
    private View getEmptyView() {
        return mEmptyView;
    }
}
