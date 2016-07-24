package com.android.guider.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by apple on 16/2/2.
 */
public class FileUtil {
    public static final String CACHE_PATH = Environment.getExternalStorageDirectory() + "/.YYQ/Media";

    public static void save(String url, byte[] b) {

        if (!isMounted() || TextUtils.isEmpty(url))
            return;
        File file = new File(CACHE_PATH);
        if (!file.exists())
            file.mkdirs();
        File videoFile = new File(file, getFileName(url));

        if (videoFile.exists())
            videoFile.delete();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(videoFile);
            fos.write(b);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String url) {
        String fname = url.substring(0, url.indexOf("?e=") + url.indexOf("&e=") + 1);
        return MD5.getDigest(fname);
    }

    public static boolean isMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static File getFile(String url) {
        if (!isMounted())
            return null;
        File f = new File(CACHE_PATH, getFileName(url));
        if (f.exists())
            return f;
        return null;
    }

    public static String getFilePath(String url) {
        if (!isMounted())
            return null;
        File file = new File(CACHE_PATH);
        if (!file.exists())
            file.mkdirs();
        File videoFile = new File(file, getFileName(url));
        return videoFile.getAbsolutePath();
    }



}

