package com.yanyuanquan.android.guangjie.model;

import java.util.List;

/**
 * Created by guider on 16/7/24.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class Entity  {

    /**
     * id : 2889857
     * title : 时尚精选！GanDria 瑞亚 全自动男士机械表 亚马逊中国384.3元（包邮）
     * pubtime : 2016-07-24 23:22:10
     * image : http://7bv7rb.com1.z0.glb.clouddn.com/e9cce7536f99cb36cbe6c325191bf66e.jpg
     * imgw : 170
     * imgh : 170
     * iftobuy : 1
     * fromsite : 识货
     * country : cn
     * mall : 亚马逊中国
     * cates : ["makeup"]
     * buyurl : http://guangdiu.com/gdurl.php?id=2889857
     * dealfeature : 0
     */

    private int id;
    private String title;
    private String pubtime;
    private String image;
    private int imgw;
    private int imgh;
    private int iftobuy;
    private String fromsite;
    private String country;
    private String mall;
    private String buyurl;
    private String dealfeature;
    private Object cates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImgw() {
        return imgw;
    }

    public void setImgw(int imgw) {
        this.imgw = imgw;
    }

    public int getImgh() {
        return imgh;
    }

    public void setImgh(int imgh) {
        this.imgh = imgh;
    }

    public int getIftobuy() {
        return iftobuy;
    }

    public void setIftobuy(int iftobuy) {
        this.iftobuy = iftobuy;
    }

    public String getFromsite() {
        return fromsite;
    }

    public void setFromsite(String fromsite) {
        this.fromsite = fromsite;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getBuyurl() {
        return buyurl;
    }

    public void setBuyurl(String buyurl) {
        this.buyurl = buyurl;
    }

    public String getDealfeature() {
        return dealfeature;
    }

    public void setDealfeature(String dealfeature) {
        this.dealfeature = dealfeature;
    }

    public Object getCates() {
        return cates;
    }

    public void setCates(Object cates) {
        this.cates = cates;
    }
}
