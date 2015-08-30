package com.mdc.smartrecyclerview.sample.header_footer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mdc.smartrecyclerview.sample.ExampleAdapter;
import com.mdc.smartrecyclerview.sample.R;

public class HeaderFooterRecyclerActivity extends AppCompatActivity {
    private ExampleAdapter mAdapter;

    private View header1;
    private View header2;
    private View footer1;
    private View footer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header1 = View.inflate(this, R.layout.header_1, null);
        header2 = View.inflate(this, R.layout.header_2, null);
        footer1 = View.inflate(this, R.layout.footer_1, null);
        footer2 = View.inflate(this, R.layout.footer_2, null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ExampleAdapter();
        mAdapter.addItems(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"});
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_header_footer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_headers_footers:

                mAdapter.removeAllHeaders();
                mAdapter.removeAllFooters();

                return true;
            case R.id.add_headers:

                mAdapter.addHeader(header1);
                mAdapter.addHeader(header2);
                mAdapter.addFooter(footer1);
                mAdapter.addFooter(footer2);

                return true;

            case R.id.remove_second_header:

                mAdapter.removeHeader(1);
                // or
                mAdapter.removeHeader(header2);

                return true;

            case R.id.remove_second_footer:

                mAdapter.removeFooter(1);
                // or
                mAdapter.removeFooter(footer2);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
