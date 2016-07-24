package com.yanyuanquan.android.automvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yanyuanquan.android.automvp.model.EzModel;
import com.yanyuanquan.android.automvp.model.UtilModel;

/**
 */
public class EzPresenter<V, M extends EzModel> extends UtilPresenter {
    protected M model;

    /**
     * activity 第一次create直到到主动退出Activity之前都不会调用。
     */
    public void onCreate(@NonNull V view, Bundle savedState) {
        this.view = view;
        model = UtilModel.getModelInstance(this.getClass());
    }

    /**
     * presenter销毁时的回调。代表着activity正式退出
     */
    public void onDestroy() {

    }

    /**
     * activity$OnCreate的回调,但执行延迟到OnCreate之后。
     */
    public void onPostCreate(@NonNull V view) {

    }

    public void onDestroyView() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onSave(Bundle state) {
    }

    public void onResult(int requestCode, int resultCode, Intent data) {

    }

    String id;
    protected V view;

    @NonNull
    public final V getView() {
        return view;
    }


}
