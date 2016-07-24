package com.yanyuanquan.android.automvp.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guider on 16/6/23.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class UtilActivity extends AppCompatActivity {
    public static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        activities.add(this);
    }
    public void exit() {
        if (activities.size() > 0) {
            int size = activities.size() - 1;
            for (int i = size; i >= 0; i--) {
                Activity activity = activities.get(i);
                if (activity != null && activity != this)
                    activity.finish();
            }
        }
    }

    public static Activity getCurrentActivity() {
        if (activities.size() > 0)
            return activities.get(activities.size() - 1);
        return null;
    }

    protected void intent2Unkonw(String data) {
        intent2Unkonw(Uri.parse(data));
    }

    protected void intent2CallPhone(String mobile) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                + mobile));
        startActivity(intent);
    }

    protected void intent2Unkonw(Uri uri) {
        Intent intent = new Intent();
        intent.setData(uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void intent2Activity(Class<? extends Activity> cls) {
        intent2Activity(cls, "");
    }

    protected void intent2Activity(Class<? extends Activity> cls, Parcelable p) {
        Intent intent = new Intent(this, cls);
        if (p != null) {
            intent.putExtra(cls.getName(), p);
        }
        startActivity(intent);
    }

    protected void intent2ActivityNewFlags(Class<? extends Activity> cls, boolean b, int flags) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(flags);
        intent.putExtra(cls.getName(), b);
        startActivity(intent);
    }

    protected void intent2ActivityNewFlags(Class<? extends Activity> cls, boolean b, int flag, int flag2) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(flag);
        intent.addFlags(flag2);
        intent.putExtra(cls.getName(), b);
        startActivity(intent);
    }

    protected void intent2ActivityNewFlags(Class<? extends Activity> cls, Parcelable p, int flags) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(flags);
        if (p != null) {
            intent.putExtra(cls.getName(), p);
        }
        startActivity(intent);
    }

    protected void intent2Activity(Class<? extends Activity> cls, Serializable s) {
        Intent intent = new Intent(this, cls);
        if (s != null) {
            intent.putExtra(cls.getName(), s);
        }
        startActivity(intent);
    }

    protected void intent2Activity(Class<? extends Activity> cls, boolean b) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(cls.getName(), b);
        startActivity(intent);
    }

    protected void intent2Activity(Class<? extends Activity> cls, String params) {
        Intent intent = new Intent(this, cls);
        if (!TextUtils.isEmpty(params)) {
            intent.putExtra(cls.getName(), params);
        }
        startActivity(intent);
    }

    protected void intent2Activity(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void intent2Activity(Class<? extends Activity> clz, String param, String simpleParam) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(clz.getName(), param);
        intent.putExtra(clz.getSimpleName(), simpleParam);
        startActivity(intent);
    }

    protected void intent2ActivityForResult(Class<? extends Activity> cls, Bundle bundle, @NonNull int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(cls.getName(), bundle);
        startActivityForResult(intent, requestCode);
    }

    protected void intent2ActivityForResult(Class<? extends Activity> cls, Parcelable parcelable, @NonNull int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(cls.getName(), parcelable);
        startActivityForResult(intent, requestCode);
    }

    protected void intent2ActivityForResultCompat(Class<? extends Activity> cls, Bundle bundle, @NonNull int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    protected void intent2ActivityForResult(Class<? extends Activity> cls, String parmas, @NonNull int requestCode) {
        Intent intent = new Intent(this, cls);
        if (!TextUtils.isEmpty(parmas)) {
            intent.putExtra(cls.getName(), parmas);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void intent2ActivityForResult(Class<? extends Activity> cls, int requestCode) {
        intent2ActivityForResult(cls, "", requestCode);
    }

    protected void showToast(String msg,int duration){
        Toast.makeText(this,msg,duration);
    }

    protected void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT);
    }


    @Override
    protected void onDestroy() {
        activities.remove(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected <V extends View> V getView(int id) {
        return (V) findViewById(id);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }


}
