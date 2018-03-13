package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.MyPromoterAdapter;
import com.tuoyi.threebusinesscity.bean.MyPromoterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的推广商家爱
 */
public class MyPromoterActivity extends AppCompatActivity {

    @BindView(R.id.my_promoter_back)
    ImageView mBack;
    @BindView(R.id.my_promoter_image)
    CircleImageView mImage;
    @BindView(R.id.my_promoter_rv_list)
    RecyclerView mRvList;
    private List<MyPromoterBean> beanList;
    private MyPromoterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_promoter);
        ButterKnife.bind(this);
        initList();
    }
    /**
     * 我的推广商家列表
     */
    private void initList() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            beanList.add(new MyPromoterBean("用户" + i));
        }
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyPromoterAdapter(beanList);
        mRvList.setAdapter(mAdapter);
    }


    @OnClick(R.id.my_promoter_back)
    public void onViewClicked() {
    }
}
