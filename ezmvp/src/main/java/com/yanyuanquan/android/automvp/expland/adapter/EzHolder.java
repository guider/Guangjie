package com.yanyuanquan.android.automvp.expland.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class EzHolder {

    private SparseArray<View> views;
    private View convertView;

    private EzHolder(View convertView, int layoutId, ViewGroup parent) {
        this.convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        this.convertView.setTag(this);
        views = new SparseArray<View>();
    }

    public static EzHolder getInstance(ViewGroup parent, View convertView, int layoutId) {
        if (convertView != null) {
            EzHolder holder = (EzHolder) convertView.getTag();
            if (holder != null) {
                return holder;
            }
        }
        return new EzHolder(convertView, layoutId, parent);

    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }
}
