package com.yanyuanquan.android.guangjie.ui;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.guangjie.R;
import com.yanyuanquan.android.guangjie.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

@Presenter(MainPresenter.class)
public class ActivityMain extends BaseActivity<MainPresenter> implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.radiogroup)
    RadioGroup radiogroup;

    MainFragmentManager manager;

    @Override
    protected void initView() {
        manager = MainFragmentManager.getInstance(this, R.id.container);
        manager.show(0);
    }

    @Override
    protected void initData() {
        radiogroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.home:
                manager.show(0);
                break;
            case R.id.aborad:
                manager.show(1);
                break;
            default:
                manager.show(2);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (manager != null) {
            manager.onDestory();
        }
    }
    @OnClick({R.id.fire, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fire:
                intent2Activity(ActivityHot.class);
                break;
            case R.id.search:
                intent2Activity(ActivitySearch.class);
                break;
        }
    }
}
