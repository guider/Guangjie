package com.yanyuanquan.android.automvp.expland.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanyuanquan.android.automvp.R;
import com.yanyuanquan.android.automvp.widget.NetUtil;

import java.util.List;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class EzAdapter<T> extends EzBaseAdapter<T> {
    public static final int STATE_EMPTY_ITEM = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_LESS_ONE_PAGE = 4;
    public static final int STATE_NETWORK_ERROR = 5;
    public static final int STATE_OTHER = 6;
    protected int state = STATE_LOAD_MORE;
    private View footerView;

    public EzAdapter(int layoutId) {
        super(layoutId);
    }

    public EzAdapter() {
        super();
    }

    public EzAdapter(List<T> mList, int layoutId) {
        super(mList, layoutId);
    }


    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (hasFooterView() && position == getCount()-1) {
            footerView = LayoutInflater.from(convertView.getContext()).inflate(R.layout.listview_footer, null);
            View progressBar = footerView.findViewById(R.id.progressbar);
            TextView text = (TextView) footerView.findViewById(R.id.loading);
            switch (getState()) {
                case STATE_LOAD_MORE:
                    footerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                    text.setText("加载中...");
                    break;
                case STATE_NO_MORE:
                    footerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                    text.setText("没有更多了");
                    break;
                case STATE_EMPTY_ITEM:
                    progressBar.setVisibility(View.GONE);
                    footerView.setVisibility(View.VISIBLE);
                    text.setText("暂无数据");
                    break;
                case STATE_NETWORK_ERROR:
                    footerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                    if (NetUtil.isNetworkAvailable(convertView.getContext())) {
                        text.setText("加载出错了");
                    } else {
                        text.setText("没有可用的网络");
                    }
                    break;
                default:
                    progressBar.setVisibility(View.GONE);
                    footerView.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    break;
            }
            return footerView;
        }

        EzHolder holder = EzHolder.getInstance(parent, convertView, layoutId);

        setView(mDatas.get(position), holder, parent.getContext());
        setView(mDatas.get(position), holder, parent.getContext(), position);
        return holder.getConvertView();
    }


    protected void setView(T t, EzHolder holder, Context context, int position) {
    }


    protected abstract void setView(T t, EzHolder holder, Context context);


}
