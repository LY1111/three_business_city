package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ShopTypeAdapter;
import com.tuoyi.threebusinesscity.bean.ShopTypeBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopTypeActivity extends AppCompatActivity {

    private static final String TAG = "商家类型";
    @BindView(R.id.mList)
    ListView mList;
    @BindView(R.id.mOk)
    Button mOk;

    private ShopTypeAdapter adapter;
    private List<ShopTypeBean.DataBean> beanList = new ArrayList<>();
    private List<String> type_ids = new ArrayList<>();
    private LinkedHashMap<Integer, Boolean> map = new LinkedHashMap <>();

    private int type_id;
    private MyAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type);
        ButterKnife.bind(this);
        type_id=getIntent().getIntExtra("pos",0);
        Log.e("wwwwwwwwww", "商家类型qqqqqqqqqqq: "+type_id );
        getData();
    }

    private void getData() {
        OkGo.<String>post(com.tuoyi.threebusinesscity.url.Config.s+"api/member/get_business_commission")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.d(response.body());
//                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        ShopTypeBean bean = gson.fromJson(response.body(), ShopTypeBean.class);
                        if (bean.getCode() == 200) {
                           beanList = bean.getData();
                           /*  Log.d(TAG, "handleMessage: " + beanList);
                            for (int i = 0; i < beanList.size(); i++) {
                                type_ids.add(String.valueOf(beanList.get(i).getId()));
                            }
                            adapter = new ShopTypeAdapter(ShopTypeActivity.this, beanList);

                            mList.setAdapter(adapter);*/

                            adapter1=new MyAdapter();
                            mList.setAdapter(adapter1);
                        } else {
                            Toast.makeText(ShopTypeActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public Object getItem(int i) {
            return beanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return beanList != null ? beanList.size() : 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
           MyAdapter.ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(ShopTypeActivity.this).inflate(R.layout.item_shop_sort, null);
                holder = new MyAdapter.ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (MyAdapter.ViewHolder) view.getTag();
            }

            if (beanList.get(i).getId()==type_id){
                holder.mItem.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            holder.mItem.setText(beanList.get(i).getName());
            holder.mItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type",beanList.get(i).getName());
                    bundle.putInt("type_id", beanList.get(i).getId());
                    JumpUtil.newInstance().finishRightTrans(ShopTypeActivity.this,bundle,001);
                }
            });
            return view;
        }

        class ViewHolder {
            public View rootView;
            public TextView mItem;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.mItem = (TextView) rootView.findViewById(R.id.mItem);
            }

        }
    }

    /*@OnClick(R.id.mOk)
    public void onViewClicked() {
        type_id = null;
        type = null;
        map = adapter.getMap();
        for (int position : map.keySet()) {
            if (map.get(position)) {
                if (type_id == null) {
                    type_id = type_ids.get(position);
                } else {
                    type_id = type_ids.get(position) + "," + type_id;
                }
                if (type == null){
                    type = beanList.get(position).getName();
                }else {
                    type = beanList.get(position).getName() + "," + type;
                }
            }
            Log.e(TAG, "onViewClicked: " + position );
        }
        Log.i(TAG, "onViewClicked: " + type_id);
        Log.d(TAG, "onViewClicked: " + type);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        bundle.putString("type_id",type_id);
        JumpUtil.newInstance().finishRightTrans(this,bundle,001);
//        RxActivityTool.skipActivityAndFinish(ShopTypeActivity.this,MyBusinessActivity.class,bundle);
    }*/
}
