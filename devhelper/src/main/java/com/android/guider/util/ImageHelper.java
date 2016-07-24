package com.android.guider.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

/**
 * Created by apple on 16/1/31.
 */
public class ImageHelper {

    /**
     * 根据图片的Uri获取图片的本地保存路径
     *
     * @param context
     * @param uri     图片Uri
     * @return 图片的本地保存路径
     */
    public static final String getPathByUri(Context context, Uri uri) {
        if (uri == null)
            return null;

        if ("file".equals(uri.getScheme())) {
            return uri.getPath();
        }

        Cursor cursor = null;
        try {
            String keyData = MediaStore.Images.Media.DATA;
            if (TextUtils.isEmpty(keyData)) {
                keyData = "_data";
            }

            String[] projection = {keyData};
            cursor = null;
            try {
                cursor = MediaStore.Images.Media.query(
                        context.getContentResolver(), uri, projection, null,
                        null);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            if (cursor == null) {
                return null;
            }

            if (cursor.getCount() == 0) {
                cursor.close();
                return null;
            } else {
                cursor.moveToNext();
                String imagePath = cursor.getString(cursor
                        .getColumnIndexOrThrow(keyData));
                cursor.close();
                return imagePath;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return null;
    }
}
