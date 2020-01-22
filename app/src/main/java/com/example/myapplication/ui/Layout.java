package com.example.myapplication.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

public class Layout extends LinearLayout {

    public Layout(Context context, int id) {
        super(context);
        init(context, id);
    }



    private void init(Context context, int id) {
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(id, this, true);
    }
}
