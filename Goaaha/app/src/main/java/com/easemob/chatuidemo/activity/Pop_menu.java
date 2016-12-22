
package com.easemob.chatuidemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.easemob.chatuidemo.R;

/**
 * Created by 小妖精停一下 on 2016/9/27.
 */
public class Pop_menu extends Activity implements View.OnClickListener{
    private LinearLayout mGroupChat;
    private LinearLayout mAddFrd;
    private LinearLayout mFind;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_menu);
        Intent intent = getIntent();
        initView();
    }

    public void initView(){

        //设置各个控件的点击响应
        mGroupChat = (LinearLayout)findViewById(R.id.id_menu_map_search);
        mAddFrd = (LinearLayout)findViewById(R.id.id_menu_map_buslinesearch);
        mFind = (LinearLayout)findViewById(R.id.id_menu_map_routeplan);


        mGroupChat.setOnClickListener(this);
        mAddFrd.setOnClickListener(this);
        mFind.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.id_menu_map_search:
                Intent i = new Intent();
                i.setClass(Pop_menu.this,PoiSearchDemo.class);
                startActivity(i);
                break;
            case R.id.id_menu_map_buslinesearch:
                Intent m =new Intent();
               m.setClass(Pop_menu.this,BusLineSearchDemo.class);
                startActivity(m);
                break;
            case R.id.id_menu_map_routeplan:
                Intent n =new Intent();
                n.setClass(Pop_menu.this,RoutePlanDemo.class);
                startActivity(n);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }


}
