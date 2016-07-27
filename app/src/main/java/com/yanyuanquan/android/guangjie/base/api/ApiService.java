package com.yanyuanquan.android.guangjie.base.api;


import com.yanyuanquan.android.guangjie.model.Entity;
import com.yanyuanquan.android.guangjie.model.HotEntity;
import com.yanyuanquan.android.guangjie.model.Trank;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by guider on 16/7/13.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public interface ApiService {


    //    首页 sinceid 分页
    @GET("getlist.php")
    Observable<WrapData<List<Entity>>> getList(@Query("count") String count, @Query("sinceid") String sinceid, @Query("country") String county);

    //    详情 通用
    //    http://guangdiu.com/api/showdetail.php?v=2&id=2889803


    //   搜索
    @GET("getresult.php")
    Observable<WrapData<List<Entity>>> search(@Query("count") String count, @Query("q") String keyword, @Query("sinceid") String sinceid);

//    http://guangdiu.com/api/getresult.php?count=30&q=%E9%9E%8B

    //    海淘 首页
//    getlist.php?country=us&count=30
    @GET("getlist.php")
    Observable<WrapData<List<Entity>>> getHaiTaoList(@Query("country") String county, @Query("count") String count);

//
//    分页
//    getlist.php?country=us&count=30&sinceid=2889659


    // 风云榜
    @GET("getranklist.php")
    Observable<Trank<List<Entity>>> getTrankList(@Query("count") String count, @Query("date") String date, @Query("hour") String hour);


    //    热门
//    gethots.php?c=us
    @GET("gethots.php")
    Observable<WrapData<List<HotEntity>>> getHLotist(@Query("c") String county);


}
