package com.swntek.czm.cardeasyaudit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swntek.czm.cardeasyaudit.pojo.Audit;

public class ShopAuditActivity extends BaseBarActivity {
    private Audit audit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_audit);
        audit= (Audit) getIntent().getSerializableExtra("audit");
        assignTitleBar("商户审核");
    }
}
