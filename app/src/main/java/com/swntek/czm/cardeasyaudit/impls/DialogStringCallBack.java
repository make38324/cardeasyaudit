package com.swntek.czm.cardeasyaudit.impls;

import android.app.Activity;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Caozhimin on 2016/6/21.
 * 邮箱:496950806@qq.com
 */
public class DialogStringCallBack<T> extends OkCookieCallBack<T>{
    private IndicatorProgressDialog dialog;
    private Activity activity;
    public DialogStringCallBack(Activity activity){
        this.activity=activity;
        dialog=new IndicatorProgressDialog(activity);
    }
    @Override
    public void onBefore(Request request) {
        dialog.show();
    }

    @Override
    public void onAfter() {
        if(dialog!=null&&activity!=null&&!activity.isFinishing()) {
            dialog.cancel();
            dialog=null;
        }
    }

    @Override
    public T parseNetworkResponse(final Response response) throws Exception {
        super.parseNetworkResponse(response);
        final String t = response.body().string();
        try {
            final int errno = new JSONObject(t).optInt("error", -1);
            parseContent(response, t, errno);
            UIHandler.post(new Runnable() {
                @Override
                public void run() {
                    onUIResponse(response, t, errno);
                }
            });
        }catch (Exception e){
            Log.i("Tag","json异常");
        }
        return null;
    }

    public void onUIResponse(Response response, String t, int errno) {

    }

    public T parseContent(Response response, String t, int errno) {
        return null;
    }
}
