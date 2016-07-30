package com.yanyuanquan.android.guangjie.ui.widget;

import android.content.Context;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.guider.util.L;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.base.widget.TopToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by guider on 16/7/30.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class SearchHeader extends ComponentCompat implements View.OnClickListener {
    @Bind(R.id.search_view)
    SearchView searchView;

    public SearchHeader(Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.item_searchview, null);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        L.d("   query   text :  " + searchView.getQuery().toString());
        if (listener != null && !TextUtils.isEmpty(searchView.getQuery())) {
            listener.search(searchView.getQuery().toString());
            L.d("   query   text :  " + searchView.getQuery().toString());
        } else if (!TextUtils.isEmpty(searchView.getQuery())) {
            Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_LONG).show();
        }
    }

    public interface SearchListener {
        void search(String key);
    }

    private SearchListener listener;

    public void setSearchListener(SearchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDestory() {
        ButterKnife.unbind(this);
    }
}
