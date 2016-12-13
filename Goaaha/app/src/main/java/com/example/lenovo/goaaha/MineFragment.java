package com.example.lenovo.goaaha;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lenovo on 2016/11/7.
 */
public class MineFragment extends Fragment {
    TextView tv;
    ImageView it1,it2,it3,it4;//四个进入页面
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.layout_mine,container,false);
        View view = inflater.inflate(R.layout.layout_mine, null);

        tv = (TextView) view.findViewById(R.id.login1);
        it1=(ImageView)view.findViewById(R.id.inter1);
        it2=(ImageView)view.findViewById(R.id.inter2);
        it3=(ImageView)view.findViewById(R.id.inter3);
        it4=(ImageView)view.findViewById(R.id.inter4);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login1:
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        //我的账号界面
        it1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.inter1:
                        Intent intent = new Intent(getActivity(), MineDetailsActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        //设置界面
        it2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.inter2:
                        Intent intent = new Intent(getActivity(), SettingsActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        it3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.inter3:
                        Intent intent = new Intent(getActivity(), AboutActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        it4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.inter4:
                        Intent intent = new Intent(getActivity(), ExitActivity.class);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }

}