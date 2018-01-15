package com.wyj.cn.jinkaolademo.Utils;

import android.widget.Toast;

import com.wyj.cn.jinkaolademo.app.MyApplication;

/**
 * Created by guaju on 2018/1/1.
 */

public class ToastUtils {
    static Toast toast;

    public static void show(String str) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getApp().getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(str);
        toast.show();
    }

}
