package com.swntek.czm.cardeasyaudit.view;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by Caozhimin on 2016/6/7.
 * 邮箱:496950806@qq.com
 */
public interface UploadAndShowListener {
    void showImage(Bitmap bitmap);
    void uploadImage(Uri uri);
}
