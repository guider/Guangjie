package com.yanyuanquan.android.guangjie.ui.widget;

import android.content.Context;

import com.yanyuanquan.android.automvp.expland.adapter.EzAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzBaseAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
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

    }
}
