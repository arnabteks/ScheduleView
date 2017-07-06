package com.example.arnab.scheduleview.scheduleview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.arnab.scheduleview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    Object[][] eventPos;

    public void setup(FrameLayout parent_overlay) {


        if (adapter == null)
            throw new IllegalStateException("adapter can not be null. call setAdapter first");

        HorizontalScrollView.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentFrameLayout.setLayoutParams(layoutParams);

        ArrayList<String> tlDataSet = adapter.getTLDataSet();
        List<String> tracksDataSet = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));

        eventPos = new Object[tlDataSet.size()][tracksDataSet.size()]; //to store hour,track location
        int count = (2 * tlDataSet.size()) + 1;

        //draw tracks

        LinearLayout tracks = new LinearLayout(context);
        tracks.setOrientation(LinearLayout.HORIZONTAL);

        for (int index = 0; index < tracksDataSet.size(); index++) {

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
                    textView.setText(i == 0 ? "Room " + tracksDataSet.get(index) : "");

                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER);

                tr.addView(textView);
                Divider div = new Divider(context, true, index == 0);
                tr.addView(div);

                if (!isTerminal && i % 2 != 0 && i < 25) {
                    eventPos[i][index] = textView;
                    Log.v("oo", " " + eventPos[i][index]);
                }

            }
            System.out.println(tr.getChildCount());
            tr.setGravity(Gravity.LEFT | Gravity.TOP);

            tracks.addView(tr);
            tracks.addView(new Divider(context, false, false));
        }
        parentFrameLayout.addView(tracks);


        parent_overlay.setMinimumWidth(parentFrameLayout.getWidth());
        parent_overlay.setMinimumHeight(parentFrameLayout.getHeight());
        int hour = 3;
        int track = 1;

        addEventOnTop(hour, track, parent_overlay);

//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                TextView object= (TextView) eventPos[3][1];
//                eventView.setX(object.getX());
//                eventView.setY(object.getY());
//            }
//        });
    }

    EventView eventView;

    private void addEventOnTop(int hour, int track, FrameLayout parent_overlay) {

        eventView = new EventView(context);
//        String s = eventPos[hour][track];
//        String first = s.split(",")[0];
//        String next = s.split(",")[1];

//        eventView.setX(Float.parseFloat(first));
//        eventView.setY(Float.parseFloat(next));

        parentFrameLayout.addView(eventView);

       // invalidate();

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
