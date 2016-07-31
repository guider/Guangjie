package com.yanyuanquan.android.guangjie.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.expland.EzListFragment;
import com.yanyuanquan.android.automvp.expland.adapter.EzHolder;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.model.Entity;

@Presenter(HotPresenter.class)
public class HotFragment extends EzListFragment<HotPresenter, Entity> implements View.OnClickListener {

    Button next, last;

    @Override
    public void initView() {
        View footer = LayoutInflater.from(getActivity()).inflate(R.layout.item_hot_footer, null);
        next = (Button) footer.findViewById(R.id.next);
        last = (Button) footer.findViewById(R.id.last);
        next.setOnClickListener(this);
        last.setOnClickListener(this);
        getListView().addFooterView(footer);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRefresh() {
        presenter.getData("","");
    }

    @Override
    public int getLayout() {
        return R.layout.item_home;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                presenter.getNext();
                break;
            default:
                presenter.getLast();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapter!=null&&adapter.getData(i)!=null){
            intent2Activity(ActivityWebView.class,adapter.getData(i).getBuyurl());
        }
    }

    @Override
    public void setItemData(Entity entity, EzHolder holder, Context context) {
        Glide.with(getActivity()).load(entity.getImage()).into((ImageView) holder.getView(R.id.icon));
        ((TextView) holder.getView(R.id.title)).setText(entity.getTitle());
        ((TextView) holder.getView(R.id.channel)).setText(entity.getPubtime());
        ((TextView) holder.getView(R.id.time)).setText(entity.getFromsite());
    }

    @Override
    protected boolean hasFooter() {
        return false;
    }


    public void sethasNext(boolean hasNext) {
        next.setEnabled(hasNext);
    }

    public void sethasLast(boolean hasLast) {
        last.setEnabled(hasLast);
    }
}
