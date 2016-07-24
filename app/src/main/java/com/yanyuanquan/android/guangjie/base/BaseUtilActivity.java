package com.yanyuanquan.android.guangjie.base;

import android.os.Handler;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.automvp.view.EzActivity;

/**
 * Created by guider on 16/7/13.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class BaseUtilActivity  <P extends EzPresenter> extends EzActivity<P> {


    public void delayFinish() {
        delayFinish(500);
    }

    public void delayFinish(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, time);
    }
}
