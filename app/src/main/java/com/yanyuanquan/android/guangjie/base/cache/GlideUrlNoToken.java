package com.yanyuanquan.android.guangjie.base.cache;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

/**
 * Created by apple on 16/5/21.
 */

public class GlideUrlNoToken extends GlideUrl {
    public GlideUrlNoToken(String url) {
        super(url);
    }

    public GlideUrlNoToken(String url, Headers headers) {
        super(url, headers);
    }

    public GlideUrlNoToken(URL url) {
        super(url);
    }

    public GlideUrlNoToken(URL url, Headers headers) {
        super(url, headers);
    }

    @Override
    public String getCacheKey() {
        String url = toStringUrl();
        if (url.contains("?")) {
            return url.substring(0, url.lastIndexOf("?"));
        } else {
            return url;
        }
    }
}