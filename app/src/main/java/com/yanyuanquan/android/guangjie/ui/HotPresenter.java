package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.HotEntity;

import java.util.List;

public class HotPresenter extends MainPresenter<ActivityHot,List<HotEntity>> {



    @Override
    public void onPostCreate(@NonNull ActivityHot view) {
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
