package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.android.guider.util.L;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;
import com.yanyuanquan.android.guangjie.model.HotEntity;
import com.yanyuanquan.android.guangjie.model.Trank;

import java.util.List;


public class HotPresenter extends MainPresenter<HotFragment, Trank<List<Entity>>> {

    @Override
    public void onPostCreate(@NonNull HotFragment view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {
        unsubscribe();
        subscription = model.getTrankList("", "", new LoadingSubscriber(view.getActivity(), mData, listener));
    }

    LoadingSubscriber.OnNextListener<Trank<List<Entity>>> listener = new LoadingSubscriber.OnNextListener<Trank<List<Entity>>>() {
        @Override
        public void onLoadSuccess(Trank<List<Entity>> datas) {
            L.e(datas.toString());
            view.getAdapter().setData(datas.getData());
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
