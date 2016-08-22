package com.swntek.czm.cardeasyaudit.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.swntek.czm.cardeasyaudit.R;
import com.swntek.czm.cardeasyaudit.view.UploadAndShowListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Caozhimin on 2016/6/7.
 * 邮箱:496950806@qq.com
 */
public class SelectPicManager {
    private Activity mActivity;
    private View view;
    private Uri uri;
    private UploadAndShowListener uploadAndShowListener;
    public SelectPicManager(Activity activity, View dropview){
        this.mActivity=activity;
        this.view=dropview;
    }
    public SelectPicManager(Activity activity){
        this.mActivity=activity;
    }
    public void setUploadAndShowListener(UploadAndShowListener uploadAndShowListener) {
        this.uploadAndShowListener = uploadAndShowListener;
    }


    public void fromCamer() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            mActivity.startActivityForResult(getImageByCamera, Define.REQUEST_CODE_CAPTURE_CAMEIA);
        } else {
            Toast.makeText(mActivity.getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }
    public void fromPic() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.startActivityForResult(intent, Define.SelectLogo);
    }
    public void showAndUploadImage(int requestCode, Intent data) {
        if (requestCode == Define.SelectLogo) {
            uri = data.getData();
            if(uploadAndShowListener!=null){
                uploadAndShowListener.uploadImage(uri);
                Bitmap bitmap = getBitmap(uri);
                uploadAndShowListener.showImage(bitmap);
            }
        }else if(requestCode==Define.REQUEST_CODE_CAPTURE_CAMEIA){
            uri = data.getData();
            if(uri == null){
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap photo = (Bitmap) bundle.get("data"); //get bitmap
                    saveImage(photo,mActivity.getFilesDir()+ File.separator+"a.png");
                } else {
                    Toast.makeText(mActivity.getApplicationContext(), "err****", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        return;
    }
    private void saveImage(Bitmap photo, String spath) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            uri= Uri.fromFile(new File(spath));
            if(uploadAndShowListener!=null) {
                uploadAndShowListener.uploadImage(uri);
                Bitmap bitmap = getBitmap(uri);
                uploadAndShowListener.showImage(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    private Bitmap getBitmap(Uri uri) {
        ContentResolver cr = mActivity.getContentResolver();
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(this.uri));
            return bitmap;
        } catch (FileNotFoundException e) {
            Log.e("Exception", e.getMessage(), e);
        }
        return null;
    }
}
