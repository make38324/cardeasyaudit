package com.swntek.czm.cardeasyaudit.Fgm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.swntek.czm.cardeasyaudit.R;
import com.swntek.czm.cardeasyaudit.ShopAuditActivity;
import com.swntek.czm.cardeasyaudit.pojo.Audit;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public class ItemFragment extends Fragment {
    private Context mContext;
    private TextView tv_content;
    private ListView lv;
    private int index;
    private List<Audit> audits;

    public static ItemFragment newInstance(Context context,int index) {
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.index=index;
        itemFragment.setmContext(context);
        return itemFragment;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.lv, container, false);
        lv= (ListView) root.findViewById(R.id.lv);
//        tv_content= (TextView) root.findViewById(R.id.tv_text);
//        String title=getArguments().getString("title");
//        tv_content.setText(title);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(List<Audit> datas) {
        if(datas!=null&&datas.size()>0) {
            audits = new ArrayList<>();
            if (index == 0) {
                audits.clear();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getStatus().equals("0")) {
                        audits.add(datas.get(i));
                    }
                }
            } else if (index == 1) {
                audits.clear();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getStatus().equals("1")) {
                        audits.add(datas.get(i));
                    }
                }
            } else {
                audits.clear();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getStatus().equals("2")) {
                        audits.add(datas.get(i));
                    }
                }
            }
            lv.setAdapter(new KJAdapter<Audit>(lv, audits, R.layout.item_fgm) {
                @Override
                public void convert(AdapterHolder helper, final Audit item, boolean isScrolling, int position) {
                    helper.setText(R.id.tv_ymd, item.getCreate_date().split(" ")[0]);
                    helper.setText(R.id.tv_time, item.getCreate_date().split(" ")[1]);
                    helper.setText(R.id.tv_name, item.getName());
                    String state;
                    if (item.getStatus().equals("1")) {
                        state = "审核通过";
                    } else if (item.getStatus().equals(0)) {
                        state = "待审核";
                    } else {
                        state = "审核未通过";
                    }
                    helper.setText(R.id.tv_state, state);
                    helper.getView(R.id.root).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), ShopAuditActivity.class);
                            intent.putExtra("audit",item);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
