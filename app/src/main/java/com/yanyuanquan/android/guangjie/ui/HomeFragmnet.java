package com.yanyuanquan.android.guangjie.ui;


import android.content.Context;

import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.expland.EzListFragment;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.model.Entity;

@Presenter(HomePresenter.class)
public class HomeFragmnet extends EzListFragment<HomePresenter,Entity> {

    @Override
    public void setItemData(Entity entity, EzHolder holder, Context context) {

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
