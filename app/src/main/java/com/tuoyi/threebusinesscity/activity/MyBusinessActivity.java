package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

/* 商家注册 */
public class MyBusinessActivity extends AppCompatActivity {

    @BindView(R.id.register_title)
    TextView registerTitle;
    @BindView(R.id.tv_lication)
    TextView tvLication;
    @BindView(R.id.tv_shopName)
    TextView tv_shopName;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.et_Contacts)
    EditText etContacts;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;
    @BindView(R.id.et_BankCard)
    EditText et_BankCard;
    @BindView(R.id.et_Password)
    EditText etPassword;
    @BindView(R.id.et_Password2)
    EditText etPassword2;
    @BindView(R.id.mSort)
    TextView mSort;
    @BindView(R.id.et_InvitationCode)
    EditText etInvitationCode;
    @BindView(R.id.iv_Hygiene)
    ImageView ivHygiene;
    @BindView(R.id.iv_ID2)
    ImageView ivID2;
    @BindView(R.id.mBtn)
    Button mBtn;
    @BindView(R.id.mLogin)
    TextView mLogin;
    @BindView(R.id.tv_type)
    TextView mType;
    @BindView(R.id.ll_sort)
    LinearLayout ll_sort;
    @BindView(R.id.ll_class)
    LinearLayout ll_class;
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    private String type, sort;
    private int type_id, sort_id;
    private int type1=3;        //type1=3 个人  type1=2  企业
    private String lon, lat, address, sheng, shi, xian;

    private String mImgUrl = "";
    private String mImgUrlB = "";
    private int i;
    private Uri resultUri;
    private RxDialogChooseImage dialogChooseImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        ButterKnife.bind(this);
         dialogChooseImage = new RxDialogChooseImage(this, TITLE);

         mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (position==0){
                     type1=3;
                 }else if (position==1){
                     type1=2;
                 }
                 LogUtils.e(position+"======="+type1);
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
    }

    @OnClick({/*R.id.mGetCode,*/ R.id.ll_class, R.id.ll_sort, R.id.rl_location, R.id.mBtn, R.id.mLogin, R.id.iv_Hygiene, R.id.iv_ID2,
            R.id.leftBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
           /* case R.id.mGetCode:
                //获取验证码
                break;*/
            case R.id.ll_class:
                //商家类型
                JumpUtil.newInstance().jumpRight(this, ShopTypeActivity.class, 001);
                break;
            case R.id.ll_sort:
                //商家分类
                JumpUtil.newInstance().jumpRight(this, ShopSortActivity.class, 002);
                break;
            case R.id.rl_location:
                //商家地址
                JumpUtil.newInstance().jumpRight(this, MapAddressActivity.class, 003);
                break;
            case R.id.mBtn:
                //注册
                register();
                break;
            case R.id.iv_ID2:
                //添加营业执照
                i = 1;
                dialogChooseImage.show();
                break;
            case R.id.iv_Hygiene:
                i = 2;
                //添加店铺图片
                dialogChooseImage.show();
                break;
            case R.id.leftBack:
                finish();
                break;
            case R.id.mLogin:
                //去往登录
                JumpUtil.newInstance().jumpRight(MyBusinessActivity.this, BusinessLoginActivity.class);
                break;
        }
    }

    private void register() {
        OkGo.<String>post("http://sszl.tuoee.com/api/member/business_register")
                .params("business_type", type1)
                .params("commission_id", type_id)
                .params("classification_id", sort_id)
                .params("pid", etInvitationCode.getText().toString())
                .params("username", etContacts.getText().toString())
                .params("password", etPassword.getText().toString())
                .params("telephone", etPhonenum.getText().toString())
                .params("shop_name", tv_shopName.getText().toString())
                .params("address", tvAddress.getText().toString().trim())
                .params("longitude", lon)
                .params("latitude", lat)
                .params("province", sheng)
                .params("city", shi)
                .params("district", xian)
                .params("business_license", mImgUrl)
                .params("doorhead_photo", mImgUrlB)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            if (jsonObject.getInt("code") == 200) {
                               /* Toast.makeText(MyBusinessActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                String data = jsonObject.getString("data");
                                JSONObject object = new JSONObject(data);
                                UserBean.setToken(MyBusinessActivity.this, object.getString("token"));*/
                                Toast.makeText(MyBusinessActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(MyBusinessActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initMySelfImg(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            switch (requestCode) {
                case 001:
                    type = bundle.getString("type");
                    type_id = bundle.getInt("type_id",0);
                    mType.setText(type);
                    break;
                case 002:
                    sort = bundle.getString("sort");
                    sort_id = bundle.getInt("sort_id",0);
                    mSort.setText(sort);
                    break;
                case 003:
                    lon = bundle.getString("经度");
                    lat = bundle.getString("纬度");
                    sheng = bundle.getString("省");
                    shi = bundle.getString("市");
                    xian = bundle.getString("县");
                    address = bundle.getString("address");
                    tvLication.setText(address);
                    break;
            }
       }
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
                            switch (i){
                                case 1:
                                    mImgUrl = bean.getData().getImage_url();
                                    Log.e("22222222",mImgUrl);
                                    break;
                                case 2:
                                    mImgUrlB = bean.getData().getImage_url();
                                    Log.e("333333333",mImgUrl);
                                    break;
                            }
                        }
                        RxToast.success(bean.getMessage());
                    }
                });
    }


    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {

        Glide.with(this).
                load(uri).
                thumbnail(0.5f).
                into(imageView);

        return (new File(RxPhotoTool.getImageAbsolutePath(this, uri)));
    }


    private void initMySelfImg(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
//                    RxPhotoTool.cropImage(ActivityUser.this, );// 裁剪图片
                    initUCrop(data.getData());
                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                     //data.getExtras().get("data");
//                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }

                break;
            case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理
                resultUri = RxPhotoTool.cropImageUri;
                File tempFile = null;
                LogUtils.e("adassdada1111111111"+i);
                if (i==1){
                    tempFile = roadImageView(resultUri, ivID2);
                }else if (i==2){
                    tempFile = roadImageView(resultUri, ivHygiene);
                }
                LogUtils.e("adassdada1111111111"+tempFile);
                initUploadImage(tempFile);
                //RxToast.success("剪辑成功");
//                RequestUpdateAvatar(new File(RxPhotoTool.getRealFilePath(this, RxPhotoTool.cropImageUri)));
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    //File file = roadImageView(resultUri, mImage);
                    File tempFile1 = null;
                    LogUtils.e("adassdada1111111111"+i);
                    if (i==1){
                        tempFile1 = roadImageView(resultUri, ivID2);
                    }else if (i==2){
                        tempFile1 = roadImageView(resultUri, ivHygiene);
                    }
                    LogUtils.e("adassdada"+tempFile1);
                    initUploadImage(tempFile1);
                    //RxToast.success("剪辑成功");
//                    RxSPTool.putContent(this, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                RxToast.error(cropError.getMessage());
                Log.e("asd", "initMySelfImg: " + cropError.getMessage());
                break;
            default:
                break;
        }
    }

    private void initUCrop(Uri uri) {
        //Uri destinationUri = RxPhotoTool.createImagePathUri(this);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
//        options.setOvalDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(MyBusinessActivity.this);
    }

}
