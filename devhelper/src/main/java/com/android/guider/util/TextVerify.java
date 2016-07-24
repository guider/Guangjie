package com.android.guider.util;

import android.text.TextUtils;

import com.android.guider.BuildConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Created by apple on 16/1/11.
 * @description:
 * @projectName:YYQ
 */
public class TextVerify {

    /**
     * 验证手机格式
     */
    public static boolean isPhoneNumber(String phone) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"
        //   ”\\d{9}"代表后面是可以是0～9的数字，有9位。“

//        String telRegex = "[1][3587]\\d{9}";
        String telRegex = true ? "[15][03587]\\d{9}" : "[1][3587]\\d{9}";

        if (TextUtils.isEmpty(phone))
            return false;
        else
            return phone.matches(telRegex);
    }

    /**
     * ^[a-zA-Z][a-zA-Z0-9]{5,15}$//以字符开头
     ^[0-9a-zA-Z]{6,16}$
     [a-z0-9A-Z]表示是字母或数字
     {6, 16}表示重复出现6~16次
     ^表示从字符串头开始匹配
     $表示匹配到字符串末尾
     如果不加^和$字符串中如果有符合条件的串也会被匹配
     */
    /**
     * 用户名。字母开头6-16位字母或数字"
     */
    public static boolean isUserName(String num) {
        if (TextUtils.isEmpty(num))
            return false;
        return Pattern.matches("^[a-zA-Z][a-zA-Z0-9]{5,15}$", num);
    }

    /**
     * 密码。输入6-16位字母或数字
     *
     * @param password
     * @return
     */
    public static boolean isPassWord(String password) {
        if (TextUtils.isEmpty(password))
            return false;
        return Pattern.matches("^[0-9a-zA-Z]{6,16}$", password);
    }


    // 判断email格式是否正确

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 是否存在字母
    public static boolean isContainsLetter(String string) {
        String regex = ".*[a-zA-Z]+.*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        return m.matches();
    }
}
