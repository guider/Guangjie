package com.android.guider.ui;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by apple on 16/2/24.
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

    @Override
    public boolean canChildScrollUp() {
        if (childView ==null){
            return false;
        }

        /**
         * 解决SwipeRefreshLayout 内部有多个View时 滑动冲突。
         *
         */
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (childView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) childView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return childView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(childView, -1);
        }
    }

    boolean canRefresh = true;

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }
    public  void setAutoRefreshing(){
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
