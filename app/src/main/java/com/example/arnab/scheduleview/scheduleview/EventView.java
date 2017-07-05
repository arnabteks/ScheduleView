package com.example.arnab.scheduleview.scheduleview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arnab.scheduleview.R;

/**
 * Created by preeti on 7/4/17.
 */

public class EventView extends LinearLayout {
    public EventView(Context context) {
        super(context);
        init(Color.GREEN,"Tech talk", "Rammouli","Room no: 201","");
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(int backgroundColor,String event_head, String speaker,String room,String time){
        setBackgroundColor(backgroundColor);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.d) , getContext().getResources().getDimensionPixelSize(R.dimen.d));
        setLayoutParams(params);
        this.setOrientation(VERTICAL);
        this.setWeightSum(1);
        this.setPadding(10,10,10,10);
        this.setMinimumHeight(100);

        TextView tv_head= new TextView(getContext());
        tv_head.setText(event_head);
        tv_head.setGravity(Gravity.LEFT);

        TextView tv_speaker= new TextView(getContext());
        tv_speaker.setText(speaker);
        tv_speaker.setGravity(Gravity.LEFT);

        TextView tv_room= new TextView(getContext());
        tv_room.setText(room);
        tv_room.setGravity(Gravity.BOTTOM);

        this.addView(tv_head);
        this.addView(tv_speaker);
        this.addView(tv_room);

    }
}
