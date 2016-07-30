package com.yanyuanquan.android.guangjie.ui;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;

import java.util.List;

/**
 * Created by guider on 16/7/30.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class SearchPresenter extends MainPresenter<ActivitySearch, List<Entity>> {
    String key = "";

    @Override
    public void onPostCreate(@NonNull ActivitySearch view) {
        super.onPostCreate(view);
    }


    public void search(String key) {
        this.key = key;
        unsubscribe();
        subscription = model.search(key, new LoadingSubscriber<List<Entity>>(view, mData, listener));
    }

    LoadingSubscriber.OnNextListener<List<Entity>> listener = new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess(List<Entity> entities) {
            view.setData(entities);
        }
    };

    public void loadMore(int id) {
        subscription = model.search(key, String.valueOf(id),new LoadingSubscriber<List<Entity>>(view, mData, loadMoreListener));

    }
    LoadingSubscriber.OnNextListener<List<Entity>> loadMoreListener =  new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess(List<Entity> entities) {
        view.appendData(entities);
        }
    };
}
