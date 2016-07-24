package com.yanyuanquan.android.automvp.presenter;

import android.util.Log;

import com.yanyuanquan.android.automvp.annotation.Model;
import com.yanyuanquan.android.automvp.annotation.Presenter;

/**
 */


public class UtilPresenter {


    // 获取Presenter实例
    public static <P extends EzPresenter> P getInstance(Class<?> clz) {
        Presenter anno = clz.getAnnotation(Presenter.class);
        if (anno == null){
            throw  new IllegalArgumentException("  an activity or fragment need @Presenter");
        }
        Class<P> eClass = (Class<P>) anno.value();
        try {
            return eClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("  runtimeException   ");
    }

}
