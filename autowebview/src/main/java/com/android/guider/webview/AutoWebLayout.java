package com.android.guider.webview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.http.SslError;
import android.nfc.tech.IsoDep;
import android.os.Handler;
import android.speech.tts.Voice;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.guider.util.DisplayUtil;
import com.android.guider.util.L;
import com.android.guider.util.NetUtil;

import java.util.Properties;
import java.util.TimerTask;

/**
 * Email guider@yeah.net
 * x * @Created by apple on 16/3/10.
 *
 * @description:
 * @projectName:YYQ
 */
public class AutoWebLayout extends FrameLayout {


    /**
     * 当前 是否返回错误
     */
    boolean isError = false;
    ProgressBar progressBar;
    WebView webview;
    private String url;

    private int progressWidth =4;
    private int progress =2;
    private int secondProgress;
    private int progressStyle = android.R.attr.progressBarStyleHorizontal;
    private int progressDrawable = R.drawable.progress_style;

    public AutoWebLayout(Context context) {
        this(context, null);
    }

    public AutoWebLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoWebLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AutoWebView);
        boolean jsEnable = ta.getBoolean(R.styleable.AutoWebView_JsEnable, true);
        progress = ta.getIndex(R.styleable.AutoWebView_Progress);
        secondProgress = ta.getIndex(R.styleable.AutoWebView_secondProgress);
        progressStyle =ta.getResourceId(R.styleable.AutoWebView_ProgressStyle,progressStyle);
        progressDrawable = ta.getResourceId(R.styleable.AutoWebView_progressDrawable,progressDrawable);
        progressWidth = ta.getDimensionPixelSize(R.styleable.AutoWebView_progressWidth,progressWidth);

        webview = new WebView(context.getApplicationContext());
        this.addView(webview, new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        progressBar = new ProgressBar(context.getApplicationContext(), null, progressStyle);
        progressBar.setProgressDrawable(context.getApplicationContext().getResources().getDrawable(progressDrawable));
        this.addView(progressBar, new LayoutParams(LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(context.getApplicationContext(), progressWidth)));
        progressBar.setProgress(progress);
        progressBar.setSecondaryProgress(secondProgress);
//        progressBar.setVisibility(GONE);
        if (jsEnable) {
            WebSettings setting = webview.getSettings();
            setting.setJavaScriptEnabled(true);
        }
    }

    public void loadUrl(String murl) {
        this.url = murl;
        if (!NetUtil.isNetworkAvailable(getContext())) {
            if (call != null)
                call.onFailLoad(url);
            return;
        }
        webview.loadUrl(murl);
        initListener();
    }

    private void initListener() {
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (call != null)
                    call.onProgressChanged(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (call != null)
                    call.onReceived(title, null, null);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                if (call != null)
                    call.onReceived(null, icon, null);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
                call.onReceived(null, null, url);
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (call != null)
                    call.onStartLoad();
                isError = false;
                progressBar.setVisibility(VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                isError = true;
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                isError = true;

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                isError = true;
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                isError = true;
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (isError && call != null) {
                    call.onFailLoad(url);
                } else if (call != null) {
                    call.onSuccessLoad();
                }
                hideProgressbar();
            }
        });


    }

    public void hideProgressbar() {
        new Handler().postDelayed(new TimerTask() {
            @Override
            public void run() {
                if (progressBar !=null && progressBar.isShown())
                    progressBar.setVisibility(GONE);

            }
        },500);
    }

    public void reLoad() {
        if (webview != null) {
            webview.reload();
        }
    }

    public WebView getWebview() {
        return webview;
    }

    public void onPase() {
        webview.onPause();
    }

    public void onResume() {
        webview.onResume();
    }

    public static final int TYPE_ERROR_NETWORK = 101;
    public static final int TYPE_ERROR_FAILE = 102;

    public interface Callback {
        void onSuccessLoad();

        void onFailLoad(String url);

        void onStartLoad();

        void onReceived(String title, Bitmap Icon, String url);

        void onProgressChanged(int newProgress);
    }

    private Callback call;

    public void setCallback(Callback call) {
        this.call = call;
    }
}

