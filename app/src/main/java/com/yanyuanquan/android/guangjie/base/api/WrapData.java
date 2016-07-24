package com.yanyuanquan.android.guangjie.base.api;

import java.util.List;

/**
 * Created by guider on 16/7/24.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class WrapData<T> {


    /**
     * status : ok
     * newincluded : 1
     * data : [{"id":2889857,"title":"时尚精选！GanDria 瑞亚 全自动男士机械表 亚马逊中国384.3元（包邮）","pubtime":"2016-07-24 23:22:10","image":"http://7bv7rb.com1.z0.glb.clouddn.com/e9cce7536f99cb36cbe6c325191bf66e.jpg","imgw":170,"imgh":170,"iftobuy":1,"fromsite":"识货","country":"cn","mall":"亚马逊中国","cates":["makeup"],"buyurl":"http://guangdiu.com/gdurl.php?id=2889857","dealfeature":"0"}]
     */

    private String status;
    private int newincluded;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNewincluded() {
        return newincluded;
    }

    public void setNewincluded(int newincluded) {
        this.newincluded = newincluded;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WrapData{" +
                "status='" + status + '\'' +
                ", newincluded=" + newincluded +
                ", data=" + data +
                '}';
    }
}
