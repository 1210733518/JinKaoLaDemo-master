package com.wyj.cn.jinkaolademo.ui.upload;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wyj.cn.jinkaolademo.R;
import com.wyj.cn.jinkaolademo.Utils.ImageUtils;
import com.wyj.cn.jinkaolademo.bean.PicChooseHelper;
import com.wyj.cn.jinkaolademo.widget.SelectPicDialog;

public class UploadRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_lesson_name;
    private EditText edit_class;
    private EditText edit_knowlage;
    private EditText et_profile;
    private ImageView iv_navi;
    private ImageView iv_1, iv_selected_1, iv_selected_2, iv_selected_3;
    private ImageView iv_2;
    private ImageView iv_3;
    private RelativeLayout imageView1, imageView2, imageView3;
    private ImageView iv_select;
    private Button bt_commit;
    //状态
    private ImageView ivSelected;
    private SelectPicDialog selectPicDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_record);
        initView();
        initListener();
    }

    private void initListener() {
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        iv_select.setOnClickListener(this);
    }

    private void initView() {
        iv_navi = (ImageView) findViewById(R.id.iv_navi);


        imageView1 = (RelativeLayout) findViewById(R.id.imageView1);
        imageView2 = (RelativeLayout) findViewById(R.id.imageView2);
        imageView3 = (RelativeLayout) findViewById(R.id.imageView3);
        iv_selected_1 = (ImageView) findViewById(R.id.iv_selected_1);
        iv_selected_2 = (ImageView) findViewById(R.id.iv_selected_2);
        iv_selected_3 = (ImageView) findViewById(R.id.iv_selected_3);
        //设置默认，为选中第一个
        iv_selected_2.setVisibility(View.INVISIBLE);
        iv_selected_3.setVisibility(View.INVISIBLE);

        iv_select = (ImageView) findViewById(R.id.iv_cover_select);
        et_lesson_name = (EditText) findViewById(R.id.et_lesson_name);
        edit_class = (EditText) findViewById(R.id.edit_class);
        edit_knowlage = (EditText) findViewById(R.id.edit_knowlage);
        et_profile = (EditText) findViewById(R.id.et_profile);
        bt_commit = (Button) findViewById(R.id.bt_commit);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView1:
                if (iv_selected_1.getVisibility() == View.VISIBLE) {
                    resetAll();
                    iv_selected_1.setVisibility(View.INVISIBLE);
                } else {
                    resetAll();
                    iv_selected_1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imageView2:
                if (iv_selected_2.getVisibility() == View.VISIBLE) {
                    resetAll();
                    iv_selected_2.setVisibility(View.INVISIBLE);
                } else {
                    resetAll();
                    iv_selected_2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imageView3:
                if (iv_selected_3.getVisibility() == View.VISIBLE) {
                    resetAll();
                    iv_selected_3.setVisibility(View.INVISIBLE);
                } else {
                    resetAll();
                    iv_selected_3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_cover_select:
                selectPicDialog = new SelectPicDialog(this, R.style.common_dialog_style);
                selectPicDialog.show();
                break;
            default:
                break;

        }
    }

    void resetAll() {
        iv_selected_1.setVisibility(View.INVISIBLE);
        iv_selected_2.setVisibility(View.INVISIBLE);
        iv_selected_3.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PicChooseHelper.getInstance(this).onActivityResult(requestCode, resultCode, data, PicChooseHelper.CropType.Cover, new PicChooseHelper.OnPicReadyListener() {
            @Override
            public void onReady(Uri outUri) {
                ImageUtils.getInstance().load(outUri, iv_select);
                selectPicDialog.dismiss();

            }
        });
    }
}
