package com.wyj.cn.jinkaolademo.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.wyj.cn.jinkaolademo.MainActivity;
import com.wyj.cn.jinkaolademo.R;


/**
 * Created by acer-pc on 2018/1/2.
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_login);

        //6.0读写权限
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {

                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE

            };
            ActivityCompat.requestPermissions(this,permissions,123);

            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 0);

            }
        }

        initview();
    }

    private void initview() {
        Button btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
    @Override   //其中123是requestcode，可以根据这个code判断，用户是否同意了授权。
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("tag","requestCode****"+requestCode);
    }

}
