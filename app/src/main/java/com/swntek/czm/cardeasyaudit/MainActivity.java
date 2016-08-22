package com.swntek.czm.cardeasyaudit;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swntek.czm.cardeasyaudit.Fgm.ItemFragment;
import com.swntek.czm.cardeasyaudit.pojo.Audit;
import com.viewpagerindicator.TabPageIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.ViewInject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseBarActivity {
    private TabPageIndicator tpiIndicator;
    private ViewPager vpPager;
    private static final String[] TITLE = new String[] { "待审核", "审核通过", "审核未通过"};
    private List<Audit> audit=new ArrayList<>();
    private TabPageIndicatorAdapter adapter;

    private void assignViews() {
        tpiIndicator = (TabPageIndicator) findViewById(R.id.tpi_indicator);
        vpPager = (ViewPager) findViewById(R.id.vp_pager);
        assignTitleBar("店铺申请");
        ivBack.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip);
        assignViews();
        adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapter);
        tpiIndicator.setViewPager(vpPager);
        getNetData();
    }

    public void getNetData() {
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.setConnectTimeout(4000, TimeUnit.SECONDS);
        okHttpUtils.post().url(ServerUrl.baseurl+"api/application_list.php").build().execute(new StringCallback() {
            @Override
            public void onResponse(String response) {
                EventBus.getDefault().postSticky(audit);
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                EventBus.getDefault().postSticky("网路连接失败");
            }

            @Override
            public String parseNetworkResponse(Response response) throws IOException {
                String t=response.body().string();
//                String t="[{\"manager\":\"傻蛋\",\"name\":\"美发\",\"address\":\"地方\",\"phone\":\"2131\",\"status\":\"1\",\"create_date\":\"2016-08-23 16:11:26\",\"remark\":\"佛挡杀佛\"}]";
                try{
                    JSONObject jsonobj=new JSONObject(t);
                    JSONArray jsonArray=jsonobj.optJSONArray("results");
                    audit.clear();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Gson gson=new Gson();
                        Audit singleaudit = gson.fromJson(jsonObject.toString(),Audit.class);
                        audit.add(singleaudit);
                    }
                    Log.e("Tag",audit.size()+"");
                }catch (Exception e){
                    Log.e("Tag","json解析异常");
                }
                return t;
            }
        });
    }

    /**
     * ViewPager适配器
     * @author len
     *
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = ItemFragment.newInstance(getApplicationContext(),position);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }
}
