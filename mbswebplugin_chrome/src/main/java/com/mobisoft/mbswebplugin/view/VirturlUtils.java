package com.mobisoft.mbswebplugin.view;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Email      : jusenr@163.com
 * Author     : Fade龖龘 原文：https://blog.csdn.net/qq_36255612/article/details/79557527
 * Date       : 2019/04/26
 * Time       : 12:01
 * Project    ：weblibrary.
 */
public class VirturlUtils {
    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    private View mViewObserved;//被监听的视图
    private int usableHeightPrevious;//视图变化前的可用高度
    private ViewGroup.LayoutParams frameLayoutParams;

    /**
     * 关联要监听的视图
     *
     * @param viewObserving 要监听的视图
     */
    public void assistActivity(View viewObserving) {
        mViewObserved = viewObserving;
        //给View添加全局的布局监听器
        mViewObserved.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int usableHeightNow = computeUsableHeight();
                //比较布局变化前后的View的可用高度
                if (usableHeightNow != usableHeightPrevious) {
                    //如果两次高度不一致
                    //将当前的View的可用高度设置成View的实际高度
                    frameLayoutParams.height = usableHeightNow;
                    mViewObserved.requestLayout();//请求重新布局
                    usableHeightPrevious = usableHeightNow;
                }

                mViewObserved.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        frameLayoutParams = mViewObserved.getLayoutParams();
    }

    /**
     * 计算视图可视高度
     *
     * @return
     */
    private int computeUsableHeight() {
        Rect r = new Rect();
        mViewObserved.getWindowVisibleDisplayFrame(r);
        return (r.bottom);
    }
}