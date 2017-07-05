package com.example.arnab.scheduleview.scheduleview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.arnab.scheduleview.R;

import java.util.ArrayList;

/**
 * Created by arnab on 03/07/17.
 */

public class TwoDFrameLayout extends FrameLayout {

    private Context context;

    private HorizontalScrollView hScrollView;
    private ScrollView vScrollView;
    private FrameLayout parentFrameLayout;

    private TwoDAdapter adapter;

    private int colh, colw; // column h, w
    private int rowh, roww; // row h, w
    private int cw; // cell w
    private int l; // line h
    private Divider div;

    public TwoDFrameLayout(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TwoDFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public TwoDFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        inflate(context, R.layout.twod_frame, this);
        hScrollView = findViewById(R.id.h_scroll);
        vScrollView = findViewById(R.id.v_scroll);
        parentFrameLayout = findViewById(R.id.parent_frame);

        getDimensions();
    }

    public void setAdapter(TwoDAdapter adapter) {
        this.adapter = adapter;

    }

    public void setup() {
        if (adapter == null)
            throw new IllegalStateException("adapter can not be null. call setAdapter first");

        HorizontalScrollView.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentFrameLayout.setLayoutParams(layoutParams);

        ArrayList<String> tlDataSet = adapter.getTLDataSet();
        int count = (2 * tlDataSet.size()) + 1;

        //draw tracks

        LinearLayout tracks = new LinearLayout(context);
        tracks.setOrientation(LinearLayout.HORIZONTAL);
        int noOfTracks = 8;

        for (int index = 0; index < noOfTracks; index++) {

            LinearLayout tr = new LinearLayout(context);
            tr.setOrientation(LinearLayout.VERTICAL);
            System.out.println("count: " + count);

            for (int i = 0; i < count; i++) {

                boolean isTerminal = i == 0 || i == count - 1;
                TextView textView = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(index == 0 ? roww : colw, index == 0 && i == 0 ? rowh / 2 : rowh);
                textView.setLayoutParams(params);

                if (index == 0)
                    textView.setText(isTerminal || i % 2 == 0 ? "" : tlDataSet.get((i / 2)));
                else
                    textView.setText(i == 0 ? "Room " + index : "");

                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER);

                tr.addView(textView);
                tr.addView(new Divider(context, true, index == 0));


                if (index == 1 && i == 1) {
                    tr.addView(new EventView(context));
//                    addEventOnTop(tr);
                }
            }
            System.out.println(tr.getChildCount());
            tr.setGravity(Gravity.LEFT | Gravity.TOP);

            tracks.addView(tr);
            tracks.addView(new Divider(context, false, false));
        }
        parentFrameLayout.addView(tracks);


    }

    private void addEventOnTop(LinearLayout targetView) {

        int[] location = new int[2];
        targetView.getLocationInWindow(location);
        EventView eventView = new EventView(context);

        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) targetView.getLayoutParams();

        ViewGroup.LayoutParams topLayoutParams = (ViewGroup.LayoutParams) eventView.getLayoutParams();

//        topLayoutParams.width = location[0] + targetView.getPaddingLeft();
//
//        topLayoutParams.height = location[1] + targetView.getPaddingTop() ;
//
//        eventView.setLayoutParams(topLayoutParams);

//        eventView.setPadding(location[0] + targetView.getPaddingLeft(),location[1] + targetView.getPaddingTop(),0,0);
        eventView.setLeft(location[0] + targetView.getPaddingLeft());
        eventView.setTop(location[0] + targetView.getPaddingTop());
        invalidate();

    }

    private void getDimensions() {
        colh = context.getResources().getDimensionPixelSize(R.dimen.a);
        colw = context.getResources().getDimensionPixelSize(R.dimen.b);
        rowh = context.getResources().getDimensionPixelSize(R.dimen.c);
        roww = context.getResources().getDimensionPixelSize(R.dimen.d);
        cw = context.getResources().getDimensionPixelSize(R.dimen.cw);
        l = context.getResources().getDimensionPixelSize(R.dimen.l);
    }


    public int getColumnWidth() {
        return cw;
    }

    public int getColumnHeight() {
        return colh;
    }
}
