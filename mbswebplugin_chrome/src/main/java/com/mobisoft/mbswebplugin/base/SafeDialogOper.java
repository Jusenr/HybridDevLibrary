package com.mobisoft.mbswebplugin.base;

/**
 * Author：Created by fan.xd on 2017/3/14.
 * Email：fang.xd@mobisoft.com.cn
 * Description：
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import com.mobisoft.mbswebplugin.utils.LogUtils;

/**
 * To safely operation dialog show and hide
 *
 * @author lzh
 */
public class SafeDialogOper {

    /**
     * To show a dialog be safety
     *
     * @param dialog The dialog instance to be shown
     */
    public static void safeShowDialog(Dialog dialog) {
        LogUtils.e("safeShowDialog", "safeShowDialog");
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        Activity bindAct = getActivity(dialog);

        if (bindAct == null || bindAct.isFinishing()) {
            LogUtils.d("Dialog shown failed:", "The Dialog bind's Activity was recycled or finished!");
            return;
        }

        dialog.show();
    }

    private static Activity getActivity(Dialog dialog) {
        Activity bindAct = null;
        Context context = dialog.getContext();
        do {
            if (context instanceof Activity) {
                bindAct = (Activity) context;
                break;
            } else if (context instanceof ContextThemeWrapper) {
                context = ((ContextThemeWrapper) context).getBaseContext();
            } else {
                break;
            }
        } while (true);
        return bindAct;
    }

    /**
     * to dismiss a dialog safety
     *
     * @param dialog The dialog to be hide
     */
    public static void safeDismissDialog(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }

        Activity bindAct = getActivity(dialog);
        if (bindAct != null && !bindAct.isFinishing()) {
            dialog.dismiss();
        }
    }
}
