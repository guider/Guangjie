package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.android.guider.util.L;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;

import java.util.List;


public class HaiTaoPresenter extends MainPresenter<HaiTaoFragment, List<Entity>> {

    @Override
    public void onPostCreate(@NonNull HaiTaoFragment view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {
        subscription = model.getHaiTaoList(new LoadingSubscriber(view.getActivity(), mData, listener));
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
}
