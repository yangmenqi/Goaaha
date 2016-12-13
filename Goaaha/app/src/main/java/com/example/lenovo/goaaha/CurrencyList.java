package com.example.lenovo.goaaha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList extends AppCompatActivity {
    private String name="";
    private String currency="";
    private ImageView back;
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
                switch (i) {
                    case 0:
                        name="CNY";
                        currency="人民币";
                        break;
                    case 1:
                        name="HKD";
                        currency="港币";
                        break;
                    case 2:
                        name="MOP";
                        currency="澳门元";
                        break;
                    case 3:
                        name="USD";
                        currency="美元";
                        break;
                    case 4:
                        name="EUR";
                        currency="欧元";
                        break;
                    case 5:
                        name="JPY";
                        currency="日元";
                        break;
                    case 6:
                        name="GBP";
                        currency="英镑";
                        break;
                    case 7:
                        name="AUD";
                        currency="澳大利亚元";
                        break;
                    case 8:
                        name="CAD";
                        currency="加拿大元";
                        break;
                    case 9:
                        name="THB";
                        currency="泰国铢";
                        break;
                    case 10:
                        name="SGD";
                        currency="新加坡元";
                        break;
                    case 11:
                        name="DKK";
                        currency="丹麦克朗";
                        break;
                    case 12:
                        name="SEK";
                        currency="瑞典克朗";
                        break;
                    case 13:
                        name="CHF";
                        currency="瑞士法郎";
                        break;
                    case 14:
                        name="KRW";
                        currency="韩国元";
                        break;
                    case 15:
                        name="MYR";
                        currency="林吉特";
                        break;
                    case 16:
                        name="NZD";
                        currency="新西兰元";
                        break;
                    case 17:
                        name="ZAR";
                        currency="南非兰特";
                        break;
                    default: return;
                }

                //接收上一页面传来的数据
                Intent country = getIntent();
                int countryid = country.getIntExtra("countryid",-1);

                //将数据传送到下一页面
                Intent intent = new Intent(CurrencyList.this,MainActivity.class);
                intent.putExtra("fragid",1);       //保证下一页面是货币兑换的fragment

                if(countryid==1){
                    String name2 = country.getStringExtra("name2");
                    String currency2 = country.getStringExtra("currency2");
                    intent.putExtra("name2",name2);
                    intent.putExtra("currency2",currency2);
                    intent.putExtra("name1",name);
                    intent.putExtra("currency1",currency);
                    intent.putExtra("countryid",1);
                }else{
                    String name1 = country.getStringExtra("name1");
                    String currency1 = country.getStringExtra("currency1");
                    intent.putExtra("name1",name1);
                    intent.putExtra("currency1",currency1);
                    intent.putExtra("name2",name);
                    intent.putExtra("currency2",currency);
                    intent.putExtra("countryid",2);
                }
                startActivity(intent);
            }
        });

        back = (ImageView)findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
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
