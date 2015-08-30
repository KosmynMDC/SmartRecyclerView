Android SmartRecyclerView
==========================

### 1. Empty View for RecyclerView
Displays a special view called the empty view, when the recycler view adapter is empty, just like old [ListView.setEmptyView()](http://developer.android.com/reference/android/widget/AdapterView.html#setEmptyView%28android.view.View%29).
The empty view is used to provide feedback to the user that no data is available.
The empty view can be positioned anywhere in scren.
It need to be only attached to the smart recycler view and it's done.


Methods:
- `setEmptyView`: sets the view to show if the adapter is empty.
- `getEmptyView`: returns the empty view  that is used to provide feedback to the user that no data is available

  ** Before call these methods, be sure the layout manager was set to recycler view.

Usage:
  ```java
  SmartRecyclerView recyclerView = (SmartRecyclerView) findViewById(R.id.recycler_view);
  LinearLayoutManager layouManager = new LinearLayoutManager(this);
  recyclerView.setLayoutManager(layouManager);
  
  // empty view defined in layout
  recyclerView.setEmptyView(findViewById(R.id.empty_view));
  
  // empty view defined in code
  TextView view = new TextView(context);
  view.setText("No items available");
  recyclerView.setEmptyView(view);
  ```
  
### 2. Headers & Footers for RecyclerView
RecyclerView adapter that is supporting a list of headers and another list of footers, beside the items.

Methods:
- `addHeader`: add a new header view on top of recycler view
- `removeHeader`: remove specified header
- `removeAllHeaders`: remove all headers
- `addFooter`:  add a new footer view on bottom of recycler view
- `removeFooter`: remove specified footer
- `removeAllFooters`: remove all footers

Usage:
  ```java
  RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
  LinearLayoutManager layouManager = new LinearLayoutManager(this);
  recyclerView.setLayoutManager(layouManager);
  
  SmartRecyclerViewAdapter adapter = new SmartRecyclerViewAdapter() {
      private String[] mItems;
      
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
  // Set the adapter
  recyclerView.setAdapter(adapter);
  
  // Add items
  adapter.addItems(new String[] {"Item 1", "Item 2"});
  
  // Add header 1
  TextView header1 = new TextView(context);
  header1.setText("Header - View 1");
  adapter.addHeader(header1);
  
  // Add header 2
  TextView header2 = new TextView(context);
  header2.setText("Header - View 2");
  adapter.addHeader(header2);
  
  // Add footer 1
  TextView footer1 = new TextView(context);
  footer1.setText("Footer - View 1");
  adapter.addHeader(footer1);
  
  // Add footer 2
  TextView footer2 = new TextView(context);
  footer2.setText("Footer - View 2");
  adapter.addHeader(footer2);
  
  // Remove second header
  adapter.removeHeader(header1);
  
  // Remove first footer(using position of footer)
  adapter.removeFooter(0);
  
  // Remove all headers
  adapter.removeAllHeaders();
  
  // Remove all footers
  adapter.removeAllFooters();
  ```
  
License
=======

    Copyright 2015 Cosmin Mihu
    Copyright 2015 MDC Software™

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
