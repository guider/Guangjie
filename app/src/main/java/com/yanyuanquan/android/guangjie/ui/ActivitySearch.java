package com.yanyuanquan.android.guangjie.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.annotation.Topbar;
import com.yanyuanquan.android.automvp.widget.EzListView;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.base.expand.BaseTopbarActivity;
import com.yanyuanquan.android.guangjie.model.Entity;
import com.yanyuanquan.android.guangjie.ui.widget.SearchAdapter;
import com.yanyuanquan.android.guangjie.ui.widget.SearchHeader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by guider on 16/7/30.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
@Topbar(value = (R.string.search_zhekou))
@Presenter(SearchPresenter.class)
public class ActivitySearch extends BaseTopbarActivity<SearchPresenter> implements SearchHeader.SearchListener, EzListView.onLoadMoreLinstener {

    SearchAdapter adapter;
    @Bind(R.id.listview)
    EzListView listview;

    @Override
    protected void initView() {
        SearchHeader header = new SearchHeader(this);
        header.setSearchListener(this);
        listview.addHeaderView(header.getView());
        listview.setOnLoadMoreLinstener(this);

    }

    @Override
    protected void initData() {

        adapter = new SearchAdapter(R.layout.item_home);
        listview.setAdapter(adapter);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void search(String key) {
        presenter.search(key);
    }

    public void setData(List<Entity> data) {
        if (adapter != null) {
            adapter.setData(data);
        }
    }

    @Override
    public void loadMore() {
        if (adapter != null && adapter.getLastItemData() != null)
            presenter.loadMore(adapter.getLastItemData().getId());
    }

    public void appendData(List<Entity> entities) {
        if (adapter != null) {
            adapter.append(entities);
        }
    }
}
