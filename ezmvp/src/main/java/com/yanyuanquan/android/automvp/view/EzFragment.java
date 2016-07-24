package com.yanyuanquan.android.automvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.automvp.presenter.UtilPresenter;

/**
 * Created by guider on 16/6/23.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class EzFragment<P extends EzPresenter> extends UtilFragment {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = UtilPresenter.getInstance(this.getClass());
        presenter.onCreate(this,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onPostCreate(this);
        init();
        initData();
        initView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSave(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onResult(requestCode,resultCode,data);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter.onDestroyView();
    }

    protected abstract void initView();
    protected abstract void initData();
    protected abstract  void init();

    public abstract int getLayout() ;
}
