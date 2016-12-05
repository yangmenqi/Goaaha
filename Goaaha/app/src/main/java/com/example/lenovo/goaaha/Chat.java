package com.example.lenovo.goaaha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.lenovo.goaaha.R;

/**
 * Created by 小妖精停一下 on 2016/12/1.
 */
public class Chat extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_chat,container,false);
    }
}