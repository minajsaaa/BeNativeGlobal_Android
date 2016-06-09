package com.smatoos.nobug.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;

import com.smatoos.b2b.R;

public class AlertUtil {

    public static AlertDialog alert(Context context, int themeResId, String title, String message) {
        AlertDialog.Builder dialog = themeResId == 0 ? new AlertDialog.Builder(context) : new AlertDialog.Builder(context, themeResId);
        dialog.setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setTitle(title);
        dialog.setMessage(message);
        return dialog.show();
    }

    public static AlertDialog alertOk(Context context, String title, String message, String confirm, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        if( title != null ) {
            dialog.setTitle(title);
        }

        dialog.setMessage(message).setCancelable(false);
        String onText = confirm != null ? confirm : context.getString(R.string.alert_confirm);
        dialog.setPositiveButton(onText, listener);

        dialog.setNegativeButton(R.string.alert_cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return dialog.show();
    }

    public static AlertDialog alertOkCancel(Context context, String title, String message, String okText, String cacelText, boolean isCancel,
                                            DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(isCancel);

        if( title != null ) {
            dialog.setTitle(title);
        }

        dialog.setMessage(message).setCancelable(false);

        if( okListener != null ) {
            okText = okText != null ? okText : context.getString(R.string.alert_confirm);
            dialog.setPositiveButton(okText, okListener);
        }

        if( cancelListener != null ) {
            cacelText = cacelText != null ? cacelText : context.getString(R.string.alert_cancel);
            dialog.setNegativeButton(cacelText, cancelListener);
        }

        return dialog.show();
    }

//  ================================================================================================

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context) {
        showProgressDialog(context, context.getString(R.string.default_loading_comment));
    }

    public static void showProgressDialog(Context context, String value) {
        if (NetworkUtil.isConnected(context)) {
            dismissProgressDialog();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(value != null ? value : context.getString(R.string.default_loading_comment));
            progressDialog.show();
        }
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
