package com.android.guider.iosdialog.base;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by apple on 16/5/20.
 */

public class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public interface OnClickListener {
        void onClick();
    }
}
