package com.example.lenovo.goaaha;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lenovo on 2016/11/7.
 */
public class MapFragment extends Fragment {
    private Context context; //上下文环境
    private View view;
    private Button btnmap;
    @Override
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_map,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnmap=(Button)view.findViewById(R.id.btnmap);
        //采用匿名内部类的方式，给按钮组件对象注册事件监听器
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(context,MapsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }


}