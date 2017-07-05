package com.example.arnab.scheduleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arnab.scheduleview.scheduleview.TwoDAdapter;
import com.example.arnab.scheduleview.scheduleview.TwoDFrameLayout;

public class MainActivity extends AppCompatActivity {

    private TwoDAdapter adapter;
    private TwoDFrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_table);

        layout = (TwoDFrameLayout) findViewById(R.id.parent);
        adapter = new TwoDAdapter(this, layout);

        layout.setAdapter(adapter);
        layout.setup();
    }
}
