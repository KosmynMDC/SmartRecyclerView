package com.mdc.smartrecyclerview.sample.empty;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.mdc.smartrecyclerview.SmartRecyclerView;
import com.mdc.smartrecyclerview.sample.ExampleAdapter;
import com.mdc.smartrecyclerview.sample.R;

public class EmptyRecyclerActivity extends ActionBarActivity {

    private ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartRecyclerView recyclerView = (SmartRecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Need to be called after the layout manager is set.
        recyclerView.setEmptyView(findViewById(R.id.empty_view));

        mAdapter = new ExampleAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty_reycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_item:

                mAdapter.clear();

                return true;
            case R.id.add_item:

                mAdapter.addItems(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"});

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
