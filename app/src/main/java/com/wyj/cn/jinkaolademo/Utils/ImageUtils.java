package com.wyj.cn.jinkaolademo.Utils;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wyj.cn.jinkaolademo.app.MyApplication;

/**
 * Created by guaju on 2018/1/2.
 */

public class ImageUtils {
    static ImageUtils imageUtils=new ImageUtils();
    public static ImageUtils getInstance(){
        return imageUtils;
    }
    

    public void load(String url, ImageView iv){
        //中心裁剪样式
        Glide.with(MyApplication.getApp())
                .load(url)
                .into(iv);
    }
    public void load(Uri uri, ImageView iv){
        Glide.with(MyApplication.getApp())
                .load(uri)
                .into(iv);
    }
    public void load(int resId,ImageView iv){
        Glide.with(MyApplication.getApp())
                .load(resId)
                .into(iv);
    }
}
