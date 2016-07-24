package com.yanyuanquan.android.automvp.annotation;

import com.yanyuanquan.android.automvp.presenter.EzPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by apple on 16/6/22.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Presenter {
    Class<? extends EzPresenter> value();
}
