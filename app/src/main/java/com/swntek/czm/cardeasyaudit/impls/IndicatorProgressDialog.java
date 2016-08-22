package com.swntek.czm.cardeasyaudit.impls;

import android.app.Dialog;
import android.content.Context;

import com.swntek.czm.cardeasyaudit.R;

/**
 * Created by caozhimin on 2015/12/16.
 */
public class IndicatorProgressDialog extends Dialog {
    private boolean isOnDetach=false;
    public IndicatorProgressDialog(Context context) {
        super(context, R.style.nobackDialog);
        setCancelable(false);
        setContentView(R.layout.dialog_layout);
    }

    public IndicatorProgressDialog(Context context, int themeResId) {
        super(context, R.style.nobackDialog);
        setCancelable(false);
        setContentView(R.layout.dialog_layout);
    }

    public IndicatorProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setCancelable(false);
    }

    @Override
    public void cancel() {
        if(!isOnDetach) {
            super.cancel();
        }
    }

    @Override
    public void onDetachedFromWindow() {
//        isOnDetach=true;
        super.onDetachedFromWindow();
    }
}
