package com.example.lenovo.goaaha;

import android.support.v4.app.Fragment;
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
    private ImageView country1;
    private ImageView country2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_currency, container, false);
        country1 = (ImageView)view.findViewById(R.id.country1);
        country1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CurrencyList.class);
                startActivity(intent);
            }
        });
        country2 = (ImageView)view.findViewById(R.id.country2);
        country2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CurrencyList.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
