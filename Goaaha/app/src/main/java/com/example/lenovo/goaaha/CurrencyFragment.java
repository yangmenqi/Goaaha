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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2016/11/7.
 */
public class CurrencyFragment extends Fragment {
    private ImageView country1;
    private ImageView country2;
    private TextView name1;
    private TextView name2;
    private TextView currency1;
    private TextView currency2;
    private String name_1;
    private String currency_1;
    private String name_2;
    private String currency_2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_currency, container, false);

        //接受上一页面传值，并赋值
        Intent intentname=getActivity().getIntent();
        name_1= intentname.getStringExtra("name1");
        currency_1 = intentname.getStringExtra("currency1");
        name_2= intentname.getStringExtra("name2");
        currency_2 = intentname.getStringExtra("currency2");

        name1 = (TextView)view.findViewById(R.id.name1);
        name2 = (TextView)view.findViewById(R.id.name2);
        currency1 = (TextView)view.findViewById(R.id.currency1);
        currency2 = (TextView)view.findViewById(R.id.currency2);
        country1 = (ImageView)view.findViewById(R.id.country1);
        country2 = (ImageView)view.findViewById(R.id.country2);

        name1.setText(name_1);
        currency1.setText(currency_1);
        name2.setText(name_2);
        currency2.setText(currency_2);

        if(name1.getText().equals("")){
            name1.setText("CNY");
            currency1.setText("人民币");
        }
        if(name2.getText().equals("")){
            name2.setText("USD");
            currency2.setText("美元");
        }

        if(name1.getText().equals("CNY")){country1.setImageResource(R.drawable.china);}
        if(name1.getText().equals("HKD")){country1.setImageResource(R.drawable.china);}
        if(name1.getText().equals("USD")){country1.setImageResource(R.drawable.usa);}
        if(name1.getText().equals("KRW")){country1.setImageResource(R.drawable.south_korea);}
        if(name1.getText().equals("JPY")){country1.setImageResource(R.drawable.japan);}
        if(name1.getText().equals("CAD")){country1.setImageResource(R.drawable.canada);}
        if(name2.getText().equals("CNY")){country2.setImageResource(R.drawable.china);}
        if(name2.getText().equals("HKD")){country2.setImageResource(R.drawable.china);}
        if(name2.getText().equals("USD")){country2.setImageResource(R.drawable.usa);}
        if(name2.getText().equals("KRW")){country2.setImageResource(R.drawable.south_korea);}
        if(name2.getText().equals("JPY")){country2.setImageResource(R.drawable.japan);}
        if(name2.getText().equals("CAD")){country2.setImageResource(R.drawable.canada);}

        country1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(),CurrencyList.class);
                intent1.putExtra("name1",name1.getText());
                intent1.putExtra("currency1",currency1.getText());
                intent1.putExtra("country1",country1.getImageAlpha());
                intent1.putExtra("name2",name2.getText());
                intent1.putExtra("currency2",currency2.getText());
                intent1.putExtra("country2",country1.getImageAlpha());
                intent1.putExtra("countryid",1);
                startActivity(intent1);
            }
        });

        country2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(),CurrencyList.class);
                intent2.putExtra("name1",name1.getText());
                intent2.putExtra("currency1",currency1.getText());
                intent2.putExtra("name2",name2.getText());
                intent2.putExtra("currency2",currency2.getText());
                intent2.putExtra("countryid",2);
                startActivity(intent2);
            }
        });
        return view;
    }
}
