package com.swntek.czm.cardeasyaudit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.swntek.czm.cardeasyaudit.util.SelectPicManager;
import com.swntek.czm.cardeasyaudit.view.UploadAndShowListener;

import org.kymjs.kjframe.ui.ViewInject;

public class CheckImageActivity extends BaseBottomActivity {
    private Button btn_cancel;
    private TextView tv_albumselect;
    private TextView tv_camerselect;
    private SelectPicManager picManager;
    public static void lanuch(Context context){
        Intent intent=new Intent(context,CheckImageActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_image);
        btn_cancel= (Button) findViewById(R.id.btn_cancel);
        tv_albumselect= (TextView) findViewById(R.id.tv_albumselect);
        tv_camerselect= (TextView) findViewById(R.id.tv_camerselect);
        picManager=new SelectPicManager(this);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_albumselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picManager.fromPic();
            }
        });
        tv_camerselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picManager.fromCamer();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        picManager.setUploadAndShowListener(new UploadAndShowListener() {
            @Override
            public void showImage(Bitmap bitmap) {

            }
            @Override
            public void uploadImage(Uri uri) {
                ViewInject.toast(getApplicationContext(),"正在上传...");
            }
        });
        picManager.showAndUploadImage(requestCode,data);
    }
}
