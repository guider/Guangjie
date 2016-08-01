package com.yanyuanquan.android.guangjie.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.annotation.Topbar;
import com.yanyuanquan.android.automvp.expland.adapter.EzAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.base.expand.BaseTopbarActivity;
import com.yanyuanquan.android.guangjie.model.HotEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@Topbar(value = (R.string.hot))
@Presenter(FirePresenter.class)
public class ActivityFire extends BaseTopbarActivity<FirePresenter> {
    @Bind(R.id.listview)
    ListView listview;
    HotAdater adater;

    @Override
    protected void initView() {
        adater = new HotAdater(R.layout.item_home);
        listview.setAdapter(adater);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fire;
    }

    public void setData(List<HotEntity> data) {
        adater.setData(data);

    }

    class HotAdater extends EzAdapter<HotEntity> {
        public HotAdater(int layoutId) {
            super(layoutId);
        }

        @Override
        protected void setView(HotEntity entity, EzHolder holder, Context context) {
            Glide.with(ActivityFire.this).load(entity.getImage()).into((ImageView) holder.getView(R.id.icon));
            ((TextView) holder.getView(R.id.title)).setText(entity.getTitle());
            ((TextView) holder.getView(R.id.channel)).setText(entity.getTitle());
        }
    }
}
