package com.example.bt_layout_2001181020_nguyenchanhbao.Module;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;

import com.example.bt_layout_2001181020_nguyenchanhbao.R;

public class HelperFunc {
    public HelperFunc() {}

    public static void changeBackgroundLayer(Context activity, Drawable background, String color) {
        LayerDrawable blur_bg = (LayerDrawable) activity.getDrawable(R.drawable.blur_bg);

        int id = blur_bg.getId(0);
        blur_bg.setDrawableByLayerId(id, background);

        int id2 = blur_bg.getId(1);
        GradientDrawable background_blur = (GradientDrawable) blur_bg.findDrawableByLayerId(id2);
        background_blur.setColor(Color.parseColor(color));
    }
}
