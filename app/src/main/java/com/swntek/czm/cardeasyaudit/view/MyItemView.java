package com.swntek.czm.cardeasyaudit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swntek.czm.cardeasyaudit.R;


/**
 * Created by caozhimin on 2016/7/4.
 * email：496950806@qq.com
 */
public class MyItemView extends LinearLayout{
    private  ImageView jiantou;
    private  ImageView leftimg;
    private  TextView tv;
    private EditText bet;
    public MyItemView(Context context) {
        super(context);
    }

    public MyItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Swntekczm);
        Drawable drawable = ta.getDrawable(R.styleable.Swntekczm_img);
        String text=ta.getString(R.styleable.Swntekczm_text);
        String btext=ta.getString(R.styleable.Swntekczm_btext);
        boolean showjiantou=ta.getBoolean(R.styleable.Swntekczm_showjt,false);
        boolean noedit=ta.getBoolean(R.styleable.Swntekczm_noedit,false);
        ta.recycle();
        LayoutInflater.from(context).inflate(R.layout.view_myitem, this);
        leftimg = (ImageView) findViewById(R.id.iv_left01);
        tv = (TextView) findViewById(R.id.tv_text);
        jiantou = (ImageView)findViewById(R.id.iv_jiantou);
        bet= (EditText) findViewById(R.id.tv_btext);
        if(drawable!=null){
            leftimg.setImageDrawable(drawable);
        }else {
            leftimg.setVisibility(GONE);
        }
        if(noedit){
            bet.setKeyListener(null);
            bet.setFocusable(false);
            bet.setFocusableInTouchMode(false);
            bet.setEnabled(false);
        }else{
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    bet.setFocusable(true);
                    bet.requestFocus();
                }
            });
        }
        if(showjiantou){
            jiantou.setVisibility(VISIBLE);
        }else {
            jiantou.setVisibility(INVISIBLE);
        }
        tv.setText(text);
        bet.setHint(btext);
    }

    public MyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageView getLeftimg() {
        return leftimg;
    }

    public TextView getTv() {
        return tv;
    }

    public ImageView getJiantou() {
        return jiantou;
    }

    public EditText getBtv() {
        return bet;
    }
    public String getValue(){
        return bet.getText().toString();
    }
    public void setValue(String value){
        bet.setText(value);
    }
}
