package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;
import android.text.TextUtils;

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
        getData(getCacheData() == null ? "" : getCacheData().getNexthourdate(), getCacheData() == null ? "" : getCacheData().getNexthourhour());
    }

    public void getData(String date, String hour) {
        unsubscribe();
        subscription = model.getTrankList(date, hour, new LoadingSubscriber(view.getActivity(), mData, listener));
    }

    LoadingSubscriber.OnNextListener<Trank<List<Entity>>> listener = new LoadingSubscriber.OnNextListener<Trank<List<Entity>>>() {
        @Override
        public void onLoadSuccess(Trank<List<Entity>> datas) {
            view.getAdapter().setData(datas.getData());
            view.setRefreshComplete();
            view.sethasNext(!TextUtils.isEmpty(datas.getNexthourdate()));
            view.sethasLast(!TextUtils.isEmpty(datas.getLasthourdate()));
        }

        @Override
        public void onLoadNull() {
            view.setRefreshComplete();
        }
    };

    public void getLast() {
        getData(getCacheData() == null ? "" : getCacheData().getLasthourdate(), getCacheData() == null ? "" : getCacheData().getLasthourhour());

    }

    public void getNext() {
        getData(getCacheData() == null ? "" : getCacheData().getNexthourdate(), getCacheData() == null ? "" : getCacheData().getNexthourhour());
    }
}
