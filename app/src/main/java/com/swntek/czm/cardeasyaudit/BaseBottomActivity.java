package com.swntek.czm.cardeasyaudit;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by Caozhimin on 2016/6/5.
 * 邮箱:496950806@qq.com
 */
public abstract class BaseBottomActivity extends Activity {
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
