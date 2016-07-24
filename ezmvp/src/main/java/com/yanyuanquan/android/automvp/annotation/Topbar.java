package com.yanyuanquan.android.automvp.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by guider on 16/6/24.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Topbar {
    int cancelId() default 0;
    int value() default 0;
    int confirm() default 0;
    boolean visiable() default true;

}
