package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.android.guider.util.L;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;

import java.util.List;


public class HotPresenter extends MainPresenter<HotFragment, List<Entity>> {

    @Override
    public void onPostCreate(@NonNull HotFragment view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {
        unsubscribe();
        subscription = model.getTrankList("", "", new LoadingSubscriber(view.getActivity(), mData, listener));
    }
    LoadingSubscriber.OnNextListener<List<Entity>> listener = new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess(List<Entity> datas) {
            L.e(datas.toString());
            view.getAdapter().setData(datas);
            view.setRefreshComplete();
        }

        @Override
        public void onLoadNull() {
            view.setRefreshComplete();
        }
    };

    public void getLast() {
        subscription = model.getTrankList(getCacheData(), "", new LoadingSubscriber(view.getActivity(), mData, listener));
    }

    public void getNext() {
    }
}