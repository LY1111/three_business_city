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
import com.tuoyi.threebusinesscity.bean.UploadImageBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.UriTofilePath;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class BusinessAddGoodsActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.iv_Hygiene)
    ImageView ivHygiene;
    @BindView(R.id.et_GoodsTitle)
    EditText etGoodsTitle;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.tv_subm)
    TextView tv_subm;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    private String mImgUrl;

    private int j=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessadd);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_yes:
                        j=1;
                        LogUtils.e(checkedId+"");
                        break;
                    case R.id.rb_no:
                        j=0;
                        LogUtils.e(checkedId+"");
                        break;
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
                            RxToast.success( bean.getMessage());
                            mImgUrl = bean.getData().getImage_url();
                        }else {
                            RxToast.error( bean.getMessage());
                        }
                    }
                });

    }

    //店铺添加商品
    private void addBusinessGoods() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/add_business_goods")
                .tag(this)
                .params("image", mImgUrl)
                .params("business_token", UserBean.getBusineToken(this))
                .params("name", etGoodsTitle.getText().toString().trim())
                .params("price", etPrice.getText().toString().trim())
                .params("summary", etDescribe.getText().toString().trim())
                .params("status", j+"")
                .params("sort_order", etNumber.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            String message = jsonObject.getString("message");

                            if (jsonObject.getString("code").equals("200")) {
                                RxToast.success(message);
                                setResult(2);
                                RxActivityTool.finishActivity(BusinessAddGoodsActivity.this);
                            } else if (jsonObject.getString("code").equals("400")) {
                                Toast.makeText(BusinessAddGoodsActivity.this, message, Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.getString("code").equals("401")) {
                                Toast.makeText(BusinessAddGoodsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick({R.id.leftBack, R.id.iv_Hygiene, R.id.tv_subm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.iv_Hygiene:
                RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
                dialogChooseImage.show();
                break;
            case R.id.tv_subm:
                addBusinessGoods();
                break;
        }
    }

    private void initMySelfImg(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
                    String path;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        path = UriTofilePath.getFilePathByUri(BusinessAddGoodsActivity.this, data.getData());
                    }else {
                        path = RxPhotoTool.getRealFilePath(this,  data.getData());
                    }
                    File file = new File(path);
                    Log.e("11111111",file+"");
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


