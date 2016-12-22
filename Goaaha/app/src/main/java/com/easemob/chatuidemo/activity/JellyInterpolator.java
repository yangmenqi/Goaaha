package com.easemob.chatuidemo.activity;

import android.view.animation.LinearInterpolator;

/**
 * Created by 刘萌 on 2016-12-13.
 */
public class JellyInterpolator extends LinearInterpolator {
    private float factor;
    public JellyInterpolator() {
        this.factor = 0.15f;
    }
    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
