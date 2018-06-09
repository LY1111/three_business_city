package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.tuoyi.threebusinesscity.bean.UserInfoBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.CountDownTimerUtils;
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
import cn.qqtheme.framework.picker.OptionPicker;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/2 15:42
 * @描述 ${TODO}
 */

public class Personal_InformationActivity extends AppCompatActivity {

    @BindView(R.id.leftBack)
    ImageView       leftBack;
    @BindView(R.id.personalData_save)
    TextView        mSave;
    @BindView(R.id.personalData_image)
    CircleImageView mImage;
    @BindView(R.id.personalData_nickName)
    EditText        mNickName;
    @BindView(R.id.personalData_email)
    EditText        mEmail;
    @BindView(R.id.personalData_sex)
    TextView        mSex;

    @BindView(R.id.personalData_address)
    EditText        mAddress;
    @BindView(R.id.personalData_phone)
    EditText        mPhone;
    @BindView(R.id.personalData_VerificationCode)
    EditText        mVerificationCode;
    @BindView(R.id.personalData_getCode)
    TextView        mGetCode;
    @BindView(R.id.personalData_sign)
    EditText        mSign;
    @BindView(R.id.Bank_card)
    EditText        bank_card;
    private String[] mSexStr = {"保密", "男", "女"};
    private String   mCode   = "";
    private String   mImgUrl = "";
    private int      mSexint = 0;
    private Uri resultUri;
    private String mBirthday1="";
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private final        int    IMAGE_CODE        = 0; // 这里的IMAGE_CODE是自己任意定义的
    //private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ButterKnife.bind(this);
        initGetUerInfo();
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
                    /* data.getExtras().get("data");*/
//                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }

                break;
            case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理
                resultUri = RxPhotoTool.cropImageUri;
                File file0 = roadImageView(resultUri, mImage);
                initUploadImage(file0);
                RxToast.success("剪辑成功");
//                RequestUpdateAvatar(new File(RxPhotoTool.getRealFilePath(this, RxPhotoTool.cropImageUri)));
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    File file = roadImageView(resultUri, mImage);
                    LogUtils.e("adassdada"+file);
                    initUploadImage(file);
                    RxToast.success("剪辑成功");
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
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initMySelfImg(requestCode, resultCode, data);

//        Bitmap bm = null;
//
//        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
//
//        ContentResolver resolver = getContentResolver();
//
//        if (requestCode == IMAGE_CODE) {
//
//            try {
//
//                Uri originalUri = data.getData(); // 获得图片的uri
//
//                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
//
////                imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(bm, 100, 100));  //使用系统的一个工具类，参数列表为 Bitmap Width,Height  这里使用压缩后显示，否则在华为手机上ImageView 没有显示
//                // 显得到bitmap图片
//                // imageView.setImageBitmap(bm);
//
//                String[] proj = { MediaStore.Images.Media.DATA };
//
//                // 好像是android多媒体数据库的封装接口，具体的看Android文档
//                Cursor cursor = managedQuery(originalUri, proj, null, null, null);
//
//                // 按我个人理解 这个是获得用户选择的图片的索引值
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
//                cursor.moveToFirst();
//                // 最后根据索引值获取图片路径
//                String path = cursor.getString(column_index);
////                tv.setText(path);
//
//                RxToast.showToast(path);
////
//                RequestOptions options = new RequestOptions()
//                        .placeholder(R.drawable.s_img)// 正在加载中的图片
//                        .error(R.drawable.s_img) // 加载失败的图片
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE); // 磁盘缓存策略
//                Glide.with(this).
//                        load(path).
//                        thumbnail(0.5f).
//                        apply(options).
//                        into(mImage);
//                roadImageView(originalUri,mImage);
//            } catch (IOException e) {
//                Log.e("TAG-->Error", e.toString());
//
//            }
//
//            finally {
//                return;
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }


//    private void setImage1() {
//        Intent intent = new Intent(Intent.ACTION_PICK, null);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
//        startActivityForResult(intent, IMAGE_CODE);
//    }
//
//    private void setImage() {
//        // TODO Auto-generated method stub
//        // 使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片
//
//        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
//
//        getAlbum.setType(IMAGE_UNSPECIFIED);
//
//        startActivityForResult(getAlbum, IMAGE_CODE);
//
//    }

    @OnClick({R.id.leftBack, R.id.personalData_sex,  R.id.personalData_save, R.id.personalData_getCode, R.id.personalData_image_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
               finish();
                break;
            case R.id.personalData_sex:
               final OptionPicker picker1 = new OptionPicker(this, mSexStr);
                picker1.setSubmitText("确定");
                picker1.setCancelText("取消");
                picker1.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int position, String option) {
//                        showToast(option);
                        mSex.setText(option);
                        mSexint = position;
                        picker1.dismiss();
                    }
                });
                picker1.show();
                break;
           /* case R.id.personalData_birthday:
                final DateTimePicker datePicker = new DateTimePicker(this, DateTimePicker.NONE);
                datePicker.setTextColor(0xFF000000);
                datePicker.setDividerColor(0xFFFFFFFF);
                datePicker.setSubmitText("确定");
                datePicker.setDateRangeStart(1947, 10, 1);
                datePicker.setDateRangeEnd(2121, 10, 1);
                datePicker.setCancelText("取消");
                datePicker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mBirthday.setText(year + "-" + month + "-" + day);
                        mBirthday1=year + "-" + month + "-" + day;
                        datePicker.dismiss();
                    }
                });
                datePicker.show();

                break;*/
            case R.id.personalData_save:

                    initUpdateInfo();

                break;
            case R.id.personalData_image_rl:
//                setImage1();
                RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
                dialogChooseImage.show();
                break;
            case R.id.personalData_getCode:
                if (TextUtils.isEmpty(mPhone.getText().toString().trim())) {
                    RxToast.info("请填写手机号");
                    return;
                } else {
                    CountDownTimerUtils utils = new CountDownTimerUtils(mGetCode, 60000, 1000);
                    utils.start();
                   // initGetCode(mPhone.getText().toString().trim());
                }

                break;
        }
    }

    private void initUploadImage(File phoneStr) {
        OkGo.<String>post(Config.s + "api/App/upload_image")
                .tag(this)
                .params("upload", phoneStr)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("2222222"+response.body());
                        Gson gson = new Gson();
                        UploadImageBean bean = gson.fromJson(response.body(), UploadImageBean.class);
                        if (bean.getCode() == 200) {
                            mImgUrl = bean.getData().getImage_url();
                        }
                        Toast.makeText(Personal_InformationActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initGetUerInfo() {
        OkGo.<String>post(Config.s + "api/AppProve/member_info")
                .tag(this)
                .params("token",UserBean.getToken(Personal_InformationActivity.this) )
                .params("type", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("111111111111"+response.body());
                        Gson gson = new Gson();
                        UserInfoBean mUserInfo = gson.fromJson(response.body(), UserInfoBean.class);
                       if (mUserInfo.getCode() == 200) {
                           mImgUrl = mUserInfo.getData().getUserpic();
                           Glide.with(Personal_InformationActivity.this).
                                   load(Config.s+mUserInfo.getData().getUserpic()).
                                   thumbnail(0.5f).
                                   into(mImage);
                           mNickName.setText(mUserInfo.getData().getUsername());
                           mEmail.setText(mUserInfo.getData().getEmail());
                           Log.e("111111111111", "qqqqqq"+mUserInfo.getData().getSex());
                           if (1==(mUserInfo.getData().getSex())){
                               mSex.setText("男");
                           } else if  (2==(mUserInfo.getData().getSex())){
                               mSex.setText("女");
                           }else if (0==(mUserInfo.getData().getSex())){
                               mSex.setText("保密");
                           }
                          // mBirthday.setText(mUserInfo.getData().getBirthday());
                           mSign.setText(mUserInfo.getData().getAutograph());
                        }
                        Toast.makeText(Personal_InformationActivity.this, mUserInfo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

    private void initUpdateInfo() {
        Log.e("333333",mImgUrl);
        OkGo.<String>post(Config.s + "api/AppProve/member_info")
                .tag(this)
                .params("token",UserBean.getToken(Personal_InformationActivity.this) )
                .params("type", "1")
                .params("username", mNickName.getText().toString().trim())
                .params("userpic", mImgUrl)
                .params("email", mEmail.getText().toString().trim())
                .params("sex", mSexint)
                .params("bank_card", bank_card.getText().toString().trim())
                .params("autograph", mSign.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("22222222"+response.body());
                        Gson gson = new Gson();
                        UserInfoBean mUserInfo = gson.fromJson(response.body(), UserInfoBean.class);
                        if (mUserInfo.getCode() == 200) {
                            mImgUrl = mUserInfo.getData().getUserpic();
                            Glide.with(Personal_InformationActivity.this).
                                    load(Config.s+mUserInfo.getData().getUserpic()).
                                    thumbnail(0.5f).
                                    into(mImage);
                            mNickName.setText(mUserInfo.getData().getUsername());
                            mEmail.setText(mUserInfo.getData().getEmail());
                            if (1==(mUserInfo.getData().getSex())){
                                mSex.setText("男");
                            } else if  (2==(mUserInfo.getData().getSex())){
                                mSex.setText("女");
                            }else if (0==(mUserInfo.getData().getSex())){
                                mSex.setText("保密");
                            }
                           // mBirthday.setText(mUserInfo.getData().getBirthday());
                            mSign.setText(mUserInfo.getData().getAutograph());

                        }
                        Toast.makeText(Personal_InformationActivity.this, mUserInfo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
