package com.yanyuanquan.android.guangjie.ui.widget;

import android.content.Context;
import android.view.View;

/**
 * Created by guider on 16/7/30.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class ComponentCompat {
    protected Context context;
    protected View view;

    public ComponentCompat(Context context) {
        this.context = context;
    }


    public View getView() {
        return view;
    }

    public void onDestory() {


    }
}
