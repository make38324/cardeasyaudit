package com.swntek.czm.cardeasyaudit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.swntek.czm.cardeasyaudit.impls.DialogStringCallBack;
import com.swntek.czm.cardeasyaudit.pojo.Audit;
import com.swntek.czm.cardeasyaudit.view.MyItemView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.ui.ViewInject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class ShopAuditActivity extends BaseBarActivity {
    private Audit audit;
    private MyItemView mivBossname;
    private MyItemView mivManagename;
    private MyItemView mivPhone;
    private MyItemView mivServicetype;
    private MyItemView mivAddress;
    private ImageView ivLeft01;
    private TextView tvText;
    private RadioGroup rgMosi;
    private RadioButton rbLsd;
    private RadioButton rbDd;
    private MyItemView mivFarenname;
    private MyItemView mivFarenshenfen;
    private ImageView ivYyzz;
    private ImageView ivShenfen01;
    private ImageView ivShenfen02;
    private Button btn_submit;
    private void assignViews() {
        mivBossname = (MyItemView) findViewById(R.id.miv_bossname);
        mivManagename = (MyItemView) findViewById(R.id.miv_managename);
        mivPhone = (MyItemView) findViewById(R.id.miv_phone);
        mivServicetype = (MyItemView) findViewById(R.id.miv_servicetype);
        mivAddress = (MyItemView) findViewById(R.id.miv_address);
        ivLeft01 = (ImageView) findViewById(R.id.iv_left01);
        tvText = (TextView) findViewById(R.id.tv_text);
        rgMosi = (RadioGroup) findViewById(R.id.rg_mosi);
        rbLsd = (RadioButton) findViewById(R.id.rb_lsd);
        rbDd = (RadioButton) findViewById(R.id.rb_dd);
        mivFarenname = (MyItemView) findViewById(R.id.miv_farenname);
        mivFarenshenfen = (MyItemView) findViewById(R.id.miv_farenshenfen);
        ivYyzz = (ImageView) findViewById(R.id.iv_yyzz);
        ivShenfen01 = (ImageView) findViewById(R.id.iv_shenfen01);
        ivShenfen02 = (ImageView) findViewById(R.id.iv_shenfen02);
        btn_submit= (Button) findViewById(R.id.btn_submit);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_audit);
        audit= (Audit) getIntent().getSerializableExtra("audit");
        assignViews();
        assignTitleBar("商户审核");
        initView();
        initListener();
    }

    private void initListener() {
        ivShenfen01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckImageActivity.lanuch(ShopAuditActivity.this);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mivBossname.getValue();
//                mivAddress.getValue();
//                mivManagename.getValue();
//                mivPhone.getValue();
//                mivServicetype.getValue();
                String farenname=mivFarenname.getValue();
                String farenshenfen=mivFarenshenfen.getValue();
                String id=audit.getId();
                RequestCall build = OkHttpUtils.post().url(ServerUrl.baseurl+"api/application_list.php").addParams("id", farenname).addParams("id", farenshenfen)
                        .addParams("shop_id", id).addParams("pid",id).build();
                build.execute(new DialogStringCallBack(ShopAuditActivity.this) {
                    @Override
                    public String parseNetworkResponse(Response response) throws IOException {
                        String t=response.body().string();
                        String info="数据返回错误";
                        try {
                            JSONObject jsonObject=new JSONObject(t);
                             info=jsonObject.optString("info");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return info;
                    }

                    @Override
                    public void onResponse(Object response) {
                        ViewInject.toast(getApplicationContext(),response.toString());
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        ViewInject.toast(getApplicationContext(),"网络异常");
                    }
                });
            }
        });
    }

    private void initView() {
        mivBossname.setValue(audit.getManager());//老板名字
        mivAddress.setValue(audit.getDetail_addr());//详细地址
        mivManagename.setValue(audit.getName());//公司名称
        mivPhone.setValue(audit.getPhone());//手机号
        mivServicetype.setValue(audit.getC_name());
    }
}
