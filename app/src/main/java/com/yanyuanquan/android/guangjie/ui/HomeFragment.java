package com.yanyuanquan.android.guangjie.ui;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guider.util.L;
import com.bumptech.glide.Glide;
import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.expland.EzListFragment;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.model.Entity;

@Presenter(HomePresenter.class)
public class HomeFragment extends EzListFragment<HomePresenter, Entity> {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRefresh() {
        presenter.getData();
    }

    @Override
    public int getLayout() {
        return R.layout.item_home;
    }

    @Override
    public void loadMore() {
        presenter.loadMore();
    }

    @Override
    public void setItemData(Entity entity, EzHolder holder, Context context) {
        Glide.with(getActivity()).load(entity.getImage()).into((ImageView) holder.getView(R.id.icon));
        ((TextView) holder.getView(R.id.title)).setText(entity.getTitle());
        ((TextView) holder.getView(R.id.channel)).setText(entity.getPubtime());
        ((TextView) holder.getView(R.id.time)).setText(entity.getFromsite());
    }


}
