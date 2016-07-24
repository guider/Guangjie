package com.yanyuanquan.android.automvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by guider on 16/7/18.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class EzListView extends ListView implements AbsListView.OnScrollListener {

    private boolean isLoading = false;
    private boolean hasMore = true;

    public EzListView(Context context) {
        super(context);
    }


    public EzListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EzListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (listener != null && !isLoading && hasMore && scrollState == SCROLL_STATE_IDLE && isLastItemVisible) {
            listener.loadMore();
            isLoading = true;
        }

    }

    private boolean isLastItemVisible;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (listener != null) {
            isLastItemVisible = (totalItemCount > 0) && view.getLastVisiblePosition() == totalItemCount - 1;
        }
    }

    public void setLoadMoreComplete() {
        isLoading = false;
    }

    public void setLoadMoreComplete(boolean hasMore) {
        isLoading = false;
        this.hasMore = hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public interface onLoadMoreLinstener {
        void loadMore();
    }

    private onLoadMoreLinstener listener;

    public void setOnLoadMoreLinstener(onLoadMoreLinstener linstener) {
        this.listener = linstener;
    }

}

