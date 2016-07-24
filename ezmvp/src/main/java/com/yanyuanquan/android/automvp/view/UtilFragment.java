package com.yanyuanquan.android.automvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by guider on 16/6/23.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class UtilFragment extends Fragment {

    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    public void intent2Unkonw(String data) {
        intent2Unkonw(Uri.parse(data));
    }

    public void intent2CallPhone(String mobile) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                + mobile));
        startActivity(intent);
    }

    public void intent2Unkonw(Uri uri) {
        Intent intent = new Intent();
        intent.setData(uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void intent2Activity(Class<? extends Activity> cls) {
        intent2Activity(cls, "");
    }

    public void intent2Activity(Class<? extends Activity> cls, Parcelable p) {
        Intent intent = new Intent(context, cls);
        if (p != null) {
            intent.putExtra(cls.getName(), p);
        }
        startActivity(intent);
    }

    public void intent2ActivityNewFlags(Class<? extends Activity> cls, boolean b, int flags) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(flags);
        intent.putExtra(cls.getName(), b);
        startActivity(intent);
    }

    public void intent2ActivityNewFlags(Class<? extends Activity> cls, boolean b, int flag, int flag2) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(flag);
        intent.addFlags(flag2);
        intent.putExtra(cls.getName(), b);
        startActivity(intent);
    }

    public void intent2ActivityNewFlags(Class<? extends Activity> cls, Parcelable p, int flags) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(flags);
        if (p != null) {
            intent.putExtra(cls.getName(), p);
        }
        startActivity(intent);
    }

    public void intent2Activity(Class<? extends Activity> cls, Serializable s) {
        Intent intent = new Intent(context, cls);
        if (s != null) {
            intent.putExtra(cls.getName(), s);
        }
        startActivity(intent);
    }

    public void intent2Activity(Class<? extends Activity> cls, boolean b) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(cls.getName(), b);
        startActivity(intent);
    }

    public void intent2Activity(Class<? extends Activity> cls, String params) {
        Intent intent = new Intent(context, cls);
        if (!TextUtils.isEmpty(params)) {
            intent.putExtra(cls.getName(), params);
        }
        startActivity(intent);
    }

    public void intent2Activity(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void intent2Activity(Class<? extends Activity> clz, String param, String simpleParam) {
        Intent intent = new Intent(context, clz);
        intent.putExtra(clz.getName(), param);
        intent.putExtra(clz.getSimpleName(), simpleParam);
        startActivity(intent);
    }

    public void intent2ActivityForResult(Class<? extends Activity> cls, Bundle bundle, @NonNull int requestCode) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(cls.getName(), bundle);
        startActivityForResult(intent, requestCode);
    }

    public void intent2ActivityForResult(Class<? extends Activity> cls, Parcelable parcelable, @NonNull int requestCode) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(cls.getName(), parcelable);
        startActivityForResult(intent, requestCode);
    }

    public void intent2ActivityForResultCompat(Class<? extends Activity> cls, Bundle bundle, @NonNull int requestCode) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public void intent2ActivityForResult(Class<? extends Activity> cls, String parmas, @NonNull int requestCode) {
        Intent intent = new Intent(context, cls);
        if (!TextUtils.isEmpty(parmas)) {
            intent.putExtra(cls.getName(), parmas);
        }
        startActivityForResult(intent, requestCode);
    }

    public void intent2ActivityForResult(Class<? extends Activity> cls, int requestCode) {
        intent2ActivityForResult(cls, "", requestCode);
    }

    public void showToast(String msg,int duration){
        Toast.makeText(context,msg,duration);
    }

    public void showToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT);
    }



}
