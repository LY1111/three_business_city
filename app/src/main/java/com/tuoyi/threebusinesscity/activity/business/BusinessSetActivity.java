package com.tuoyi.threebusinesscity.activity.business;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.MapAddressActivity;
import com.tuoyi.threebusinesscity.activity.ShopSortActivity;
import com.tuoyi.threebusinesscity.activity.ShopTypeActivity;
import com.tuoyi.threebusinesscity.bean.BusinessMsgBean;
import com.tuoyi.threebusinesscity.bean.UploadImageBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class BusinessSetActivity extends AppCompatActivity {
    @BindView(R.id.busset_Back)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.tv_shopName)
    EditText tvShopName;
    @BindView(R.id.tv_lication)
    TextView tvLication;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.et_Contacts)
    EditText etContacts;
    @BindView(R.id.ll_class)
    LinearLayout llClass;
    @BindView(R.id.tv_sort)
    TextView tv_sort;
    @BindView(R.id.mBtn)
    Button mBtn;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.head_image)
    CircleImageView headImage;
    @BindView(R.id.rl_head_img)
    RelativeLayout rlHeadImg;
    @BindView(R.id.banner_img)
    ImageView bannerImg;
    @BindView(R.id.ll_banner)
    LinearLayout llBanner;
    private String sort;
    private String type;
    private String lon, lat, address, sheng, shi, xian;
    private String headImgurl;
    private String imgurl;
    private int succ, type_id, sort_id;
    private Uri resultUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessset);
        RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);
        getBusinessMsg();
    }

    @OnClick({R.id.busset_Back, R.id.rl_location, R.id.ll_class, R.id.mBtn, R.id.ll_type,R.id.rl_head_img, R.id.ll_banner})
    public void onViewClicked(View view) {
        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
        switch (view.getId()) {
            case R.id.busset_Back:
                Log.e("111111", "111111");
                RxActivityTool.finishActivity(this);
                break;
            case R.id.rl_location:
                JumpUtil.newInstance().jumpRight(this, MapAddressActivity.class, 003);
                break;
            case R.id.ll_class:
                //商家分类
                Log.e("wwwwwwwwww", "商家分类: "+sort_id );
                Intent sort=new Intent(this,ShopSortActivity.class);
                sort.putExtra("pos",sort_id);
                startActivityForResult(sort,002);
                break;
            case R.id.mBtn:
                setBusinessMsg();
                break;
            case R.id.ll_type:
               // JumpUtil.newInstance().jumpRight(this, ShopTypeActivity.class, 001);
                Log.e("wwwwwwwwww", "商家类型: "+type_id );
                Intent intent=new Intent(this,ShopTypeActivity.class);
                intent.putExtra("pos",type_id);
                startActivityForResult(intent,001);
                break;
            case R.id.rl_head_img:
                succ=1;
                dialogChooseImage.show();
                break;
            case R.id.ll_banner:
                succ=2;
                dialogChooseImage.show();
                break;
        }
    }


    //获取店铺信息
    private void getBusinessMsg() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/get_business")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .params("type", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BusinessMsgBean msgBean = gson.fromJson(response.body(), BusinessMsgBean.class);
                        if (msgBean.getCode() == 200) {
                            tvShopName.setText(msgBean.getData().getShop_name());
                            tvAddress.setText(msgBean.getData().getProvince() + msgBean.getData().getAutograph() + msgBean.getData().getCity());
                            etContacts.setText(msgBean.getData().getUsername());
                            tv_sort.setText(msgBean.getData().getClassification_name());
                            tvType.setText(msgBean.getData().getCommission_name());
                            sort_id=msgBean.getData().getClassification_id();
                            type_id=msgBean.getData().getCommission_id();
                            lon=msgBean.getData().getLongitude();
                            lat=msgBean.getData().getLatitude();
                            sheng=msgBean.getData().getProvince();
                            shi=msgBean.getData().getAutograph();
                            xian=msgBean.getData().getCity();
                            headImgurl=msgBean.getData().getImage();
                            imgurl=msgBean.getData().getBackground_image();
                            RequestOptions options = new RequestOptions();
                            options.placeholder(R.mipmap.headimg);
                            Glide.with(BusinessSetActivity.this).load(Config.IMGS+msgBean.getData().getImage()).apply(options).into(headImage);
                            options.placeholder(R.mipmap.banner_img);
                            Glide.with(BusinessSetActivity.this).load(Config.IMGS+msgBean.getData().getBackground_image()).apply(options).into(bannerImg);

                        }
                    }
                });
    }

    //上传店铺信息
    private void setBusinessMsg() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/get_business")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .params("type", "1")
                .params("shop_name", tvShopName.getText().toString())
                .params("province", sheng)
                .params("district", shi)
                .params("city", xian)
                .params("username", etContacts.getText().toString())
                .params("longitude", lon)
                .params("latitude", lat)
                .params("commission_id", type_id+"")
                .params("classification_id", sort_id+"")
                .params("image", headImgurl)
                .params("background_image", imgurl)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BusinessMsgBean msgBean = gson.fromJson(response.body(), BusinessMsgBean.class);
                        if (msgBean.getCode() == 200) {
                            tvShopName.setText(msgBean.getData().getShop_name());
                            tvAddress.setText(msgBean.getData().getProvince() + msgBean.getData().getAutograph() + msgBean.getData().getCity());
                            etContacts.setText(msgBean.getData().getUsername());
                            tv_sort.setText(msgBean.getData().getClassification_name());
                            tvType.setText(msgBean.getData().getCommission_name());
                            RxToast.success(msgBean.getMessage());
                            RxActivityTool.finishActivity(BusinessSetActivity.this);
                        }else {
                            RxToast.error(msgBean.getMessage());
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
                            if (succ==1){
                                headImgurl = bean.getData().getImage_url();
                                Log.e("777777777777", "onSuccess: "+headImgurl );
                            }else {
                                imgurl = bean.getData().getImage_url();
                                Log.e("777777777777", "onSuccess: "+imgurl );
                            }
                        }
                    }
                });

    }

    private void initUCrop(Uri uri) {
        //Uri destinationUri = RxPhotoTool.createImagePathUri(this);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        Log.e("initUCrop: ", destinationUri + "");

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
                .start(BusinessSetActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initMySelfImg(requestCode, resultCode, data);

        if (data != null) {
            Bundle bundle = data.getExtras();
            switch (requestCode) {
                case 001:
                    type = bundle.getString("type");
                    type_id = bundle.getInt("type_id",0);
                    tvType.setText(type);
                    break;
                case 002:
                    sort = bundle.getString("sort");
                    sort_id = bundle.getInt("sort_id",0);
                    tv_sort.setText(sort);
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


    private void initMySelfImg(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
                    initUCrop(data.getData());
                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    initUCrop(RxPhotoTool.imageUriFromCamera);

                }

                break;
            case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理
                resultUri = RxPhotoTool.cropImageUri;
                File file0;
                if (succ==1){
                    file0 = roadImageView(resultUri, headImage);
                }else {
                    file0 = roadImageView(resultUri, bannerImg);
                }
                initUploadImage(file0);
                //RxToast.success("剪辑成功");
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    File file;
                    if (succ==1){
                        file = roadImageView(resultUri, headImage);
                    }else {
                        file = roadImageView(resultUri, bannerImg);
                    }
                    LogUtils.e("adassdada" + file);
                    initUploadImage(file);
                    //RxToast.success("剪辑成功");
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
    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {

        Glide.with(this).
                load(uri).
                thumbnail(0.5f).
                into(imageView);

        return (new File(RxPhotoTool.getImageAbsolutePath(this, uri)));
    }
}
