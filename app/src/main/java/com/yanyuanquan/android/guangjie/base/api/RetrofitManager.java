package com.yanyuanquan.android.guangjie.base.api;

import android.text.TextUtils;

import com.android.guider.util.NetUtil;
import com.yanyuanquan.android.guangjie.base.App;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guider on 16/6/29.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class RetrofitManager {
    private static final String BASE_API = "http://guangdiu.com/api/";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    private String DATE = sdf.format(new Date(System.currentTimeMillis()));

    private static RetrofitManager manager;
    private ApiService service;
    private Retrofit retrofit;

    public static RetrofitManager getInstance() {
        if (manager == null) {
            synchronized (RetrofitManager.class) {
                if (manager == null) {
                    manager = new RetrofitManager();
                }
            }
        }
        return manager;
    }

    public ApiService getApiService() {
        if (service == null) {
            if (service == null) {
                service = retrofit.create(ApiService.class);
            }
        }
        return service;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_API)
                .build();
    }

    public OkHttpClient getHttpClient() {
        File cacheFile = new File(App.getApp().getExternalCacheDir(), "guangjie");
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(new Cache(cacheFile, 10 * 1024 * 1024))
                .addInterceptor(LOG_INTERCEPTOR)
                .addNetworkInterceptor(NETWORK_INTERCETOR)
                .build();
        return httpClient;
    }

    private final Interceptor LOG_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            String cacheControl = NetUtil.isNetworkAvailable(App.getApp())
                    ? "public, max-age=0" : "public, only-if-cached ,max-stale=2419200";
            Request.Builder builder = chain.request().newBuilder()
                    .removeHeader("Pragma");
            if (TextUtils.isEmpty(chain.request().cacheControl().toString())) {
                builder.addHeader("Cache-Control", cacheControl);

            }
            return chain.proceed(builder.build());
        }
    };

    private final Interceptor NETWORK_INTERCETOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            String cacheControl = request.cacheControl().toString();
//            if (TextUtils.isEmpty(cacheControl)) {
//                cacheControl = "no-cache";
//            }
//            L.e("   cache control2      "+cacheControl );
//            Response resonse = chain.proceed(chain.request()).newBuilder()
//                    .addHeader("Test-Log", "zjw-broker")
//                    .addHeader("Cache-Control", cacheControl)
//                    .removeHeader("Pragma").build();
//            return resonse;

            Response originalResponse = chain.proceed(chain.request());
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "no-cache";
            }
            return originalResponse.newBuilder()
                    .addHeader("Test-Log", "zjw-broker")
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma").build();
        }
    };
}
