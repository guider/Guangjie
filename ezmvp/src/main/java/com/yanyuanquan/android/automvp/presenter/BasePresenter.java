package com.yanyuanquan.android.automvp.presenter;

import android.support.annotation.NonNull;

import com.yanyuanquan.android.automvp.model.EzModel;

import rx.Subscription;
import rx.subjects.BehaviorSubject;


/**
 * Created by guider on 16/7/1.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class BasePresenter<V, M extends EzModel,D> extends EzPresenter<V, M> {

    protected BehaviorSubject<D> mData = BehaviorSubject.create();

    protected Subscription subscription;

    @Override
    public void onPostCreate(@NonNull V view) {
        super.onPostCreate(view);
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /**
     * 获取缓存数据的Subscriber
     * 可以通过 `getDataSubject().getValue();` 获取缓存数据。
     *
     * @return
     */
    public BehaviorSubject<D> getDataSubject() {
        return mData;
    }

    /**
     * 直接获取数据
     *
     * @return
     */
    public D getData() {
        return mData.getValue();
    }

    /**
     * 手动发布数据
     *
     * @param data
     */
    public void setData(D data) {
        mData.onNext(data);
    }
}
