package com.tuoyi.threebusinesscity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tuoyi.threebusinesscity.R;
import com.zhouwei.blurlibrary.EasyBlur;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by md
 * on 2018/3/5 0005.
 * 图片处理工具类
 */

public class ImageUtil {
    /**
     * 图片处理Drawable获取Bitmap
     */
    public static Bitmap getDrawableBitmap(Context context, int img) {
        Bitmap finalBitmap = EasyBlur.with(context)
                .bitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.demo_img)) //要模糊的图片
                .radius(3)//模糊半径
                .blur();
        return finalBitmap;
    }

    /**
     * 图片处理URL获取Bitmap
     */
    public static Bitmap getURLBitmap(Context context, String img) {
        Bitmap map = null;
        try {
            URL url = new URL(img);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
