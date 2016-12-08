package com.example.lenovo.goaaha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatHistorAdpter extends BaseAdapter {
    private Context context;
    private List<OneChat> loc = new ArrayList<>();
    public ChatHistorAdpter(Context context, List<OneChat> loc) {
        this.context = context;
        this.loc = loc;
    }

    @Override
    public int getCount() {
        return loc.size();
    }

    @Override
    public Object getItem(int position) {
        return loc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loc.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        }
        ImageView IVimg = (ImageView)convertView.findViewById(R.id.IvImg);
        IVimg.setImageResource(loc.get(position).getImg());
        TextView TvName = (TextView) convertView.findViewById(R.id.TvName);
        TvName.setText(loc.get(position).getName());
        TextView TvContent = (TextView) convertView.findViewById(R.id.TvContent);
        TvContent.setText(loc.get(position).getContent());
        TextView TvTime = (TextView) convertView.findViewById(R.id.TvTime);
        TvTime.setText(loc.get(position).getTime());
        return convertView;
    }
}