package com.tuoyi.threebusinesscity.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoaderInterface;


/**
 * Created by md
 * on 2017/11/22 0022.
 */

class GlideImageLoader implements ImageLoaderInterface {
    @Override
    public void displayImage(Context context, Object path, View imageView) {
        //Glide 加载图片简单用法
//        Glide.with(context).load(IMGS + path).into((ImageView) imageView);
        Glide.with(context).load(path).into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }
}