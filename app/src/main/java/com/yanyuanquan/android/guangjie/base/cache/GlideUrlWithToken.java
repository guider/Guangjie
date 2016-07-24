package com.yanyuanquan.android.guangjie.base.cache;

import com.bumptech.glide.load.model.GlideUrl;

import java.net.URL;

/**
 * Created by apple on 16/5/21.
 */

public class GlideUrlWithToken extends GlideUrl {

    private String mSourceUrl;

    public GlideUrlWithToken(String url, String token) {
        super(new StringBuilder(url)
                .append(url.contains("?") ? "&token=" : "?token=") //already has other parameters
                .append(token) // append the token at the end of url
                .toString());

        mSourceUrl = url;
    }

    public GlideUrlWithToken(URL url) {
        super(url);
    }

    public GlideUrlWithToken(String url) {
        super(url);
    }

    @Override
    public String getCacheKey() {
        return super.getCacheKey();
    }

    @Override
    public String toString() {
        return super.getCacheKey();
    }
}
