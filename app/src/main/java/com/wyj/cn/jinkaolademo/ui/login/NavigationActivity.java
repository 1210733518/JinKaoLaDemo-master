package com.wyj.cn.jinkaolademo.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.wyj.cn.jinkaolademo.R;

/**
 * Created by acer-pc on 2018/1/2.
 */

public class NavigationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_navigation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
                finish();

            }
        },2000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
