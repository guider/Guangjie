package com.yanyuanquan.android.guangjie.base;

import android.os.Bundle;
import android.widget.Toast;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;

import butterknife.ButterKnife;

/**
 * Created by guider on 16/7/5.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class BaseActivity<P extends EzPresenter> extends BaseUtilActivity<P> {


    public void showTopToast(String msg) {
        showTopToast(msg, Toast.LENGTH_LONG);
    }

    public void showTopToast(String msg, int duration) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        App.setCurrentActivity(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.setCurrentActivity(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        initTopbar();
        initData();
        initView();
    }

    protected void initTopbar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        App.getHandler().removeCallbacksAndMessages(null);
    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void init();

}
