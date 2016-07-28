package com.yanyuanquan.android.guangjie.base.api;


import com.yanyuanquan.android.guangjie.base.App;
import com.yanyuanquan.android.guangjie.base.widget.LoadingSubscriber;
import com.yanyuanquan.android.guangjie.base.widget.TopToast;
import com.yanyuanquan.android.guangjie.model.Entity;
import com.yanyuanquan.android.guangjie.model.HotEntity;
import com.yanyuanquan.android.guangjie.model.Trank;

import junit.framework.Test;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guider on 16/4/29.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class HttpManager {
    //设缓存有效期为两个星期
    public static final long CACHE_STALE_SEC = 60 * 60 * 24 * 14;
    //查询缓存的Cache-Control设置
    public static final String CACHE_CONTROL_CACHE = "public, only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置
    public static final String CACHE_CONTROL_NETWORK = "public, max-age=0";

    public static final String CACHE_CONTROL_NETWORK_3 = "public, max-age=3";
    private static final int HTTP_EXCEPTION_502 = 502;
    private static final int HTTP_EXCEPTION_404 = 404;

    public static Subscription getList(LoadingSubscriber<List<Entity>> subscriber) {
        Observable<WrapData<List<Entity>>> o = getService().getList("", "", "");
        return doListSubscriber(subscriber, o);
    }

    public static Subscription getList(String count, String sinceid, LoadingSubscriber<List<Entity>> subscriber) {
        Observable<WrapData<List<Entity>>> o = getService().getList(count, sinceid, "");
        return doListSubscriber(subscriber, o);
    }

    public static Subscription getList(String count, LoadingSubscriber<List<Entity>> subscriber) {
        Observable<WrapData<List<Entity>>> o = getService().getList(count, "", "");
        return doListSubscriber(subscriber, o);
    }

    public static Subscription getList(String count, String sinceid, String county, LoadingSubscriber<List<Entity>> subscriber) {
        Observable<WrapData<List<Entity>>> o = getService().getList(count, sinceid, county);
        return doListSubscriber(subscriber, o);
    }

    private static <T> Subscription doSubscriberList(Subscriber<List<T>> subscriber, Observable<List<T>> listObservable) {
        return listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsafeSubscribe(subscriber);
    }

    public static Subscription getHaiTaoList(String county, String count, LoadingSubscriber<List<Entity>> subscriber) {
        Observable<WrapData<List<Entity>>> o = getService().getHaiTaoList(county, count);
        return doListSubscriber(subscriber, o);
    }

    public static Subscription getHaiTaoList(String county, LoadingSubscriber<List<Entity>> subscriber) {
        return getHaiTaoList(county, null, subscriber);
    }

    public static Subscription getHotList(String county, LoadingSubscriber<List<HotEntity>> subscriber) {
        Observable<WrapData<List<HotEntity>>> o = getService().getHLotist(county);
        return doListSubscriber(subscriber, o);
    }

    public static Subscription getTrankList(String date, String hour, LoadingSubscriber<Trank<List<Entity>>>  subscriber) {
        Observable<Trank<List<Entity>>> o = getService().getTrankList(date, hour);
        return doTrankListSubscriber(subscriber, o);
    }

    public static Subscription search(String county, String date, String hour, LoadingSubscriber<List<Entity>> subscriber) {
        Observable<WrapData<List<Entity>>> o = getService().search(county, date, hour);
        return doListSubscriber(subscriber, o);
    }


//    private static <T> Subscription doSubscriber(Subscriber<T> subscriber, Observable<WrapData<T>> observable) {
//        Observable<WrapData<T>> o = observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        return doMap(subscriber, o);
//    }

    private static <T> Subscription doSubscriber(Subscriber<T> subscriber, Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsafeSubscribe(subscriber);
    }

    private static <T> Subscription doListSubscriber(Subscriber<List<T>> subscriber, Observable<WrapData<List<T>>> observable) {
        return doListMap(subscriber, observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }

    private static <T> Subscription doTrankListSubscriber(Subscriber<Trank<List<T>>> subscriber, Observable<Trank<List<T>>> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsafeSubscribe(subscriber);
    }

    private static <T> Subscription doListMap(Subscriber<List<T>> subscriber, Observable<WrapData<List<T>>> observable) {
        return observable.map(new Func1<WrapData<List<T>>, List<T>>() {
            @Override
            public List<T> call(WrapData<List<T>> data) {
                if (data != null && "ok".equals(data.getStatus())) {
                    return data.getData();
                }
                return null;
            }
        }).unsafeSubscribe(subscriber);

    }


//    @NonNull
//    private static Func1<Throwable, Observable<? extends WrapData<TestMain>>> doOnError(final String key, final String method) {
//        return new Func1<Throwable, Observable<? extends WrapData<TestMain>>>() {
//            @Override
//            public Observable<? extends WrapData<TestMain>> call(Throwable throwable) {
//                // 没网的异常
//                if (throwable instanceof ConnectException) {
//                    TopToast.show(App.getApp(), "没网的异常");
//                }
//                // 连接超时
//                if (throwable instanceof TimeoutException) {
//                    TopToast.show(App.getApp(), "连接超时");
//
//                }
//                L.e(throwable.getMessage() + "        " + throwable.getClass());
//                // 包含404  502 等等
//                if (throwable instanceof HttpException) {
//                    HttpException httpException = (HttpException) throwable;
//                    if (httpException.code() == HTTP_EXCEPTION_502) {
//                        TopToast.show(App.getApp(), "服务器正在维护");
//                    } else if (httpException.code() == HTTP_EXCEPTION_404) {
//                        TopToast.show(App.getApp(), httpException.response().toString());
//                        L.e(httpException.response().toString());
//                    } else {
//                        showToast("未知HttpException");
//                    }
//                }
//
//
//                return getService().getTestMain(method, key, CACHE_CONTROL_CACHE);
//            }
//        };
//    }

    private static void showToast(final String msg) {
        App.getHandler().post(new Runnable() {
            @Override
            public void run() {
                TopToast.show(App.getApp(), msg);
            }
        });
    }
//
//    public static Subscription postTMain(Subscriber<TestMain> subscriber, ApiParams params) {
//        Observable<WrapData<TestMain>> o = getService().postTestMain(params, CACHE_CONTROL_NETWORK)
//                .onErrorResumeNext(getService().postTestMain(params, CACHE_CONTROL_CACHE));
//        return doSubscriber(subscriber, o);
//    }


    //
//    private static <T> Subscription doMap(Subscriber<T> subscriber, Observable<WrapData<T>> o) {
//        return o.map(new Func1<WrapData<T>, T>() {
//            @Override
//            public T call(WrapData<T> tWrapData) {
//                Activity currentActivity = wApp.getCurrentActivity();
//                L.e("  doMap   data  ------>>> " +tWrapData);
//
//                if (711 == tWrapData.getErrcode() && currentActivity != null) {
//                    runOnMain(new Runnable() {
//                        @Override
//                        public void run() {
//                            // // TODO: 16/7/11   此处处理服务器返回的异常
//                        }
//                    });
//
//                    return tWrapData.getData();
//                }
//
//                return tWrapData.getData();
//            }
//        }).unsafeSubscribe(subscriber);
//    }
//
//    private static void runOnMain(Runnable runnable) {
//        Aepp.getHandler().post(runnable);
//    }
//
    public static ApiService getService() {
        return RetrofitManager.getInstance().getApiService();
    }


// -----------------------------------------------------------------------------------------------------------------------

    public static void getList(Subscriber<List<Test>> subscriber, String key) {

//        Observable<WrapData<List<Test>>> observable = getService().getList(RetrofitManager.API_KEY, CACHE_CONTROL_CACHE);
//        doSubscriber(subscriber, observable);

//        Observable<WrapData<List<Test>>> observable2 = getService().getList(RetrofitManager.API_KEY,CACHE_CONTROL_NETWORK);
//        doSubscriber(subscriber, observable2);

    }


//    public static Observable<WrapData<List<Test>>> getLists(boolean key) {
////        if (key) {
////            return getService().getList(RetrofitManager.API_KEY, CACHE_CONTROL_CACHE);
////        } else {
////            return getService().getList(RetrofitManager.API_KEY, CACHE_CONTROL_NETWORK);
////        }
//
//        return null;
//    }


//    private static <T> void doMap(Subscriber<List<T>> subscriber, Observable<WrapData<List<T>>> observable) {
//        observable.map(new Func1<WrapData<List<T>>, List<T>>() {
//            @Override
//            public List<T> call(WrapData<List<T>> listWrapData) {
//                return null;
//            }
//        }).subscribe(subscriber);
//    }

}
