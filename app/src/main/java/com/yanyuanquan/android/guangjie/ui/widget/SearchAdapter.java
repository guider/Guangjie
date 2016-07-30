package com.yanyuanquan.android.guangjie.ui.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanyuanquan.android.automvp.expland.adapter.EzAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzBaseAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.model.Entity;

import java.util.List;

/**
 * Created by guider on 16/7/30.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class SearchAdapter extends EzAdapter<Entity> {
    public SearchAdapter(int layoutId) {
        super(layoutId);
    }

    public SearchAdapter() {
    }

    public SearchAdapter(List<Entity> mList, int layoutId) {
        super(mList, layoutId);
    }

    @Override
    protected void setView(Entity entity, EzHolder holder, Context context) {
        Glide.with(context).load(entity.getImage()).into((ImageView) holder.getView(R.id.icon));
        ((TextView) holder.getView(R.id.title)).setText(entity.getTitle());
        ((TextView) holder.getView(R.id.channel)).setText(entity.getPubtime());
        ((TextView) holder.getView(R.id.time)).setText(entity.getFromsite());
    }
}
