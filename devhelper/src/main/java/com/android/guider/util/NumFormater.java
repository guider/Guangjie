package com.android.guider.util;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * @Created by apple on 16/2/16.
 * @description:
 * @projectName:YYQ
 */

public class NumFormater {
    private static Typeface numTypeface;

    public static void setNumTypeface(TextView textView) {
        if (numTypeface == null) {
            numTypeface = Typeface.createFromAsset(textView.getContext()
                    .getAssets(), "font/NUM-UL-GB.ttf");
        }
        textView.setTypeface(numTypeface);
    }
}
