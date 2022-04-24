package com.example.goodplanet.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.goodplanet.R;

public class Utils {
    /**
     * 隐藏软键盘
     *
     * @param activity
     *            要隐藏软键盘的activity
     */
    public static void hideSoftKeyBoard(Activity activity) {
        final View v = activity.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            try {
                ((InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(activity.getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Dialog showProcessDialog(Context context, String content) {
        Dialog customDialog = new Dialog(context,
                R.style.Base_V7_Theme_AppCompat_Dialog);
        customDialog.setContentView(R.layout.process_dialog);
        customDialog.findViewById(R.id.piv_loading_process).setVisibility(
                View.VISIBLE);
        ((TextView) customDialog.findViewById(R.id.tv_process))
                .setText(content);
        customDialog.show();
        return customDialog;
    }

}
