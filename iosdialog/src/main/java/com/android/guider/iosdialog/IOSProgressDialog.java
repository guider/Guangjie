package com.android.guider.iosdialog;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.android.guider.iosdialog.base.BaseDialog;

/**
 * Created by apple on 16/5/21.
 */

public class IOSProgressDialog extends BaseDialog {
    private Context context;

    public IOSProgressDialog(Context context) {
        this(context, 0);
    }


    public IOSProgressDialog(Context context, int themeResId) {
        super(context, R.style.iosdialog_progress_dialog);
        this.context = context;
        init();
    }


    private void init() {
        this.setCanceledOnTouchOutside(false);
        View root = LayoutInflater.from(context).inflate(R.layout.iosdialog_progress_dialog, null);
        setContentView(root);
    }
}
