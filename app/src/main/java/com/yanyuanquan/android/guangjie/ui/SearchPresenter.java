package com.yanyuanquan.android.guangjie.ui;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;

/**
 * Created by guider on 16/7/30.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class SearchPresenter extends MainPresenter<ActivitySearch, Object> {

    @Override
    public void onPostCreate(@NonNull ActivitySearch view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {


    }
}
