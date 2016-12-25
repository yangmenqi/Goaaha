package com.easemob.chatuidemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.adapter.CurrencyAdapter;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList extends Activity {
    private LinearLayout back;
    private List<Country> ls = new ArrayList<Country>();
    private CurrencyAdapter myadapter;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_list);
        //1、修改当前activity的布局文件，加入ListView控件并设置id
        //2、定义数据源，字符串数组
        //3、定义item布局，使用Android内置listview的item布局
        //4、根据数据源与item布局定义adapter
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //5、得到ListView对象，并设置adapter
        getData();
        myadapter = new CurrencyAdapter(this,ls);
        lv = (ListView)findViewById(R.id.CountryLv);
        lv.setAdapter(myadapter);

        //6、给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //接收上一页面传来的数据
                Intent country = getIntent();
                int countryid = country.getIntExtra("countryid",-1);

                if(countryid==1){
                    setResult(i);
                    finish();
                }else{
                    setResult(i + 18);
                    finish();
                }
            }
        });

        back = (LinearLayout) findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接收上一页面传来的数据
                Intent country = getIntent();
                int countryid = country.getIntExtra("countryid",-1);
                int nameid1 = country.getIntExtra("nameid1",-1);
                int nameid2 = country.getIntExtra("nameid2",-1);

                if(countryid==1){
                    setResult(nameid1);
                    finish();
                }else{
                    setResult(nameid2 + 18);
                    finish();
                }
            }
        });
    }

    private void getData() {
        ls.add(new Country(0L,"CNY","人民币",R.drawable.china));
        ls.add(new Country(1L,"HKD","港币",R.drawable.china));
        ls.add(new Country(2L,"MOP","澳门元",R.drawable.china));
        ls.add(new Country(3L,"USD","美元",R.drawable.usa));
        ls.add(new Country(4L,"EUR","欧元",R.drawable.italy));
        ls.add(new Country(5L,"JPY","日元",R.drawable.japan));
        ls.add(new Country(6L,"GBP","英镑",R.drawable.uk));
        ls.add(new Country(7L,"AUD","澳大利亚元",R.drawable.australia));
        ls.add(new Country(8L,"CAD","加拿大元",R.drawable.canada));
        ls.add(new Country(9L,"THB","泰国铢",R.drawable.thailand));
        ls.add(new Country(10L,"SGD","新加坡元",R.drawable.singapore));
        ls.add(new Country(11L,"DKK","丹麦克朗",R.drawable.denmark));
        ls.add(new Country(12L,"SEK","瑞典克朗",R.drawable.sweden));
        ls.add(new Country(13L,"CHF","瑞士法郎",R.drawable.switzerland));
        ls.add(new Country(14L,"KRW","韩国元",R.drawable.south_korea));
        ls.add(new Country(15L,"MYR","林吉特",R.drawable.malaysia));
        ls.add(new Country(16L,"NZD","新西兰元",R.drawable.new_zealand));
        ls.add(new Country(17L,"ZAR","南非兰特",R.drawable.south_africa));
    }
}
