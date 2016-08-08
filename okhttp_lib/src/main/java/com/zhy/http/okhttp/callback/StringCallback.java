package com.zhy.http.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zhy on 15/12/14.
 */
public class StringCallback implements Callback<String>
{
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
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(String response) {

    }

}
