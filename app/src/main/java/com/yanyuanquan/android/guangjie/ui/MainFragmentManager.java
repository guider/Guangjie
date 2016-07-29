package com.yanyuanquan.android.guangjie.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;



public class MainFragmentManager {

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fm;
    private int currentId = 0;
    private int containerId = 0;

    private static MainFragmentManager instance = null;

    public static MainFragmentManager getInstance(FragmentActivity activity, int containerId) {
        return instance == null ? instance = new MainFragmentManager(activity, containerId) : instance;
    }

    private MainFragmentManager(FragmentActivity activity, int containerId) {
        this.fm = activity.getSupportFragmentManager();
        this.containerId = containerId;
        init();
    }

    private void init() {
        fragments.add(new HomeFragment());
        fragments.add(new HaiTaoFragment());
        fragments.add(new HotFragment());
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment f : fragments) {
            if (f != null) {
                ft.add(containerId, f);

            }
        }
        ft.commit();
    }


    public void hide() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment f : fragments) {
            if (f != null) {
                ft.hide(f);
            }
        }
        ft.commit();
    }

    public void show(int position) {
        hide();
        currentId = position;
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragments.get(position));
        ft.commit();
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentId);
    }

    public void onDestory() {
        instance = null;
    }


}
