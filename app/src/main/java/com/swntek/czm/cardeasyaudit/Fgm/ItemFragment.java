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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.swntek.czm.cardeasyaudit.MainActivity;
import com.swntek.czm.cardeasyaudit.R;
import com.swntek.czm.cardeasyaudit.ShopAuditActivity;
import com.swntek.czm.cardeasyaudit.adapter.CommonAdapter;
import com.swntek.czm.cardeasyaudit.adapter.ViewHolder;
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
 * Created by caozhimin on 2016/5/19.
 */
public class ItemFragment extends Fragment {
    private Context mContext;
    private TextView tv_content;
    private PullToRefreshListView lv;
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
        lv= (PullToRefreshListView) root.findViewById(R.id.lv);
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lv.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        lv.getLoadingLayoutProxy(false, true).setRefreshingLabel("加载中....");
        lv.getLoadingLayoutProxy(false, true).setReleaseLabel("松手加载");
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                MainActivity mainActivity= (MainActivity) getActivity();
                mainActivity.getNetData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
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
    public void onError(String s){
        lv.onRefreshComplete();
//        ViewInject.toast(getContext().getApplicationContext(),"网络或服务器异常");
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(List<Audit> datas) {
        lv.onRefreshComplete();
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
            lv.setAdapter(new CommonAdapter<Audit>(getContext(),audits,R.layout.item_fgm) {

                @Override
                public void convert(ViewHolder holder, final Audit item, int position) {
                    holder.setText(R.id.tv_ymd, item.getCreate_date().split(" ")[0]);
                    holder.setText(R.id.tv_time, item.getCreate_date().split(" ")[1]);
                    holder.setText(R.id.tv_name, item.getName());
                    String state;
                    if (item.getStatus().equals("1")) {
                        state = "审核通过";
                    } else if (item.getStatus().equals(0)) {
                        state = "待审核";
                    } else {
                        state = "审核未通过";
                    }
                    holder.setText(R.id.tv_state, state);
                    holder.getView(R.id.root).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), ShopAuditActivity.class);
                            intent.putExtra("audit",item);
                            startActivity(intent);
                        }
                    });
                }
            } );
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
