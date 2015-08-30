package com.mdc.smartrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView adapter that is supporting mHeaderViewsList and footer, just like old {@link android.widget.ListView}.
 * The adapter supports multiple mHeaderViewsList and mFooterViewsList, beside the items
 *
 * @author Cosmin Mihu
 * @date 30.08.2015
 */
public abstract class SmartRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * Supported header views list.
     */
    private List<View> mHeaderViewsList;

    /**
     * Supported mFooterViewsList list
     */
    private List<View> mFooterViewsList;

    /**
     * Header type for this adapter
     * Value: 0
     */
    public static final int TYPE_HEADER = 0;
    /**
     * Footer type for this adapter
     * Value: 2
     */
    public static final int TYPE_FOOTER = 2;
    /**
     * Normal item type for this adapter
     * Value: 1
     */
    public static final int TYPE_ITEM = 1;


    public SmartRecyclerViewAdapter() {
        this.mHeaderViewsList = new ArrayList<>();
        this.mFooterViewsList = new ArrayList<>();
    }

    /**
     * @deprecated Please use {@link #onCreateHolder(ViewGroup, int)}.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        //if our position is one of our items (this comes from getItemViewType(int position) below)
        if (type == TYPE_ITEM) {
            return onCreateHolder(viewGroup, type);
        } else {
            //create a new FrameLayout, or inflate from a resource
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            //make sure it fills the space
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new HeaderFooterViewHolder(frameLayout);
        }
    }

    /**
     * @deprecated Please use {@link #onBindViewHolder(int, RecyclerView.ViewHolder)} ()}.
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        //check what type of view our position is
        if (position < mHeaderViewsList.size()) {
            View v = mHeaderViewsList.get(position);
            //add the view to a header view and display it
            prepareHeaderFooter((HeaderFooterViewHolder) viewHolder, v);
        } else if (position >= mHeaderViewsList.size() + getCount()) {
            View v = mFooterViewsList.get(position - getCount() - mHeaderViewsList.size());
            //add the view to a footer view and display it
            prepareHeaderFooter((HeaderFooterViewHolder) viewHolder, v);
        } else {
            //One of the other items, display as required
            onBindViewHolder(position - mHeaderViewsList.size(), viewHolder);
        }
    }

    /**
     * @deprecated Please use {@link #getCount()}.
     */
    @Override
    public int getItemCount() {
        //make sure the adapter knows to look for all our items, headers, and footers
        return mHeaderViewsList.size() + getCount() + mFooterViewsList.size();
    }


    /**
     * Empty the frame layout from header/footer view holder and replace it with new views.
     */
    private void prepareHeaderFooter(HeaderFooterViewHolder viewHolder, View view) {
        //empty out our FrameLayout and replace with our header/footer
        viewHolder.mBase.removeAllViews();
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        viewHolder.mBase.addView(view);
    }

    @Override
    public int getItemViewType(int position) {
        //check what type our position is, based on the assumption that the order is mHeaderViewsList > items > mFooterViewsList
        if (position < mHeaderViewsList.size()) {
            return TYPE_HEADER;
        } else if (position >= mHeaderViewsList.size() + getCount()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    /**
     * Called to provide the count of the items in RecyclerView. This is the number of items without footer, and headers
     */
    public abstract int getCount();

    /**
     * Called by RecyclerView to display the data at the specified position. This method should update the contents of the RecyclerView.ViewHolder
     * itemView to reflect the item at the given position.
     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method again if the position of the item changes in the
     * data set unless the item itself is invalidated or the new position cannot be determined. For this reason, you should only use the position
     * parameter while acquiring the related data item inside this method and should not keep a copy of it. If you need the position of an item
     * later on (e.g. in a click listener), use RecyclerView.ViewHolder.getAdapterPosition() which will have the updated adapter position.
     */
    public abstract void onBindViewHolder(int position, RecyclerView.ViewHolder viewHolder);

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     * This new ViewHolder should be constructed with a new View that can represent the items of the given type. You can either create a new View
     * manually or inflate it from an XML layout file.
     *
     * @param type one of the types {@link #TYPE_FOOTER},{@link #TYPE_HEADER}, {@link #TYPE_ITEM}
     */
    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int type);

    /**
     * Add a header view to the adapter
     */
    public void addHeader(View header) {
        if (!mHeaderViewsList.contains(header)) {
            mHeaderViewsList.add(header);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove a header from the adapter
     */
    public void removeHeader(View header) {
        if (mHeaderViewsList.contains(header)) {
            mHeaderViewsList.remove(header);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove a header from the adapter
     */
    public void removeHeader(int position) {
        if (mHeaderViewsList.size() > position) {
            mHeaderViewsList.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove all headers from the adapter
     */
    public void removeAllHeaders() {
        mHeaderViewsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Add a footer to the adapter
     */
    public void addFooter(View footer) {
        if (!mFooterViewsList.contains(footer)) {
            mFooterViewsList.add(footer);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove a footer from the adapter
     */
    public void removeFooter(View footer) {
        if (mFooterViewsList.contains(footer)) {
            mFooterViewsList.remove(footer);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove a header from the adapter
     */
    public void removeFooter(int position) {
        if (mFooterViewsList.size() > position) {
            mFooterViewsList.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * Remove all footers from the adapter
     */
    public void removeAllFooters() {
        mFooterViewsList.clear();
        notifyDataSetChanged();
    }

    public List getHeaderList() {
        return mHeaderViewsList;
    }

    public List getFooterList() {
        return mFooterViewsList;
    }

    /**
     * This header footer view holder, has just a simple frame layout to support any other views or fragments
     */
    public static class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
        protected FrameLayout mBase;

        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
            this.mBase = (FrameLayout) itemView;
        }
    }

    /**
     * Invalidates the header and footer views.
     * Note: it can be called from background threads as well.
     */
    protected void invalidateHeaderFooter() {
        for (View header : mHeaderViewsList) {
            if (header != null) {
                header.postInvalidate();
            }
        }

        for (View footer : mFooterViewsList) {
            if (footer != null) {
                footer.postInvalidate();
            }
        }
    }
}
