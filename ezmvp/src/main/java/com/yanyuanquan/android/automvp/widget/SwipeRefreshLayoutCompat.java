package com.yanyuanquan.android.automvp.widget;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class SwipeRefreshLayoutCompat extends SwipeRefreshLayout {

    private View childView;

    public SwipeRefreshLayoutCompat(Context context) {
        super(context);
    }

    public SwipeRefreshLayoutCompat(Context context, View childView) {
        super(context);
        this.childView = childView;

    }

    public SwipeRefreshLayoutCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canRefresh) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public void setChildView(View childView) {
        this.childView = childView;
    }

    @Override
    public boolean canChildScrollUp() {
        if (childView == null) {
            return false;
        }
        return ViewCompat.canScrollVertically(childView, -1);

    }

    boolean canRefresh = true;

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }

    public void setAutoRefreshing() {
        Class<? extends SwipeRefreshLayout> refreshLayoutClass = this.getClass();
        if (refreshLayoutClass != null) {
            try {
                Method setRefreshing = refreshLayoutClass.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(this, true, true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
