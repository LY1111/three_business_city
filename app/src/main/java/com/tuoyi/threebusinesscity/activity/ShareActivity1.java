package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.GetShareAddressBean;
import com.tuoyi.threebusinesscity.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.util.LogUtils;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareActivity1 extends AppCompatActivity {
    @BindView(R.id.share_btA)
    Button shareBtA;
    @BindView(R.id.share_btB)
    Button shareBtB;
    private String address1,address2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share1);
        ButterKnife.bind(this);
        getOrderDetail();
    }

    @OnClick({R.id.share_btA, R.id.share_btB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_btA:
                //if (!TextUtils.isEmpty(address)){
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, address2);
                    shareIntent.setType("text/*");
                    startActivity(Intent.createChooser(shareIntent, "分享到"));
                //}
                break;
            case R.id.share_btB:
                //if (!TextUtils.isEmpty(address)){
                Intent shareIntent2 = new Intent();
                shareIntent2.setAction(Intent.ACTION_SEND);
                shareIntent2.putExtra(Intent.EXTRA_TEXT, address1);
                shareIntent2.setType("text/*");
                startActivity(Intent.createChooser(shareIntent2, "分享到"));
                //showShare();
                //}
                break;
        }
    }

    private void getOrderDetail() {
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/sharing_links")
                .tag(this)
                .params("token", UserBean.getToken(ShareActivity1.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.debug(response.body());
                        Gson gson = new Gson();
                        GetShareAddressBean orderDetailBean = gson.fromJson(response.body(), GetShareAddressBean.class);
                        if (orderDetailBean.getCode() == 200) {
                            address1=orderDetailBean.getData().getSharing_links_business();
                            address2=orderDetailBean.getData().getSharing_links_member();
                        } else {

                        }
                        Toast.makeText(ShareActivity1.this, orderDetailBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("测试测试测测试");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        //网络图片的url：所有平台
        oks.setImageUrl("http://sszl.tuoee.com/public/uploads/images/osc1/adafaaf.jpg");//网络图片rul
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }

}
