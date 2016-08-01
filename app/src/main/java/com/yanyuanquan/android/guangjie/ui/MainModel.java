package com.yanyuanquan.android.guangjie.ui;

import com.yanyuanquan.android.automvp.model.EzModel;
import com.yanyuanquan.android.guangjie.base.BaseModel;
import com.yanyuanquan.android.guangjie.base.api.HttpManager;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;
import com.yanyuanquan.android.guangjie.model.HotEntity;

import java.io.Serializable;
import java.util.List;

import rx.Subscription;

/**
 * Created by apple on 16/7/25.
 */
public class MainModel extends BaseModel {

    public Subscription getList(LoadingSubscriber subscriber) {
        return HttpManager.getList(subscriber);
    }

    public Subscription getHaiTaoList(LoadingSubscriber subscriber) {
        return HttpManager.getHaiTaoList("us",subscriber);
    }

    public Subscription getTrankList(String date, String hour, LoadingSubscriber subscriber) {
        return HttpManager.getTrankList(date,hour,subscriber);
    }

    public Subscription getList(String sinceid, LoadingSubscriber subscriber) {
        return HttpManager.getList(sinceid,subscriber);
    }

    public Subscription getHaiTaoList(String sinceid, LoadingSubscriber subscriber) {
        return HttpManager.getHaiTaoList("us",sinceid,subscriber);
    }

    public Subscription search(String key, LoadingSubscriber<List<Entity>> subscriber) {
        return search(key,"",subscriber);
    }

    public Subscription search(String key, String id, LoadingSubscriber<List<Entity>> listLoadingSubscriber) {
        return HttpManager.search(key,id,listLoadingSubscriber);
    }

    public Subscription getHotList(String country, LoadingSubscriber<List<HotEntity>> listLoadingSubscriber) {
        return HttpManager.getHotList(country,listLoadingSubscriber);
    }
}
