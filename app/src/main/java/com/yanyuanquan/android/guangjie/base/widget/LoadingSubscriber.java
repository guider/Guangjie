package com.yanyuanquan.android.guangjie.base.widget;

import android.content.Context;

import com.android.guider.iosdialog.IOSProgressDialog;
import com.android.guider.util.L;
import com.yanyuanquan.android.guangjie.base.App;

import rx.Subscriber;
import rx.subjects.BehaviorSubject;

/**
 * Created by apple on 16/6/2.
 */
public class LoadingSubscriber<T> extends Subscriber<T> {
    private final IOSProgressDialog dialog;
    private BehaviorSubject<T> data;
    private OnNextListener listener;

    public LoadingSubscriber(Context context, OnNextListener<T> listener) {
        dialog = new IOSProgressDialog(context);
        this.listener = listener;
    }

    public LoadingSubscriber(Context context, BehaviorSubject<T> data, OnNextListener<T> listener) {
        dialog = new IOSProgressDialog(context);
        this.data = data;
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (dialog != null)
            dialog.show();
    }

    @Override
    public void onCompleted() {
        hide();
        if (data != null) {
//            data.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (data != null)
            data.onError(e);
        hide();
        L.e("  http  error   --- >>  " + e.toString());
    }

    @Override
    public void onNext(T t) {
        L.e("  http  success  TTTT --- >>  " + t);
        if (data != null) {
            data.onNext(t);
        }
        if (listener != null) {
            if (t != null) {
                listener.onLoadSuccess(t);
            } else {
                listener.onLoadNull();
            }
        }
    }

    private void hide() {
        if (dialog != null && dialog.isShowing()) {
            App.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            });
        }
    }

    // 非静态内部类在外部实例化会报类型检查异常
    public static abstract class OnNextListener<T> {
        public void onLoadNull() {
        }

        public abstract void onLoadSuccess(T t);
    }

}
