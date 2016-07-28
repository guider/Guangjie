package com.yanyuanquan.android.guangjie.ui;

import android.support.annotation.NonNull;

import com.android.guider.util.L;
import com.yanyuanquan.android.automvp.expland.adapter.EzAdapter;
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
        unsubscribe();
        subscription = model.getHaiTaoList(new LoadingSubscriber(view.getActivity(), mData, listener));
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
        subscription = model.getHaiTaoList(String.valueOf(getCacheData().get(getCacheData().size()-1).getId()),new LoadingSubscriber(view.getActivity(), mData, loadMoreListener));

    }

    LoadingSubscriber.OnNextListener<List<Entity>> loadMoreListener =new LoadingSubscriber.OnNextListener<List<Entity>>() {
        @Override
        public void onLoadSuccess(List<Entity> entities) {
            view.setLoadMoreComplete();
            view.getAdapter().append(entities);
            view.getAdapter().setState(EzAdapter.STATE_LOAD_MORE);
        }

        @Override
        public void onLoadNull() {
            view.setLoadMoreComplete();
            view.getAdapter().setState(EzAdapter.STATE_NETWORK_ERROR);
        }
    };

}
