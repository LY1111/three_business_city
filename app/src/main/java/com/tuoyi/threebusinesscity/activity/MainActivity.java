package com.tuoyi.threebusinesscity.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.fragment.MainFragment;
import com.tuoyi.threebusinesscity.fragment.MessageFragment;
import com.tuoyi.threebusinesscity.fragment.MyFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineShopFragment;
import com.tuoyi.threebusinesscity.fragment.ShopFragment;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.main_fl)
    FrameLayout mainFl;
    @BindView(R.id.main_rb_main)
    RadioButton mainRbMain;
    @BindView(R.id.main_rb_shop)
    RadioButton mainRbShop;
    @BindView(R.id.main_rb_sao)
    LinearLayout mainRbSao;
    @BindView(R.id.main_rb_message)
    RadioButton mainRbMessage;
    @BindView(R.id.main_rb_my)
    RadioButton mainRbMy;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    @BindView(R.id.main_rb_sao_img)
    ImageView mainRbSaoImg;
    @BindView(R.id.main_rb_sao_tv)
    TextView mainRbSaoTv;
    private MainFragment mMainFragment;
    private ShopFragment mShopFragment;
    private MessageFragment mMessageFragment;
    private MyFragment mMyFragment;
    private OnLineShopFragment onLineShopFragment;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.BLUETOOTH
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    //----------以下动态获取权限---------
    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }


    /**
     * 检查权限
     *
     * @param permissions
     *
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        //获取权限列表
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                showMissingPermissionDialog();              //显示提示信息
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();

//        initFragment(changIndexListener.changIndex());

    }

    /**
     * 底部导航栏点击事件
     */
    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_main:
                        initFragment(1);
                        Glide.with(MainActivity.this).load(R.mipmap.photo).priority(Priority.LOW).centerCrop().into(mainRbSaoImg);
                        mainRbSaoTv.setText("扫一扫");
                        break;
                    case R.id.main_rb_shop:
                        initFragment(2);
                        Glide.with(MainActivity.this).load(R.mipmap.gouwuche).priority(Priority.LOW).centerCrop().into(mainRbSaoImg);
                        mainRbSaoTv.setText("购物车");
                        break;
                    case R.id.main_rb_message:
                        initFragment(3);
                        Glide.with(MainActivity.this).load(R.mipmap.photo).priority(Priority.LOW).centerCrop().into(mainRbSaoImg);
                        mainRbSaoTv.setText("扫一扫");
                        break;
                    case R.id.main_rb_my:
                        initFragment(4);
                        Glide.with(MainActivity.this).load(R.mipmap.photo).priority(Priority.LOW).centerCrop().into(mainRbSaoImg);
                        mainRbSaoTv.setText("扫一扫");
                        break;
                }

            }
        });
        mainRg.check(R.id.main_rb_main);


    }

    /**
     * 加载Fragment方法
     */
    public void initFragment(int index) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        switch (index) {
            case 1:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMainFragment);
                break;
            case 2:
                if (onLineShopFragment == null) {
                    onLineShopFragment = OnLineShopFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, onLineShopFragment);
                break;
            case 3:
                if (mMessageFragment == null) {
                    mMessageFragment = MessageFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMessageFragment);
                break;
            case 4:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMyFragment);
                break;
            default:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMainFragment);
                break;
        }
        beginTransaction.commit();
    }

    @OnClick(R.id.main_rb_sao)//二维码扫描
    public void onViewClicked() {
        RxActivityTool.skipActivity(this, RegisterActivity.class);
        Toast.makeText(this, mainRbSaoTv.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}