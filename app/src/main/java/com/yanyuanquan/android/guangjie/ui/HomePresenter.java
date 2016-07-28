package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;
import android.view.View;

import com.android.guider.util.L;
import com.yanyuanquan.android.automvp.expland.adapter.EzAdapter;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.model.Entity;

import java.util.List;


public class HomePresenter extends MainPresenter<HomeFragment, List<Entity>> {

    @Override
    public void onPostCreate(@NonNull HomeFragment view) {
        super.onPostCreate(view);
        getData();
    }

    public void getData() {
        unsubscribe();
        subscription = model.getList(new LoadingSubscriber(view.getActivity(), mData, listener));
    }

    LoadingSubscriber.OnNextListener<List<Entity>> listener = new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess(List<Entity> datas) {
            view.getAdapter().setData(datas);
            view.setRefreshComplete();
        }

        @Override
        public void onLoadNull() {
            view.setRefreshComplete();
        }
    };


    public void loadMore() {
        unsubscribe();
        String id = getCacheData().get(getCacheData().size()-1).getId() + "";
        subscription = model.getList(id, new LoadingSubscriber(view.getActivity(), mData, loadMorelistener));
    }

    LoadingSubscriber.OnNextListener<List<Entity>> loadMorelistener = new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess(List<Entity> entities) {
            view.getAdapter().append(entities);
            view.setLoadMoreComplete();
            view.getAdapter().setState(EzAdapter.STATE_LOAD_MORE);
        }

        @Override
        public void onLoadNull() {
            view.getAdapter().setState(EzAdapter.STATE_NETWORK_ERROR);
            view.setLoadMoreComplete();
        }
    };
}
