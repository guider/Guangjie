package com.yanyuanquan.android.automvp.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by guider on 16/7/8.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Topbar2  {
    int leftTextId() default 0;
    int value() default 0;
    int rightTextId() default 0;
    boolean visiable() default true;

}
