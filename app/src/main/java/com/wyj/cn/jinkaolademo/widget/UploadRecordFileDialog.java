package com.wyj.cn.jinkaolademo.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wyj.cn.jinkaolademo.R;
import com.wyj.cn.jinkaolademo.Utils.ToastUtils;
import com.wyj.cn.jinkaolademo.ui.upload.UploadRecordActivity;


/**
 * Created by guaju on 2018/1/5.
 */

public class UploadRecordFileDialog implements View.OnClickListener {


    private TextView tv_upload;
    private TextView tv_save;
    private TextView tv_delete;
    private TextView tv_cancel;

    
    Context context;
    LayoutInflater inflater;
    private View v;
    Dialog dialog;
    private WindowManager wm;
    private Display display;
    private ViewGroup.LayoutParams layoutParams;

    public UploadRecordFileDialog(@NonNull Context context) {
        this.context = context;
        dialog = new Dialog(context, R.style.common_dialog_style);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display=wm.getDefaultDisplay();
        init();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public void show() {

        dialog.show();

    }

    private void init() {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.dialog_upload_recordfile, null, false);

        tv_upload = v.findViewById(R.id.tv_upload);
        tv_save = v.findViewById(R.id.tv_save);
        tv_delete = v.findViewById(R.id.tv_delete);
        tv_cancel = v.findViewById(R.id.tv_cancel);

        tv_upload.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);


        dialog.setContentView(v);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = display.getWidth()*2/5;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        attributes.gravity= Gravity.BOTTOM;
        window.setAttributes(attributes);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_upload:
                gotoUploadActivity();
                break;
            case R.id.tv_save:
                ToastUtils.show("以保存到本地目录");
                break;

            case R.id.tv_delete:
                ToastUtils.show("已删除录制文件");
                break;

            case R.id.tv_cancel:
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
            default:
                break;
        }
    }

    private void gotoUploadActivity() {
        Activity activity=(Activity)context;
        Intent intent = new Intent(activity, UploadRecordActivity.class);
        activity.startActivity(intent);
    }
}
