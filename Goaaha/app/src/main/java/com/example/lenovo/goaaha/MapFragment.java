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
        initId();
        setListener();
    }

    private void setListener() {
        MyListener listener = new MyListener();
        btnmap.setOnClickListener(listener);
    }

    private void initId() {
        btnmap=(Button)view.findViewById(R.id.btnmap);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.btnmap:
                    i.setClass(getContext(),MapActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}