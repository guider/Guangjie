package com.android.guider.iosdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.guider.iosdialog.base.BaseDialog;
import com.android.guider.iosdialog.widget.DisplayUtil;


public class IOSDialog extends BaseDialog {
    public enum Type {NORMAL, PROGRESS}

    ;

    public IOSDialog(Context context) {
        super(context);
    }

    public IOSDialog(Context context, int themeResId) {
        super(context, themeResId);


    }

    public IOSDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }


    public static class Builder implements View.OnClickListener {
        private Context context;
        private String title;
        private int titleColor = 0;
        private int messageColor = 0;
        private int titleTextSize = 0;
        private int messageTextSize = 0;
        private int btnTextSize = 0;

        private String message;
        private String confirmBtnText;
        private int confirmBtnColor = 0;
        private String cancelBtnText;
        private int cancelBtnColor = 0;
        private int neautralBtnColor = 0;
        private OnClickListener confirmClickListener;
        private OnClickListener cancelClickListener;
        private OnClickListener neautralClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setConfirmBtnText(String confirmBtnText, OnClickListener listener) {
            this.confirmClickListener = listener;
            this.confirmBtnText = confirmBtnText;
            return this;
        }

        public Builder setCancelBtnText(String cancelBtnText, OnClickListener listener) {
            this.cancelClickListener = listener;
            this.cancelBtnText = cancelBtnText;
            return this;
        }


        public Builder setConfirmColor(@NonNull int colorId) {
            this.confirmBtnColor = colorId;
            return this;
        }

        public Builder setCancelColor(@NonNull int colorId) {
            this.cancelBtnColor = colorId;
            return this;
        }

        public Builder setNeautralColor(@NonNull int colorId) {
            this.neautralBtnColor = colorId;
            return this;
        }

        public Builder setTitleColor(@NonNull int colorId) {
            this.titleColor = colorId;
            return this;
        }

        public Builder setMessageColor(@NonNull int colorId) {
            this.messageColor = colorId;
            return this;
        }

        public Builder setTitleTextSize(@NonNull int textSize) {
            this.titleTextSize = textSize;
            return this;
        }

        public Builder setMessageTextSize(@NonNull int textSize) {
            this.messageTextSize = textSize;
            return this;
        }

        public Builder setBtnTextSize(@NonNull int textSize) {
            this.btnTextSize = textSize;
            return this;
        }

        IOSDialog dialog;

        public IOSDialog create() {
            View root = LayoutInflater.from(context).inflate(R.layout.iosdialog_dialog, null);
            TextView cancelBtn, confirmBtn, neautralBtn;
            if (!TextUtils.isEmpty(confirmBtnText) && !TextUtils.isEmpty(cancelBtnText)) {
                cancelBtn = (TextView) root.findViewById(R.id.cancel_btn);
                confirmBtn = (TextView) root.findViewById(R.id.confirm_btn);
                confirmBtn.setOnClickListener(this);
                cancelBtn.setOnClickListener(this);
                if (cancelBtnColor > 0) {
                    cancelBtn.setTextColor(context.getResources().getColor(cancelBtnColor));
                }
                if (confirmBtnColor > 0) {
                    confirmBtn.setTextColor(context.getResources().getColor(confirmBtnColor));
                }
                if (btnTextSize != 0) {
                    cancelBtn.setTextSize(btnTextSize);
                    confirmBtn.setText(btnTextSize);
                }
            } else if (!(TextUtils.isEmpty(confirmBtnText) && TextUtils.isEmpty(cancelBtnText))) {
                neautralBtn = getView(root, R.id.neutral_btn);
                neautralBtn.setText(TextUtils.isEmpty(confirmBtnText) ? (TextUtils.isEmpty(cancelBtnText) ? "隐藏" : cancelBtnText) : confirmBtnText);
                neautralBtn.setOnClickListener(this);
                neautralClickListener = confirmClickListener == null ? cancelClickListener : confirmClickListener;
                getView(root, R.id.linear_btn).setVisibility(View.GONE);
                if (neautralBtnColor > 0) {
                    neautralBtn.setTextColor(context.getResources().getColor(neautralBtnColor));
                }
                if (neautralBtnColor != 0) {
                    neautralBtn.setTextSize(btnTextSize);
                }
            }
            dialog = new IOSDialog(context, R.style.iosdialog_style);
            TextView titleTv = getView(root, R.id.title);
            titleTv.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
            titleTv.setText(String.valueOf(title));
            if (titleColor != 0)
                titleTv.setTextColor(context.getResources().getColor(titleColor));
            if (titleTextSize != 0)
                titleTv.setTextSize(titleTextSize);
            TextView messageTv = getView(root, R.id.message);
            messageTv.setVisibility(TextUtils.isEmpty(message) ? View.GONE : View.VISIBLE);
            messageTv.setText(String.valueOf(message));
            if (messageColor != 0)
                messageTv.setTextColor(context.getResources().getColor(messageColor));
            if (messageTextSize != 0)
                messageTv.setTextSize(messageTextSize);

            dialog.setContentView(root);
            Window dialogWindow = dialog.getWindow();
            // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
            WindowManager m = dialog.getWindow().getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

            p.width = DisplayUtil.dip2px(context, 290);

            dialog.onWindowAttributesChanged(p);
            dialogWindow.setAttributes(p);
            return dialog;
        }


        private <T extends View> T getView(View view, int id) {
            return (T) view.findViewById(id);

        }

        @Override
        public void onClick(View v) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

            if (v.getId() == R.id.confirm_btn) {
                if (confirmClickListener != null) {
                    confirmClickListener.onClick();
                }

            } else if (v.getId() == R.id.cancel_btn) {
                if (cancelClickListener != null) {
                    cancelClickListener.onClick();
                }
            } else if (v.getId() == R.id.neutral_btn) {
                if (neautralClickListener != null) {
                    cancelClickListener.onClick();
                }

            }
        }
    }

}
