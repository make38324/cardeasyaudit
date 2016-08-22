package com.swntek.czm.cardeasyaudit.impls;

import com.swntek.czm.cardeasyaudit.util.Define;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Caozhimin on 2016/6/22.
 * 邮箱:496950806@qq.com
 */
public class OkCookieCallBack<T> implements Callback<T> {
    @Override
    public void onBefore(Request request) {

    }

    @Override
    public void onAfter() {

    }

    @Override
    public void inProgress(float progress) {

    }

    @Override
    public T parseNetworkResponse(final Response response) throws Exception {
        return null;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(T response){

    }
}
