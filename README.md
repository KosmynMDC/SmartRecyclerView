Android SmartRecyclerView
==========================

### 1. Empty View in RecyclerView
Display a special view called the empty view, When the recycler view adapter is empty. 
The empty view is used to provide feedback to the user that no data is available.
The empty view can be positioned anywhere in scren.
It need to be only attached to the smart recycler view and it's done.

Methods:
- `setEmptyView`: sets the view to show if the adapter is empty.
- `getEmptyView`: return the empty view  that is used to provide feedback to the user that no data is available

  ```java
  SmartRecyclerView recyclerView = (SmartRecyclerView) findViewById(R.id.recycler_view);
  LinearLayoutManager layouManager = new LinearLayoutManager(this);
  recyclerView.setLayoutManager(layouManager);
  
  recyclerView.setEmptyView(findViewById(R.id.empty_view));
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
