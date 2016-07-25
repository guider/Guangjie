package com.yanyuanquan.android.guangjie.ui;

import com.yanyuanquan.android.automvp.model.EzModel;
import com.yanyuanquan.android.guangjie.base.BaseModel;
import com.yanyuanquan.android.guangjie.base.api.HttpManager;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;

import rx.Subscription;

/**
 * Created by apple on 16/7/25.
 */
public class MainModel extends BaseModel {

    public Subscription getList(LoadingSubscriber subscriber) {
        return HttpManager.getList(subscriber);
    }

}
