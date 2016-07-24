package com.yanyuanquan.android.guangjie.base.expand;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanyuanquan.android.automvp.annotation.Topbar;
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
public abstract class BaseTopbarActivity<P extends EzPresenter> extends BaseActivity<P> {

    @Bind(R.id.back)
    ImageView back;
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

    protected void initTopbar() {
        Topbar topBar = this.getClass().getAnnotation(Topbar.class);
        if (topBar != null && topBar.visiable()) {
            topbarLayout.setVisibility(View.VISIBLE);
            setTitleText(topBar.value() > 0 ? getString(topBar.value()) : "");
            setConfirmText(topBar.confirm() > 0 ? getString(topBar.confirm()) : "");
            confirm.setVisibility(topBar.confirm() > 0 ? View.VISIBLE : View.GONE);
        } else {
            topbarLayout.setVisibility(View.GONE);
        }
    }

    public void setConfirmText(String confirmText) {
        confirm.setText(confirmText);
    }

    public void setConfirmText(int confirmTextId) {
        confirm.setText(confirmTextId);
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
