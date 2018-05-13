package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.ShopSortBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopSortActivity extends AppCompatActivity {

    private static final String TAG = "商家分类";
    @BindView(R.id.mList)
    ListView mList;

    private List<ShopSortBean.DataBean> beanList = new ArrayList<>();
    private String sort, sort_id;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_sort);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        OkGo.<String>post("http://sszl.tuoee.com/api/member/get_business_classification")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        ShopSortBean bean = gson.fromJson(response.body(), ShopSortBean.class);
                        beanList = bean.getData();
                        adapter = new MyAdapter();
                        mList.setAdapter(adapter);
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
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(ShopSortActivity.this).inflate(R.layout.item_shop_sort, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            holder.mItem.setText(beanList.get(i).getName());
            holder.mItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("sort",beanList.get(i).getName());
                    bundle.putString("sort_id", String.valueOf(beanList.get(i).getId()));
                    JumpUtil.newInstance().finishRightTrans(ShopSortActivity.this,bundle,002);
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
}
