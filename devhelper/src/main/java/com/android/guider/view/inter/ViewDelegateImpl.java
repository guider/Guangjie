package com.android.guider.view.inter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/**
 * Created by apple on 16/5/25.
 */

public class ViewDelegateImpl implements ViewDelegate {
    @Override
    public void showCirclePointBadge() {

    }

    @Override
    public void showTextBadge(String badgeText) {

    }

    @Override
    public void hiddenBadge() {

    }

    @Override
    public void showDrawableBadge(Bitmap bitmap) {

    }

    @Override
    public boolean callSuperOnTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean isShowBadge() {
        return false;
    }

    @Override
    public ViewDelegate getDelegate() {
        return null;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void postInvalidate() {

    }

    @Override
    public ViewParent getParent() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean getGlobalVisibleRect(Rect r) {
        return false;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public View getRootView() {
        return null;
    }
}
