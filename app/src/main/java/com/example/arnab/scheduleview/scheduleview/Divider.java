package com.example.arnab.scheduleview.scheduleview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.arnab.scheduleview.R;

/**
 * Created by preeti on 7/4/17.
 */

public class Divider extends View {
    public Divider(Context context, boolean h, boolean t) {
        super(context);
        init(h, t);
    }

    public Divider(Context context) {
        super(context);
        init();
    }

    public Divider(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Divider(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        init(true, true);
    }

    private void init(boolean h, boolean t) {
        setBackgroundColor(t ? Color.TRANSPARENT : Color.DKGRAY);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                h ? ViewGroup.LayoutParams.MATCH_PARENT : getContext().getResources().getDimensionPixelSize(R.dimen.l),
                h ? getContext().getResources().getDimensionPixelSize(R.dimen.l) : ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
    }
}
