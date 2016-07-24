package com.yanyuanquan.android.guangjie.base.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by apple on 16/5/23.
 */

public class BlurTransformation extends BitmapTransformation {

    public BlurTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}
