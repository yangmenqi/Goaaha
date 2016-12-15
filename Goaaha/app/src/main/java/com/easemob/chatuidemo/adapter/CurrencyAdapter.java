package com.easemob.chatuidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.activity.Country;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends BaseAdapter {
    private Context context;
    private List<Country> country = new ArrayList<>();

    public CurrencyAdapter(Context c, List<Country> ls) {
        context = c;
        country = ls;
    }

    @Override
    public int getCount() {
        return country.size();
    }

    @Override
    public Object getItem(int i) {
        return country.get(i);
    }

    @Override
    public long getItemId(int i) {
        return country.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.item_currency,null);
        }
        ImageView ivIcon = (ImageView)view.findViewById(R.id.IvIcon);
        ivIcon.setImageResource(country.get(i).getUrl());
        TextView TvName = (TextView)view.findViewById(R.id.TvName);
        TvName.setText(country.get(i).getName());
        TextView TvCurrency = (TextView)view.findViewById(R.id.TvCurrency);
        TvCurrency.setText(country.get(i).getCurrency());

        return view;
    }
}