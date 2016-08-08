package com.zhy.http.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public interface Callback<T>
{
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request);

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter();

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress);
    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);


    Callback CALLBACK_DEFAULT = new Callback()
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
        public Object parseNetworkResponse(Response response) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e)
        {

        }

        @Override
        public void onResponse(Object response)
        {

        }
    };

}