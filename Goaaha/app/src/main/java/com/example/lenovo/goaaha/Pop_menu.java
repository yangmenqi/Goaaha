package com.example.lenovo.goaaha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 小妖精停一下 on 2016/9/27.
 */
public class Pop_menu extends Activity implements View.OnClickListener{
    private LinearLayout mGroupChat;
    private LinearLayout mAddFrd;
    private LinearLayout mFind;
    private LinearLayout mpay;
    private LinearLayout mFeedBack;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_menu);
        Intent intent = getIntent();
        initView();
    }

    public void initView(){

        //设置各个控件的点击响应
        mGroupChat = (LinearLayout)findViewById(R.id.faqiqunliao);
        mAddFrd = (LinearLayout)findViewById(R.id.tianjiapengyou);
        mFind = (LinearLayout)findViewById(R.id.saoyisao);
        mpay = (LinearLayout)findViewById(R.id.shoufukuan);
        mFeedBack = (LinearLayout)findViewById(R.id.bangzhuyukankui);

        mGroupChat.setOnClickListener(this);
        mAddFrd.setOnClickListener(this);
        mFind.setOnClickListener(this);
        mpay.setOnClickListener(this);
        mFeedBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.faqiqunliao:
                Toast.makeText(this,"菜单1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tianjiapengyou:
                Toast.makeText(this,"菜单2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.saoyisao:
                Toast.makeText(this,"菜单3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shoufukuan:
                Toast.makeText(this,"菜单4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bangzhuyukankui:
                Toast.makeText(this,"菜单5", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }


}
