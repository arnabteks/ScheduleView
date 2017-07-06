package com.example.arnab.scheduleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.arnab.scheduleview.scheduleview.TwoDAdapter;
import com.example.arnab.scheduleview.scheduleview.TwoDFrameLayout;

public class MainActivity extends AppCompatActivity {

    private TwoDAdapter adapter;
    private TwoDFrameLayout layout;
    private FrameLayout parent_overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_table);

        layout = (TwoDFrameLayout) findViewById(R.id.parent);
        parent_overlay = (FrameLayout) findViewById(R.id.parent_overlay);
        adapter = new TwoDAdapter(this, layout);

        layout.setAdapter(adapter);
        layout.setup(parent_overlay);
    }
}
