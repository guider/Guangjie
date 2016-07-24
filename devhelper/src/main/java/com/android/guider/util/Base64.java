package com.android.guider.util;

import android.text.TextUtils;

/**
 * @Created by apple on 16/1/16.
 * @description:
 * @projectName:YYQ
 */
public class Base64 {
    //加密
    public static String getBase64(String str){
        if (TextUtils.isEmpty(str))
            return null;

        return urlSafeDecode(new String(android.util.Base64.encode(str.getBytes(), android.util.Base64.DEFAULT))).trim();
    }

    public static String getString(String str){
        if (TextUtils.isEmpty(str))
            return null;
        return urlSafeEncode(new String(android.util.Base64.decode(str.getBytes(), android.util.Base64.DEFAULT))).trim();
    }

    private static String urlSafeEncode(String str) {
        return str.replace("+","-").replace("/","_");
    }

    private static String urlSafeDecode(String str){
        return str.replace("-","+").replace("_","/");
    }



}