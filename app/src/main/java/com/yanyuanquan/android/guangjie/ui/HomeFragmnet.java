package com.yanyuanquan.android.guangjie.ui;


import android.content.Context;

import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.expland.EzListFragment;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.guangjie.R;

@Presenter(HomePresenter.class)
public class HomeFragmnet extends EzListFragment<HomePresenter,Object> {

    @Override
    public void setItemData(Object o, EzHolder holder, Context context) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public int getLayout() {
        return R.layout.item_home;
    }

    @Override
    public void loadMore() {

    }
}
