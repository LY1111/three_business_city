package com.tuoyi.threebusinesscity.activity.business;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BusineseeAdminBean;
import com.tuoyi.threebusinesscity.bean.UploadImageBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.UriTofilePath;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;
import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class ModifyGoodsActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.tv_subm)
    TextView tvSubm;
    @BindView(R.id.iv_Hygiene)
    ImageView ivHygiene;
    @BindView(R.id.et_GoodsTitle)
    EditText etGoodsTitle;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.et_describe)
    EditText etDescribe;

    private BusineseeAdminBean.DataBean dataBean;
    private String imgurl;
    private int status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifgodds);
        RxActivityTool.addActivity(this);
        ButterKnife.bind(this);
        dataBean = (BusineseeAdminBean.DataBean) getIntent().getSerializableExtra("bean");

        etGoodsTitle.setText(dataBean.getName());
        etPrice.setText("¥ " + dataBean.getPrice());
        Glide.with(this).load(IMGS + dataBean.getImage()).into(ivHygiene);  //商品图片
        imgurl = dataBean.getImage();
        etDescribe.setText(dataBean.getSummary());
        etNumber.setText(dataBean.getSort_order()+"");
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_yes:
                        status = checkedId;
                        Log.e("222222qweuqwourqw", checkedId + "");
                        break;
                    case R.id.rb_no:
                        status = checkedId;
                        Log.e("222222qweuqwourqw", checkedId + "");
                        break;
                }
            }
        });

    }

    @OnClick({R.id.leftBack, R.id.tv_subm, R.id.iv_Hygiene})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                RxActivityTool.finishActivity(this);
                break;
            case R.id.tv_subm:
                initOkGo();
                break;
            case R.id.iv_Hygiene:
                RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
                dialogChooseImage.show();
                break;
        }
    }

    private void initOkGo() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/update_business_goods_one")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(ModifyGoodsActivity.this))
                .params("goods_id", dataBean.getGoods_id())
                .params("image", imgurl)
                .params("name", etGoodsTitle.getText().toString())
                .params("price", etPrice.getText().toString())
                .params("status", status)
                .params("sort_order", etNumber.getText().toString())
                .params("summary", etDescribe.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("修改商品数据", "onSuccess11111: " + response.body());
                        Gson gson = new Gson();
                        BusineseeAdminBean bean = gson.fromJson(response.body(), BusineseeAdminBean.class);
                        if (bean.getCode() == 200) {
                            RxActivityTool.finishActivity(ModifyGoodsActivity.this);
                        } else {
                            Toast.makeText(ModifyGoodsActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initUploadImage(File phoneStr) {
        OkGo.<String>post(Config.s + "api/App/upload_image")
                .tag(this)
                .params("upload", phoneStr)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("2222222" + response.body());
                        Gson gson = new Gson();
                        UploadImageBean bean = gson.fromJson(response.body(), UploadImageBean.class);
                        if (bean.getCode() == 200) {
                            imgurl = bean.getData().getImage_url();
                        }
                        // Toast.makeText(ModifyGoodsActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initMySelfImg(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
                    String path;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        path = UriTofilePath.getFilePathByUri(ModifyGoodsActivity.this, data.getData());
                    } else {
                        path = RxPhotoTool.getRealFilePath(this, data.getData());
                    }
                    File file = new File(path);
                    Log.e("11111111", file + "");
                    initUploadImage(file);
                    Glide.with(this).load(file).into(ivHygiene);

                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    String path = RxPhotoTool.getRealFilePath(this, RxPhotoTool.imageUriFromCamera);
                    File file = new File(path);
                    initUploadImage(file);
                    Glide.with(this).load(file).into(ivHygiene);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initMySelfImg(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
