package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.onLineShop.SearchGoodsListActivity;
import com.tuoyi.threebusinesscity.adapter.ClassificationGridAdapter;
import com.tuoyi.threebusinesscity.adapter.ClassificationListAdapter;
import com.tuoyi.threebusinesscity.bean.ShopClassificationInformationBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class OnLineSortFragment extends Fragment {
    private static final String TAG = "三商分类";
    @BindView(R.id.leftBack)
    ImageButton mLeftBack;
    @BindView(R.id.lv_menu)
    ListView mLvMenu;
    @BindView(R.id.gv_home)
    GridView mGvHome;
    Unbinder unbinder;
    private View view;

    private List<ShopClassificationInformationBean.DataBean> dataBean = new ArrayList<>();
    private List<ShopClassificationInformationBean.DataBean.LowerBean> dataBean2 = new ArrayList<>();
    private ClassificationListAdapter listAdapter;
    private ClassificationGridAdapter gridAdapter;


    public static OnLineSortFragment newInstance() {
        OnLineSortFragment fragment = new OnLineSortFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_goods_sort, null);
        }
        unbinder = ButterKnife.bind(this, view);

        //获取分类主列表
        getCI();
        return view;
    }

    /* 获取分类信息 */
    private void getCI() {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/get_category")
                .tag(this)
                .params("num", "10")
                .params("page", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "主分类: " + response.body());
                        Gson gson = new Gson();
                        final ShopClassificationInformationBean bean = gson.fromJson(response.body(), ShopClassificationInformationBean.class);
                        if (bean.getCode() == 200) {
                            dataBean = bean.getData();
                            listAdapter = new ClassificationListAdapter(getActivity().getApplicationContext(), dataBean);
                            mLvMenu.setAdapter(listAdapter);

                            dataBean2 = bean.getData().get(0).getLower();
                            gridAdapter = new ClassificationGridAdapter(getContext(), dataBean2);
                            mGvHome.setAdapter(gridAdapter);
                            mGvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("s", "10分类");
                                    bundle.putString("sort_id", String.valueOf(dataBean2.get(position).getId()));
                                    JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                                }
                            });

                            mLvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    listAdapter.selectedPosition(position);
                                    listAdapter.notifyDataSetInvalidated();
                                    dataBean2 = bean.getData().get(position).getLower();
                                    gridAdapter = new ClassificationGridAdapter(getContext(), dataBean2);
                                    mGvHome.setAdapter(gridAdapter);
                                    mGvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("s", "10分类");
                                            bundle.putString("sort_id", String.valueOf(dataBean2.get(position).getId()));
                                            JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                                        }
                                    });
                                }
                            });

                        } else {
                            ToastUtil.show(getContext(), bean.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
