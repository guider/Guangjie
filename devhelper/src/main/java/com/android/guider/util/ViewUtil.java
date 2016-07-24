package com.android.guider.util;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.ArrowKeyMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * @Created by apple on 16/2/15.
 * @description:
 * @projectName:YYQ
 */

public class ViewUtil {

    /**
     * 获取View距离上边界的距离, 即layout_marginTop属性的值
     *
     * @param v
     * @return View距离上边界的距离
     */
    public static int getViewTopMargin(View v) {
        int topMargin = 0;

        ViewGroup.LayoutParams lp = v.getLayoutParams();

        try {
            Class<?> cls = lp.getClass();
            Field field = cls.getField("topMargin");
            topMargin = field.getInt(lp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return topMargin;
    }

    /**
     * 设置View距离上边界的距离, 即layout_marginTop属性的值
     *
     * @param v
     * @param topMargin 上边距
     */
    public static void setViewTopMargin(View v, int topMargin) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();

        try {
            Class<?> cls = lp.getClass();
            Field field = cls.getField("topMargin");

            field.setInt(lp, topMargin);
            v.setLayoutParams(lp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取View的宽度
     *
     * @param v
     * @return View的宽度
     */
    public static int getViewWidth(View v) {
        int width = v.getWidth();
        if (width <= 0) {
            v.measure(0, 0);
            width = v.getMeasuredWidth();
        }

        return width;
    }

    /**
     * 获取View的高度
     *
     * @param v
     * @return View的高度
     */
    public static int getViewHeight(View v) {
        int height = v.getHeight();
        if (height <= 0) {
            v.measure(0, 0);
            height = v.getMeasuredHeight();
        }

        return height;
    }

    /**
     * 设置TextView是否可以选择复制. <br/>
     * <br/>
     * 需要注意的是: <br/>
     * 不能设置TextView的内容为Spannable的, 如果设置为Spannable, 可能会导致无法长按出现复制菜单. 如果非要设置成
     * Spannable的话, 思路是可以设置TextView的LongClick事件, 详细解决方案可以联系我.
     *
     * @param textView
     * @author wangjingtao
     */
    public static void setTextSelectable(TextView textView) {

        // Android 3.0(11)以下的方式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            setTextSelectableBase(textView);
        } else {
            setTextSelectableV11(textView);
        }
    }

    private static void setTextSelectableBase(TextView textView) {
        textView.setFocusableInTouchMode(true);
        textView.setFocusable(true);
        textView.setClickable(true);
        textView.setLongClickable(true);
        textView.setMovementMethod(ArrowKeyMovementMethod.getInstance());
        textView.setText(textView.getText(), TextView.BufferType.SPANNABLE);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static void setTextSelectableV11(TextView textView) {
        textView.setTextIsSelectable(true);
    }

    /**
     * 设置View的背景
     *
     * @param v
     * @param drawable
     */
    public static void setBackground(View v, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundV16(v, drawable);
        } else {
            setBackgroundBase(v, drawable);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static void setBackgroundV16(View v, Drawable drawable) {
        v.setBackground(drawable);
    }

    @SuppressWarnings("deprecation")
    private static void setBackgroundBase(View v, Drawable drawable) {
        v.setBackgroundDrawable(drawable);

    }
}
