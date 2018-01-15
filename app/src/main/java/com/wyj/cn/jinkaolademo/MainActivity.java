package com.wyj.cn.jinkaolademo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.guaju.screenrecorderlibrary.ScreenRecorderHelper;
import com.wyj.cn.jinkaolademo.app.MyApplication;
import com.wyj.cn.jinkaolademo.ui.WhiteBoardFragment;
import com.wyj.cn.jinkaolademo.widget.UploadRecordFileDialog;

public class MainActivity extends FragmentActivity{
    private WhiteBoardFragment whiteBoardFragment;
    WhiteBoardFragment wb;
    private FragmentManager fm;
    private ScreenRecorderHelper srHelper;
    private boolean isclick=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srHelper = MyApplication.getApp().getSRHelper();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            isclick=true;
            srHelper.initRecordService(this);

        }else {
            isclick=false;
        }

        fm = getSupportFragmentManager();

        initview();
        initListener();
    }


    private void initview() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        //获取WhiteBoardFragment实例
        whiteBoardFragment = WhiteBoardFragment.newInstance();
        transaction.add(R.id.controlLayout, whiteBoardFragment,"wb").commit();

    }
    private void initListener() {

        wb = whiteBoardFragment;
        //等待找到控件
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (wb.luping_img == null) {

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        wb.luping_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isclick){
                                    srHelper.startRecord(MainActivity.this);
                                }else {
                                    Toast.makeText(MainActivity.this,"Android 5.0以下暂不支持录屏",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                        wb.zanting_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isclick){
                                    srHelper.stopRecord(new ScreenRecorderHelper.OnRecordStatusChangeListener() {
                                        @Override
                                        public void onChangeSuccess() {
                                            //当停止成功，做界面变化
                                            wb.luping_img.setBackgroundResource(R.drawable.luzhi);
                                            wb.zanting_img.setBackgroundResource(R.drawable.paused);
                                            wb.timer.stop();
                                            //Toast.makeText(MainActivity.this, "录屏成功"+srHelper.getRecordFilePath(), Toast.LENGTH_SHORT).show();
                                            showUploadDialog();

                                        }

                                        @Override
                                        public void onChangeFailed() {
                                            //不作处理

                                        }
                                    });
                                }else {
                                    Toast.makeText(MainActivity.this,"Android 5.0以下暂不支持录屏",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                });
            }
        }).start();

    }

    private void showUploadDialog() {
        UploadRecordFileDialog uploadRecordFileDialog = new UploadRecordFileDialog(this);
        wb.timer.setVisibility(View.GONE);
        uploadRecordFileDialog.show();
    }


    @Override   //其中123是requestcode，可以根据这个code判断，用户是否同意了授权。
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("tag", "requestCode****" + requestCode);
        srHelper.onActivityResult(this, requestCode, resultCode, data, new ScreenRecorderHelper.OnRecordStatusChangeListener() {
            @Override
            public void onChangeSuccess() {
                //控制开始按钮的文字变化
//                bt_start.setText("正在录制");
//                Toast.makeText(MainActivity.this, "开始录制~~~", Toast.LENGTH_SHORT).show();
                wb.luping_img.setBackgroundResource(R.drawable.recording);
                wb.zanting_img.setBackgroundResource(R.drawable.pause);
                wb.timer.setVisibility(View.VISIBLE);
                wb.timer.setBase(SystemClock.elapsedRealtime());//计时器清零
                wb.timer.setFormat("%s");
                wb.timer.start();
            }

            @Override
            public void onChangeFailed() {
                //如果录制失败，则不作任何变化
//                bt_start.setText("开始录制");
            }
        });
    }

}
