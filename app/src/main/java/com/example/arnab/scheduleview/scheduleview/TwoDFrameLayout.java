package com.example.arnab.scheduleview.scheduleview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
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
        setup();
    }

    public void setup() {
        if (adapter == null)
            throw new IllegalStateException("adapter can not be null. call setAdapter first");

        HorizontalScrollView.LayoutParams layoutParams = new LayoutParams(adapter.getWidth(), adapter.getHeight());
        parentFrameLayout.setLayoutParams(layoutParams);

        LinearLayout tl = new LinearLayout(context);
        tl.setOrientation(LinearLayout.VERTICAL);
        ArrayList<String> tlDataSet = adapter.getTLDataSet();
        int count = tlDataSet.size() + 2;
        for (int index = 0; index < count; index++) {
            boolean isTerminal = index == 0 || index == count - 1;
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(roww, isTerminal ? rowh / 2 : rowh);
            textView.setLayoutParams(params);
            textView.setText(isTerminal ? "" : tlDataSet.get(index - 1));
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            tl.addView(textView);
        }
        tl.setGravity(Gravity.LEFT | Gravity.TOP);
        parentFrameLayout.addView(tl);
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
