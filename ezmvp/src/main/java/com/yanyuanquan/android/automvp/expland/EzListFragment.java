package com.yanyuanquan.android.automvp.expland;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yanyuanquan.android.automvp.R;
import com.yanyuanquan.android.automvp.expland.adapter.EzAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzBaseAdapter;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.automvp.view.EzBaseFragment;
import com.yanyuanquan.android.automvp.view.EzFragment;
import com.yanyuanquan.android.automvp.widget.EzListView;
import com.yanyuanquan.android.automvp.widget.SwipeRefreshLayoutCompat;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class EzListFragment<P extends EzPresenter, D> extends EzFragment<P>
        implements EzListView.onLoadMoreLinstener, SwipeRefreshLayoutCompat.OnRefreshListener {

    private EzListView listView;
    private SwipeRefreshLayoutCompat swipeRefreshLayout;
    private View emptyView;
    protected EzAdapter<D> adapter;
    private View errorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout root = new FrameLayout(getActivity());
        View errView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_error_view, null);
        errView.setId(R.id.error_view);
        root.addView(errView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        errView.setVisibility(View.GONE);
        View progressBar = LayoutInflater.from(getActivity()).inflate(R.layout.layout_progressbar, null);
        root.addView(progressBar, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        progressBar.setVisibility(View.GONE);
        //---------ListView
        FrameLayout frameLayout = new FrameLayout(getActivity());
        TextView tv = new TextView(getActivity());
        tv.setId(R.id.textview);
        tv.setVisibility(View.GONE);
        tv.setText("暂无数据");
        tv.setGravity(Gravity.CENTER);
        frameLayout.addView(tv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        EzListView lv = new EzListView(getActivity());
        lv.setId(R.id.listview);
        frameLayout.addView(lv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        SwipeRefreshLayoutCompat swipeRefreshLayout = new SwipeRefreshLayoutCompat(getActivity(), lv);
        swipeRefreshLayout.setId(R.id.swiperefreshlayout);
        swipeRefreshLayout.addView(frameLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        root.addView(swipeRefreshLayout, new SwipeRefreshLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //------------root layoutparmas
        root.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSetting();
    }

    protected void initSetting() {
        if (listView != null) {
            listView.setDivider(getResources().getDrawable(getDividerDrawableId()));
            listView.setDividerHeight(getDividerHeight());
            listView.setEmptyView(emptyView);
        }
        swipeRefreshLayout.setCanRefresh(canRefresh());

    }


    private void initAdapter() {
        adapter = new EzAdapter<D>(getLayout()) {
            @Override
            protected void setView(D d, EzHolder holder, Context context) {

            }
        };
        listView.setAdapter(adapter);


    }

    public EzAdapter<D> getAdapter() {
        return adapter;
    }


    public abstract void setItemData(D d, EzHolder holder, Context context);

    public abstract void initView();

    public abstract void initData();

    protected void setRefreshComplete() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);

                }
            });
    }

    protected void setRefreshCompleteDelayed(int time) {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);

                }
            }, 500);
    }

    @Override
    public void init() {
        listView = (EzListView) getView().findViewById(R.id.listview);
        listView.setOnLoadMoreLinstener(this);
        initAdapter();

        swipeRefreshLayout = (SwipeRefreshLayoutCompat) getView().findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        emptyView = getView().findViewById(R.id.textview);
        errorView = getView().findViewById(R.id.error_view);
    }

    public EzListView getListView() {
        return listView != null ? listView : (listView = (EzListView) getView().findViewById(R.id.listview));
    }

    public void setBackGround(int drawableId) {
        if (listView != null) {
            listView.setBackgroundResource(drawableId);
        }
    }

    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        if (listView != null) {
            listView.setPadding(left, top, right, bottom);
        }
    }

    public void setPaddingTB(int top, int bottom) {
        if (listView != null) {
            listView.setPadding(0, top, 0, bottom);
        }
    }

    public void setPaddingLR(int left, int right) {
        if (listView != null) {
            listView.setPadding(left, 0, right, 0);
        }
    }

    public int getDividerHeight() {
        return 0;
    }

    private boolean canRefresh() {
        return true;
    }

    public int getDividerDrawableId() {
        return R.color.float_transparent;
    }

}
