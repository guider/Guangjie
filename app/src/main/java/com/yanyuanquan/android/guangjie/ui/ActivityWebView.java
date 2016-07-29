package com.yanyuanquan.android.guangjie.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.guider.webview.AutoWebLayout;
import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.annotation.Topbar;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.base.expand.BaseTopbarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
@Topbar(value = (R.string.detail))
@Presenter(WebViewPresenter.class)
public class ActivityWebView extends BaseTopbarActivity<WebViewPresenter> implements AutoWebLayout.Callback {

    @Bind(R.id.weblayout)
    AutoWebLayout weblayout;

    @Override
    protected void initView() {
        weblayout.loadUrl(getIntent().getStringExtra(this.getClass().getName()));
        weblayout.setCallback(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    public void onSuccessLoad() {

    }

    @Override
    public void onFailLoad(String url) {

    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onReceived(String title, Bitmap Icon, String url) {

    }

    @Override
    public void onProgressChanged(int newProgress) {

    }
}
