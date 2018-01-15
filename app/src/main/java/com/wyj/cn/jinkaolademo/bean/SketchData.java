package com.wyj.cn.jinkaolademo.bean;

import android.graphics.Bitmap;

import com.wyj.cn.jinkaolademo.widget.SketchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChiEr on
 */
public class SketchData {
    public List<PhotoRecord> photoRecordList;
    public List<StrokeRecord> strokeRecordList;
    public List<StrokeRecord> strokeRedoList;
    public Bitmap thumbnailBM;//缩略图文件
    public Bitmap backgroundBM;
    public int strokeType;
    public int editMode;

    public SketchData() {
        strokeRecordList = new ArrayList<>();
        photoRecordList = new ArrayList<>();
        strokeRedoList = new ArrayList<>();
        backgroundBM = null;
        thumbnailBM = null;
        strokeType = StrokeRecord.STROKE_TYPE_DRAW;
        editMode = SketchView.EDIT_STROKE;
    }

}
