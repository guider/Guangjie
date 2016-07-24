package com.yanyuanquan.android.automvp.expland.adapter;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class EzBaseAdapter<D> extends BaseAdapter {
    protected List<D> mDatas;
    protected int layoutId;

    public EzBaseAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    public EzBaseAdapter() {
    }

    public EzBaseAdapter(List<D> mList, int layoutId) {
        this.mDatas = mList;
        this.layoutId = layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }


    public void setData(List<D> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public List<D> getDatas() {
        return mDatas;
    }


    public void append(List<D> datas) {
        if (mDatas == null)
            mDatas = new ArrayList<>();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void append(D t) {
        if (mDatas == null)
            mDatas = new ArrayList<>();
        mDatas.add(t);
        notifyDataSetChanged();
    }

    public void insert(D data, int position) {
        if (mDatas == null || mDatas.size() < position) {
            return;
        }
        mDatas.add(position, data);
        notifyDataSetChanged();
    }

    public void replace(D data, int position) {
        if (mDatas == null || mDatas.size() < position) {
            return;
        }
        mDatas.set(position, data);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mDatas == null || mDatas.size() < position) {
            return;
        }
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void remove(D data) {
        if (mDatas != null) {
            mDatas.remove(data);
        }
    }

    public void appendBefore(List<D> datas) {
        if (datas == null)
            return;
        List<D> list = new ArrayList<>();
        list.addAll(datas);
        if (this.mDatas != null)
            list.addAll(mDatas);
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void appendBefore(D t) {
        if (t == null)
            return;
        List<D> list = new ArrayList<>();
        list.add(t);
        if (mDatas != null)
            list.addAll(mDatas);

        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    protected boolean hasFooterView() {
        return true;
    }

    public D getData(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        if (hasFooterView()) {
            return mDatas == null || mDatas.size() == 0 ? 0 : mDatas.size() + 1;
        }
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public D getItem(int position) {

        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
