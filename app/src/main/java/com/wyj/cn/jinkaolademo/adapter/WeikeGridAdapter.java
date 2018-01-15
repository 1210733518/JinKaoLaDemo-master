package com.wyj.cn.jinkaolademo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wyj.cn.jinkaolademo.R;

import java.util.ArrayList;

/**
 * Created by acer-pc on 2018/1/14.
 */

public class WeikeGridAdapter extends BaseAdapter {

    private ArrayList<String>list;
    private Context context;

    public WeikeGridAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MyViewholder viewholder=null;

        if (view==null){
            viewholder=new MyViewholder();
            view= LayoutInflater.from(context).inflate(R.layout.wodeweike_item,null);
            viewholder.imageView=(ImageView)view.findViewById(R.id.wodeweike_gridimg);
            view.setTag(viewholder);
        }else {
           viewholder= (MyViewholder) view.getTag();
        }
        viewholder.imageView.setImageBitmap(getVideoThumbnail(list.get(i)));

        return view;
    }

    class MyViewholder{

        ImageView imageView;
    }

    /**
     * 根据视频文件的路径获取视频的缩略图
     * filePath 使用 files.getPath() 或 files.getAbsolutePath() 都可以
     */
    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
