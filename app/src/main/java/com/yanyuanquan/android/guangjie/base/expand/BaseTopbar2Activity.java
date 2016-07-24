package com.yanyuanquan.android.guangjie.base.expand;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.yanyuanquan.android.automvp.annotation.Topbar2;
import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by guider on 16/7/13.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class BaseTopbar2Activity<P extends EzPresenter> extends BaseActivity<P> {

    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.topbar)
    FrameLayout topbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initTopbar() {
        Topbar2 topBar = this.getClass().getAnnotation(Topbar2.class);
        if (topBar != null && topBar.visiable()) {
            topbarLayout.setVisibility(View.VISIBLE);
            setTitleText(topBar.value() > 0 ? getString(topBar.value()) : "");
            setRightText(topBar.rightTextId() > 0 ? getString(topBar.rightTextId()) : "");
            confirm.setVisibility(topBar.rightTextId() > 0 ? View.VISIBLE : View.GONE);
            setLeftText(topBar.leftTextId() > 0 ? getString(topBar.leftTextId()) : "");
            back.setVisibility(topBar.leftTextId() > 0 ? View.VISIBLE : View.GONE);
        } else {
            topbarLayout.setVisibility(View.GONE);
        }
    }

    public void setRightText(String confirmText) {
        confirm.setText(confirmText);
    }

    public void setRightText(int confirmTextId) {
        confirm.setText(confirmTextId);
    }

    public void setLeftText(String confirmText) {
        back.setText(confirmText);
    }

    public void setLeftText(int confirmTextId) {
        back.setText(confirmTextId);
    }

    public void setTitleText(String titleText) {
        title.setText(titleText);
    }

    public void setTitleText(int titleTextId) {
        title.setText(titleTextId);
    }

    @OnClick(R.id.back)
    protected void back() {
        onBackPressed();
    }

    @OnClick(R.id.confirm)
    protected void confirm() {

    }

}
