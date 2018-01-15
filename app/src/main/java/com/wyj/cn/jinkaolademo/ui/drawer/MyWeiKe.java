package com.wyj.cn.jinkaolademo.ui.drawer;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.wyj.cn.jinkaolademo.R;
import com.wyj.cn.jinkaolademo.adapter.WeikeGridAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by acer-pc on 2018/1/2.
 */

public class MyWeiKe extends Activity {

    private String path;
    private ArrayList<String>videolist=new ArrayList<>();
    private GridView gridView;
    private VideoView videoView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_myweike);

            path = Environment.getExternalStorageDirectory().getPath()+"/ScreenRecord/";// 获取视屏文件目录
            Log.e("tag","path------------------------------------"+path);
            if (path!=null){
                GetVideoFiles(path);
            }



        initview();

    }

    private void initview() {

        gridView = (GridView)findViewById(R.id.weike_grid);
        videoView = (VideoView)findViewById(R.id.myvideo);

        WeikeGridAdapter weikeGridAdapter=new WeikeGridAdapter(videolist,this);

        gridView.setAdapter(weikeGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Uri uri = Uri.parse(videolist.get(i));
                videoView.setMediaController(new MediaController(MyWeiKe.this));
                videoView.setVisibility(View.VISIBLE);
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

    }

    /**
     * 获取文件列表
     */
    public void GetVideoFiles(String path){

            // 获取该文件夹下的所有文件
            File mediaStorageDir = new File(path);

            if (mediaStorageDir.exists()){
                File[] files = mediaStorageDir.listFiles();
                Toast.makeText(MyWeiKe.this,"一共有"+files.length+"个视频",Toast.LENGTH_SHORT).show();
                if (files.length==0){
                    Toast.makeText(MyWeiKe.this,"文件夹时空的",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    for (File file:files){
                        if (file.isDirectory()){
                            GetVideoFiles(file.getAbsolutePath());
                        }else {

//                            String filename = file.getName().substring(0, file.getName().lastIndexOf("."));
                            //传入视屏的文件名和路径
                            videolist.add(file.getAbsolutePath());
                        }
                    }
                }

            }  else {
                Toast.makeText(MyWeiKe.this,"文件夹不存在",Toast.LENGTH_SHORT).show();
            }

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        videoView.setVisibility(View.GONE);
        videoView.stopPlayback();//停止播放


    }
}
