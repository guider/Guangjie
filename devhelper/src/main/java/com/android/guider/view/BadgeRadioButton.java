package com.android.guider.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.android.guider.R;
import com.android.guider.util.DisplayUtil;
import com.android.guider.util.L;

/**
 * Created by zjw on 16/5/24.
 */

public class BadgeRadioButton extends RadioButton {
    Paint paint;
    private int bgColor;
    private int textColor;
    private int textSize;
    // 没有文字的情况下BadgePadding 就是绘制的圆的半径
    private int BadgePadding;
    // magin top left优先
    private int marginTop, marginRight, marginBottom, marginLeft;

    private String textContent;

    private boolean isBadgeShow = true;

    public BadgeRadioButton(Context context) {
        super(context);
        initResource(context, null);
        init();
    }

    public BadgeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResource(context, attrs);
        init();
    }

    public BadgeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResource(context, attrs);
        init();
    }

    private void initResource(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeRadioButton);
        try {
            bgColor = ta.getColor(R.styleable.BadgeRadioButton_bgColor, context.getResources().getColor(R.color.red));
            textColor = ta.getColor(R.styleable.BadgeRadioButton_textColor, context.getResources().getColor(R.color.white));
            textSize = ta.getDimensionPixelSize(R.styleable.BadgeRadioButton_textSize, DisplayUtil.dip2px(context, 10));
            BadgePadding = ta.getDimensionPixelSize(R.styleable.BadgeRadioButton_badgePadding, DisplayUtil.dip2px(context, 4));
            marginTop = ta.getDimensionPixelSize(R.styleable.BadgeRadioButton_marginTop, 0);
            marginRight = ta.getDimensionPixelSize(R.styleable.BadgeRadioButton_marginRight, 0);
            marginBottom = ta.getDimensionPixelSize(R.styleable.BadgeRadioButton_marginBottom, 0);
            marginLeft = ta.getDimensionPixelSize(R.styleable.BadgeRadioButton_marginLeft, 0);
            textContent = ta.getString(R.styleable.BadgeRadioButton_badgeText);
            isBadgeShow = ta.getBoolean(R.styleable.BadgeRadioButton_badgeShow, isBadgeShow);
        } finally {
            ta.recycle();
        }
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isBadgeShow) {
            return;
        }
        if (TextUtils.isEmpty(textContent)) {
            drawBadge(canvas);
        } else {
            drawTextBadeg(canvas, textContent);
        }
    }

    public void hideText() {
        textContent = "";
        postInvalidate();
    }

    public void hideBadge() {
        isBadgeShow = false;
        postInvalidate();
    }

    public void showText(String content) {
        isBadgeShow = true;
        textContent = content;
        postInvalidate();
    }

    public boolean isShowing() {
        return isBadgeShow;
    }


    private void drawTextBadeg(Canvas canvas, String textContent) {
        Rect rect = new Rect();
        paint.setTextSize(textSize);
        paint.getTextBounds(textContent, 0, textContent.length(), rect);

        int bgWidth = rect.width() + BadgePadding * 2;
        int bgHeight = rect.height() + BadgePadding * 2;
        // 当bgWidth的长度为1或0时，计算出来的高度会比宽度大，此时设置宽度等于高度
        if (textContent.length() <= 1) {
            bgWidth = bgHeight;
        }

        RectF rectf = new RectF();
        rectf.left = getOffSetX(bgWidth);
        rectf.right = rectf.left + bgWidth;
        rectf.top = getOffSetY(bgHeight);
        rectf.bottom = rectf.top + bgHeight;


        paint.setColor(bgColor);

        L.e(rect.toString());

        canvas.drawRoundRect(rectf, bgHeight / 2, bgHeight / 2, paint);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        float x = rectf.left + bgWidth / 2;
        // **注意：绘制文本时的y是指文本底部，而不是文本的中间
        float y = (rectf.bottom - BadgePadding);

        canvas.drawText(textContent, x, y, paint);

    }

    private void drawBadge(Canvas canvas) {
        paint.setColor(bgColor);
        int x = marginLeft > 0 ? marginLeft + BadgePadding : getWidth() - marginRight - BadgePadding;
        int y = marginTop > 0 ? marginTop + BadgePadding : getHeight() - marginBottom - BadgePadding;
        canvas.drawCircle(x, y, BadgePadding, paint);

    }

    public int getOffSetX(int bgWidth) {
        return marginLeft > 0 ? marginLeft : getWidth() - marginRight - bgWidth;
    }

    public int getOffSetY(int bgHeight) {
        return marginTop > 0 ? marginTop : getHeight() - marginBottom - bgHeight;
    }

}
