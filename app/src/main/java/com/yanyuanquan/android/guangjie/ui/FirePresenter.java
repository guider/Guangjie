package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;
import com.yanyuanquan.android.guangjie.model.HotEntity;

import java.util.List;

public class FirePresenter extends MainPresenter<ActivityFire,List<HotEntity>> {



    @Override
    public void onPostCreate(@NonNull ActivityFire view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {
        model.getHotList("",new LoadingSubscriber<List<HotEntity>>(view,mData,listener));
    }
    private LoadingSubscriber.OnNextListener<List<HotEntity>> listener = new LoadingSubscriber.OnNextListener<List<HotEntity>>() {
        @Override
        public void onLoadSuccess(List<HotEntity> entities) {
            view.setData(entities);
        }
    };
}
