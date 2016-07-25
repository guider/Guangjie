package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.yanyuanquan.android.automvp.model.EzModel;
import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.guangjie.base.BaseModel;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;

import java.util.List;


public class HomePresenter extends MainPresenter<HomeFragmnet, List<Entity>> {

    @Override
    public void onPostCreate(@NonNull HomeFragmnet view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {
        subscription = model.getList(new LoadingSubscriber(view.getActivity(), mData, listener));
    }

    LoadingSubscriber.OnNextListener<List<Entity>> listener = new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess( List<Entity> datas) {
            view.getAdapter().setData(datas);
            view.setRefreshComplete();
        }

        @Override
        public void onLoadNull() {
            view.setRefreshComplete();
        }
    };
}
