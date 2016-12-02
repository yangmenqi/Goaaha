package com.example.lenovo.goaaha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll;
    private LinearLayout mLChat,mLCurrency,mLMap,mLMine;
    //声明Fragment属性
    private ChatFragment mChat;
    private CurrencyFragment mCurrency;
    private MapFragment mMap;
    private MineFragment mMine;
    // 底部标签图片
    private ImageView chatImg, currencyImg , mapImg , mineImg;
    // 底部标签的文本
    private TextView chatTv, currencyTv,mapTv, mineTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //1.获取界面的控件
        getViews();
        //2.注册事件监听器
        setListener();
        //3.设置默认的页面（fragment页面）
        setDefaultPage();
    }
    //获取界面的控件
    private void getViews(){
        ll = (LinearLayout) findViewById(R.id.ll);
        mLChat = (LinearLayout) findViewById(R.id.chat);
        mLCurrency = (LinearLayout) findViewById(R.id.currency);
        mLMap = (LinearLayout)findViewById(R.id.map);
        mLMine = (LinearLayout) findViewById(R.id.mine);

        chatImg = (ImageView)findViewById(R.id.chatimg);
        currencyImg = (ImageView)findViewById(R.id.currencyimg);
        mapImg = (ImageView)findViewById(R.id.mapimg);
        mineImg = (ImageView)findViewById(R.id.mineimg);

        chatTv = (TextView)findViewById(R.id.chattext);
        currencyTv = (TextView)findViewById(R.id.currencytext);
        mapTv = (TextView)findViewById(R.id.maptext);
        mineTv = (TextView)findViewById(R.id.minetext);
    }

    //注册事件监听器
    private void setListener(){
        MyListener listener = new MyListener();
        mLChat.setOnClickListener(listener);
        mLCurrency.setOnClickListener(listener);
        mLMap.setOnClickListener(listener);
        mLMine.setOnClickListener(listener);
    }

    //设置默认的页面（fragment页面）
    private void setDefaultPage(){
        //1.获取一个FragmentManager对象
        android.app.FragmentManager fm = getFragmentManager();
        //2.获取FragmentTransaction对象
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        mChat = new ChatFragment();
        //3.设置页面
        transaction.replace(R.id.contaner,mChat);
        //4.执行更改
        transaction.commit();
    }
    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //1.获取一个FragmentManager对象
            android.app.FragmentManager fm = getFragmentManager();
            //2.获取FragmentTransaction对象
            android.app.FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()){
                case R.id.chat:
                    if(mChat == null){
                        mChat = new ChatFragment();
                    }
                    mChat = new ChatFragment();
                    //3.设置页面
                    transaction.replace(R.id.contaner,mChat);
                    chatImg.setImageResource(R.drawable.chat1);
                    currencyImg.setImageResource(R.drawable.currency);
                    mapImg.setImageResource(R.drawable.map);
                    mineImg.setImageResource(R.drawable.mine);
                    chatTv.setTextColor(getResources().getColor(R.color.Maincolor));
                    currencyTv.setTextColor(getResources().getColor(R.color.LightGray));
                    mapTv.setTextColor(getResources().getColor(R.color.LightGray));
                    mineTv.setTextColor(getResources().getColor(R.color.LightGray));
                    break;
                case R.id.currency:
                    if(mCurrency == null){
                        mCurrency = new CurrencyFragment();
                    }
                    mCurrency = new CurrencyFragment();
                    //3.设置页面
                    transaction.replace(R.id.contaner,mCurrency);
                    chatImg.setImageResource(R.drawable.chat);
                    currencyImg.setImageResource(R.drawable.currency1);
                    mapImg.setImageResource(R.drawable.map);
                    mineImg.setImageResource(R.drawable.mine);
                    chatTv.setTextColor(getResources().getColor(R.color.LightGray));
                    currencyTv.setTextColor(getResources().getColor(R.color.Maincolor));
                    mapTv.setTextColor(getResources().getColor(R.color.LightGray));
                    mineTv.setTextColor(getResources().getColor(R.color.LightGray));
                    break;
                case R.id.map:
                    if(mMap == null){
                        mMap = new MapFragment();
                        mMap.setContext(getApplicationContext());
                    }
                    //3.设置页面
                    transaction.replace(R.id.contaner,mMap);

                    chatImg.setImageResource(R.drawable.chat);
                    currencyImg.setImageResource(R.drawable.currency);
                    mapImg.setImageResource(R.drawable.map1);
                    mineImg.setImageResource(R.drawable.mine);
                    chatTv.setTextColor(getResources().getColor(R.color.LightGray));
                    currencyTv.setTextColor(getResources().getColor(R.color.LightGray));
                    mapTv.setTextColor(getResources().getColor(R.color.Maincolor));
                    mineTv.setTextColor(getResources().getColor(R.color.LightGray));
                    break;
                case R.id.mine:
                    if(mMine == null){
                        mMine = new MineFragment();
                    }
                    mMine = new MineFragment();
                    //3.设置页面
                    transaction.replace(R.id.contaner,mMine);
                    chatImg.setImageResource(R.drawable.chat);
                    currencyImg.setImageResource(R.drawable.currency);
                    mapImg.setImageResource(R.drawable.map);
                    mineImg.setImageResource(R.drawable.mine1);
                    chatTv.setTextColor(getResources().getColor(R.color.LightGray));
                    currencyTv.setTextColor(getResources().getColor(R.color.LightGray));
                    mapTv.setTextColor(getResources().getColor(R.color.LightGray));
                    mineTv.setTextColor(getResources().getColor(R.color.Maincolor));
                    break;
            }
            //4.执行更改
            transaction.commit();
            ll.invalidate();
        }
    }
}