package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mob.wrappers.AnalySDKWrapper;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.UploadImageBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.bean.UserInfoBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.CountDownTimerUtils;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialog;
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
    ImageView leftBack;
    @BindView(R.id.personalData_save)
    TextView mSave;
    @BindView(R.id.personalData_image)
    CircleImageView mImage;
    @BindView(R.id.personalData_nickName)
    EditText mNickName;
    @BindView(R.id.personalData_email)
    EditText mEmail;
    @BindView(R.id.personalData_sex)
    TextView mSex;
    @BindView(R.id.personalData_address)
    EditText mAddress;
    @BindView(R.id.personalData_phone)
    EditText mPhone;
    @BindView(R.id.personalData_VerificationCode)
    EditText mVerificationCode;
    @BindView(R.id.personalData_getCode)
    TextView mGetCode;
    @BindView(R.id.tv_authentication)
    TextView tv_authentication;
    @BindView(R.id.tv_agreement)
    TextView tv_agreement;
    @BindView(R.id.personalData_sign)
    EditText mSign;
    @BindView(R.id.ll_bankCard)
    LinearLayout ll_bankCard;
    private String[] mSexStr = {"保密", "男", "女"};
    private String mCode = "";
    private String mImgUrl = "";
    private int mSexint = 0;
    private Uri resultUri;
    private String mBirthday1 = "";
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private final int IMAGE_CODE = 0; // 这里的IMAGE_CODE是自己任意定义的
    //private File file;
    private String contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                    LogUtils.e("adassdada" + file);
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
                .start(Personal_InformationActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initMySelfImg(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick({R.id.leftBack, R.id.personalData_sex, R.id.personalData_save, R.id.personalData_getCode, R.id.personalData_image_rl, R.id.tv_authentication,R.id.tv_agreement
    ,R.id.ll_bankCard})
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
            case R.id.tv_authentication:
                authentication();   //实名认证
                break;
            case R.id.ll_bankCard:      //银行卡列表
                Bundle bundle1=new Bundle();
                bundle1.putString("where","Personal_InformationActivity");
                RxActivityTool.skipActivity(this, BankCardActivity.class,bundle1);
                break;
            case R.id.tv_agreement:
                if ("1".equals(contract)){
                    Bundle bundle=new Bundle();
                    bundle.putString("where","Personal_InformationActivity");
                    RxActivityTool.skipActivity(this,ContractActivity.class,bundle);   //签约
                }else {
                    ToastUtil.show(this, "请实名认证后，在进行签约");
                }

                break;
        }
    }

    private void authentication() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dialog_authentication, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        final EditText etPassword = (EditText) view
                .findViewById(R.id.et_password);
        final EditText etName = (EditText) view
                .findViewById(R.id.et_name);

        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString().trim();
                String name = etName.getText().toString().trim();
                // password!=null && !password.equals("")
                if (!TextUtils.isEmpty(password)) {
                    OkGo.<String>post(Config.s + "api/AppProve/real_name_authentication")
                            .tag(this)
                            .params("name", name)
                            .params("identityNo", password)
                            .params("token",  UserBean.getToken(Personal_InformationActivity.this))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    BaseBean bean = gson.fromJson(response.body(), BaseBean.class);
                                    if (bean.getCode()==200){
                                        ToastUtil.show(Personal_InformationActivity.this, bean.getMessage());
                                        initGetUerInfo();
                                        dialog.dismiss();
                                    }else {
                                        ToastUtil.show(Personal_InformationActivity.this, bean.getMessage());
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Personal_InformationActivity.this, "身份证号内容不能为空!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();// 隐藏dialog
            }
        });

        dialog.show();
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
                            mImgUrl = bean.getData().getImage_url();
                        }
                        // Toast.makeText(Personal_InformationActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initGetUerInfo() {
        OkGo.<String>post(Config.s + "api/AppProve/member_info")
                .tag(this)
                .params("token", UserBean.getToken(Personal_InformationActivity.this))
                .params("type", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("111111111111" + response.body());
                        Gson gson = new Gson();
                        UserInfoBean mUserInfo = gson.fromJson(response.body(), UserInfoBean.class);
                        if (mUserInfo.getCode() == 200) {
                            mImgUrl = mUserInfo.getData().getUserpic();
                            Glide.with(Personal_InformationActivity.this).
                                    load(Config.IMGS + mUserInfo.getData().getUserpic()).
                                    thumbnail(0.5f).
                                    into(mImage);
                            mNickName.setText(mUserInfo.getData().getUsername());
                            mEmail.setText(mUserInfo.getData().getEmail());
                            Log.e("111111111111", "qqqqqq" + mUserInfo.getData().getSex());
                            if (1 == (mUserInfo.getData().getSex())) {
                                mSex.setText("男");
                            } else if (2 == (mUserInfo.getData().getSex())) {
                                mSex.setText("女");
                            } else if (0 == (mUserInfo.getData().getSex())) {
                                mSex.setText("保密");
                            }
                            // mBirthday.setText(mUserInfo.getData().getBirthday());
                            mSign.setText(mUserInfo.getData().getAutograph());
                            //是否认证
                            contract=mUserInfo.getData().getIs_real_name();
                            if ("0".equals(mUserInfo.getData().getIs_real_name())) {
                                tv_authentication.setBackgroundResource(R.drawable.bg_identify_code_normal);
                                tv_authentication.setEnabled(true);
                                tv_authentication.setText("去认证");
                            } else if ("1".equals(mUserInfo.getData().getIs_real_name())) {
                                tv_authentication.setBackgroundResource(R.drawable.bg_identify_code_press);
                                tv_authentication.setEnabled(false);
                                tv_authentication.setText("已认证");
                            }
                            if ("0".equals(mUserInfo.getData().getElectronic_signing())) {
                                tv_agreement.setBackgroundResource(R.drawable.bg_identify_code_normal);
                                tv_agreement.setEnabled(true);
                                tv_agreement.setText("去签约");
                            } else if ("1".equals(mUserInfo.getData().getIs_real_name())) {
                                tv_agreement.setBackgroundResource(R.drawable.bg_identify_code_press);
                                tv_agreement.setEnabled(false);
                                tv_agreement.setText("已签约");
                            }
                        }
                        //Toast.makeText(Personal_InformationActivity.this, mUserInfo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initUpdateInfo() {
        Log.e("333333", mImgUrl);
        OkGo.<String>post(Config.s + "api/AppProve/member_info")
                .tag(this)
                .params("token", UserBean.getToken(Personal_InformationActivity.this))
                .params("type", "1")
                .params("username", mNickName.getText().toString().trim())
                .params("userpic", mImgUrl)
                .params("email", mEmail.getText().toString().trim())
                .params("sex", mSexint)
                .params("bank_card", "")
                .params("autograph", mSign.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("22222222" + response.body());
                        Gson gson = new Gson();
                        UserInfoBean mUserInfo = gson.fromJson(response.body(), UserInfoBean.class);
                        if (mUserInfo.getCode() == 200) {
                            mImgUrl = mUserInfo.getData().getUserpic();
                            Glide.with(Personal_InformationActivity.this).
                                    load(Config.IMGS + mUserInfo.getData().getUserpic()).
                                    thumbnail(0.5f).
                                    into(mImage);
                            mNickName.setText(mUserInfo.getData().getUsername());
                            mEmail.setText(mUserInfo.getData().getEmail());
                            if (1 == (mUserInfo.getData().getSex())) {
                                mSex.setText("男");
                            } else if (2 == (mUserInfo.getData().getSex())) {
                                mSex.setText("女");
                            } else if (0 == (mUserInfo.getData().getSex())) {
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
