package com.yanyuanquan.android.automvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;
import com.yanyuanquan.android.automvp.view.EzFragment;

import butterknife.ButterKnife;

/**
 * Created by guider on 16/7/5.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class EzBaseFragment<P extends EzPresenter> extends EzFragment<P> {

    protected Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context =context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
