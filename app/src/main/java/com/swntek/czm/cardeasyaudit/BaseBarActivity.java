package com.swntek.czm.cardeasyaudit;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by caozhimin on 2016/7/4.
 * email：496950806@qq.com
 */
public abstract class BaseBarActivity extends FragmentActivity {
    protected ImageView ivBack;
    protected TextView tvText;
    protected TextView tvRight;

    protected void assignTitleBar(String text) {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvText = (TextView) findViewById(R.id.tv_text);
        tvRight= (TextView) findViewById(R.id.tv_right);
        View.OnClickListener finishlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        ivBack.setOnClickListener(finishlistener);
        tvText.setOnClickListener(finishlistener);
        tvText.setText(text);
    }
}
