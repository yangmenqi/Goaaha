package com.example.lenovo.goaaha;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2016/11/7.
 */
public class CurrencyFragment extends Fragment {
    private ImageView country;
    private List<Country> ls = new ArrayList<Country>();
    private CurrencyAdapter myadapter;
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_currency, container, false);
        country = (ImageView) view.findViewById(R.id.country1);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CurrencyList.class);
                startActivity(intent);
            }
        });

        //1、修改当前activity的布局文件，加入ListView控件并设置id
        //2、定义数据源，字符串数组
        //3、定义item布局，使用Android内置listview的item布局
        //4、根据数据源与item布局定义adapter
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //5、得到ListView对象，并设置adapter
        getData();
        myadapter = new CurrencyAdapter(getActivity(),ls);
        lv = (ListView)view.findViewById(R.id.CountryLv);
        lv.setAdapter(myadapter);
        //6、给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"你点击了"+i+"项",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void getData() {
        ls.add(new Country(0L,"CNY","人民币",R.drawable.china));
        ls.add(new Country(1L,"HKD","港币",R.drawable.china));
        ls.add(new Country(2L,"USD","美元",R.drawable.usa));
        ls.add(new Country(3L,"KRW","韩元",R.drawable.south_korea));
        ls.add(new Country(4L,"JPY","日元",R.drawable.japan));
        ls.add(new Country(5L,"CAD","加拿大元",R.drawable.canada));
    }
}
