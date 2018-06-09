package com.tuoyi.threebusinesscity.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.url.Config;
import com.youth.banner.loader.ImageLoaderInterface;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;


/**
 * Created by md
 * on 2017/11/22 0022.
 * 轮播图专用加载接口
 */

public class GlideImageLoader implements ImageLoaderInterface {
    @Override
    public void displayImage(Context context, Object path, View imageView) {
        //Glide 加载图片简单用法
//        Glide.with(context).load(IMGS + path).into((ImageView) imageView);
        Glide.with(context).load(IMGS+path).into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }
}
