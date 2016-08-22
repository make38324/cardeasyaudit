//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.swntek.czm.cardeasyaudit.impls;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

public class UIHandler {
    private static Handler handler = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message msg) {
            return UIHandler.handleMessage(msg);
        }
    });

    public UIHandler() {
    }

    private static boolean handleMessage(Message msg) {
        Object[] objs = (Object[])((Object[])msg.obj);
        Message inner = (Message)objs[0];
        Callback callback = (Callback)objs[1];
        return callback != null?callback.handleMessage(inner):false;
    }

    private static Message getShellMessage(Message msg, Callback callback) {
        Message shell = new Message();
        shell.what = 1;
        shell.obj = new Object[]{msg, callback};
        return shell;
    }

    private static Message getShellMessage(int what, Callback callback) {
        Message msg = new Message();
        msg.what = what;
        return getShellMessage(msg, callback);
    }

    public static boolean sendMessage(Message msg, Callback callback) {
        return handler.sendMessage(getShellMessage(msg, callback));
    }

    public static boolean sendMessageDelayed(Message msg, long delayMillis, Callback callback) {
        return handler.sendMessageDelayed(getShellMessage(msg, callback), delayMillis);
    }

    public static boolean sendMessageAtTime(Message msg, long uptimeMillis, Callback callback) {
        return handler.sendMessageAtTime(getShellMessage(msg, callback), uptimeMillis);
    }

    public static boolean sendMessageAtFrontOfQueue(Message msg, Callback callback) {
        return handler.sendMessageAtFrontOfQueue(getShellMessage(msg, callback));
    }

    public static boolean sendEmptyMessage(int what, Callback callback) {
        return handler.sendMessage(getShellMessage(what, callback));
    }

    public static boolean sendEmptyMessageAtTime(int what, long uptimeMillis, Callback callback) {
        return handler.sendMessageAtTime(getShellMessage(what, callback), uptimeMillis);
    }

    public static boolean sendEmptyMessageDelayed(int what, long delayMillis, Callback callback) {
        return handler.sendMessageDelayed(getShellMessage(what, callback), delayMillis);
    }

    public static boolean post(Runnable r) {
        return handler.post(r);
    }

    public static boolean postAtFrontOfQueue(Runnable r) {
        return handler.postAtFrontOfQueue(r);
    }

    public static boolean postDelayed(Runnable r, long delayMillis) {
        return handler.postDelayed(r, delayMillis);
    }

    public static boolean postAtTime(Runnable r, long uptimeMillis) {
        return handler.postAtTime(r, uptimeMillis);
    }

    public static boolean postAtTime(Runnable r, Object token, long uptimeMillis) {
        return handler.postAtTime(r, token, uptimeMillis);
    }

    public static void removeCallbacks(Runnable r) {
        handler.removeCallbacks(r);
    }

    public static void removeAllMessages() {
        handler.removeMessages(1);
    }

    public static void removeMessages(int what) {
        handler.removeMessages(what);
    }
}
