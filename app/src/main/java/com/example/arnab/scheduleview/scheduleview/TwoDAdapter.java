package com.example.arnab.scheduleview.scheduleview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by arnab on 03/07/17.
 */

public class TwoDAdapter {

    private Context context;
    private TwoDFrameLayout layout;

    public TwoDAdapter(Context context, TwoDFrameLayout layout) {
        this.context = context;
        this.layout = layout;
    }

    public int getWidth() {
        return layout.getColumnWidth() * getColumnCount();
    }

    public int getHeight() {
        return layout.getColumnHeight() * getRowCount();
    }

    public int getRowCount() {
        return 25;
    }

    public int getColumnCount() {
        return 10;
    }


    public ArrayList<String> getTLDataSet() {
        ArrayList<String> tlDataSet = new ArrayList<>();
        for (int index = 0; index < 24; index++)
            tlDataSet.add(Integer.toString(index) + (index >= 12 ? " PM" : " AM"));
        return tlDataSet;
    }
}
