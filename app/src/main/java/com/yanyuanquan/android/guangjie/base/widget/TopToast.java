package com.yanyuanquan.android.guangjie.base.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.guider.util.DisplayUtil;
import com.yanyuanquan.android.guangjie.base.App;

/**
 * Created by apple on 16/6/2.
 */
public class TopToast extends Toast {
    private  static  TopToast toast;
    private  static TextView textView;
    private TopToast(Context context) {
        super(context);
    }
    private static TopToast  getInstance(Context context,String msg,int duration){
        if (toast ==null) {
            toast = new TopToast(context);
        }
        return toast;
    }
    public static void show(Context mContext,String msg){
        show(mContext,msg,TopToast.LENGTH_SHORT);
    }
    public static void show(Context mContext,String msg, int duration){
        if (toast==null) {
            Context context = mContext.getApplicationContext();
            toast = new TopToast(context);
            textView = new TextView(context);

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    DisplayUtil.getScreenWidth(context), DisplayUtil.dip2px(context, 40));
            //外层的布局不能设置背景 否则无法显示
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.addView(textView);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                    DisplayUtil.getScreenWidth(context), DisplayUtil.dip2px(context, 40));
            textView.setLayoutParams(layoutParams2);
            textView.setText(msg);
            textView.setTextSize(14);
            textView.setTextColor(Color.parseColor("#FFDFBF90"));
            textView.setBackgroundColor(Color.parseColor("#ED2D2D2D"));
            toast.setDuration(duration);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(DisplayUtil.dip2px(context, 20), 0, DisplayUtil.dip2px(context, 20), 0);
            toast.setView(linearLayout);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, DisplayUtil.dip2px(context, 44));

        }else {
            textView.setText(msg);
            toast.setDuration(duration);
        }
        App.getHandler().post(new Runnable() {
            @Override
            public void run() {
                toast.show();
            }
        });
    }

    public static void hide(){
        if (toast!=null){
            toast.cancel();
        }
    }
}