package com.tuoyi.threebusinesscity.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import com.tuoyi.threebusinesscity.activity.LoginActivity;
import com.tuoyi.threebusinesscity.bean.UserBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by md
 * on 2018/4/28 0028.
 */

public class MyUtil {

    public static boolean initToLogin(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("跳转登录")
                .setMessage("继续操作需要登录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RxActivityTool.skipActivity(
                                context, LoginActivity.class);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();


        if (UserBean.getToken(context).equals("")
                || TextUtils.isEmpty(UserBean.getToken(context))
                || UserBean.getToken(context) == null) {
            builder.show();
//                JumpUtil.newInstance().jumpRight(this, LoginActivity.class);
//                finish();
            return false;
        }else {
            return true;
        }
    }



    /**
     * 将二维码图片保存到文件夹
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        String externalStorageState = Environment.getExternalStorageState();
        //判断sd卡是否挂载
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
        /*外部存储可用，则保存到外部存储*/
            //创建一个文件夹
            File appDir = new File(Environment.getExternalStorageDirectory(), "sszl");
            //如果文件夹不存在
            if (!appDir.exists()) {
                //则创建这个文件夹
                appDir.mkdir();
            }
            //将bitmap保存
            save(context, bmp, appDir);
        } else {
            //外部不可用，将图片保存到内部存储中，获取内部存储文件目录
            File filesDir = context.getFilesDir();
            //保存
            save(context, bmp, filesDir);
        }
    }

    private static void save(Context context, Bitmap bmp, File appDir) {
        //命名文件名称
        String fileName = System.currentTimeMillis() + ".jpg";
        //创建图片文件，传入文件夹和文件名
        File imagePath = new File(appDir, fileName);
        try {
            //创建文件输出流，传入图片文件，用于输入bitmap
            FileOutputStream fos = new FileOutputStream(imagePath);
            //将bitmap压缩成png，并保存到相应的文件夹中
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            //冲刷流
            fos.flush();
            //关闭流
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    imagePath.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagePath.getAbsolutePath())));
    }

}
