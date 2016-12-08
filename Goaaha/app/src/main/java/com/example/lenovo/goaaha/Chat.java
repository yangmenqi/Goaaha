package com.example.lenovo.goaaha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.goaaha.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小妖精停一下 on 2016/12/1.
 */
public class Chat extends Fragment {
    @Nullable
    private List<OneChat> oc = new ArrayList<>();
    private ListView lv;
    private ChatHistorAdpter chatHistorAdpter;
    private View rootView;//缓存Fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_chat, container, false);
        lv = (ListView) rootView.findViewById(R.id.id_chat_Lv);
        chatHistorAdpter = new ChatHistorAdpter(getActivity(), oc);
        getData();
        lv.setAdapter(chatHistorAdpter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),ChatroomActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void getData() {
        oc.add(new OneChat(0L, R.drawable.p0, "聊天群0", "孔还庆孔还庆孔还庆0", "8:00"));
        oc.add(new OneChat(1L, R.drawable.p1, "聊天群1", "孔还庆孔还庆孔还庆1", "9:00"));
        /*oc.add(new OneChat(2L,R.drawable.p2,"聊天群2","孔还庆孔还庆孔还庆2","10:00"));
        oc.add(new OneChat(3L,R.drawable.p3,"聊天群3","孔还庆孔还庆孔还庆3","11:00"));
        oc.add(new OneChat(4L,R.drawable.p4,"聊天群4","孔还庆孔还庆孔还庆4","12:00"));
        oc.add(new OneChat(5L,R.drawable.p5,"聊天群5","孔还庆孔还庆孔还庆5","13:00"));
        oc.add(new OneChat(6L,R.drawable.p6,"聊天群6","孔还庆孔还庆孔还庆6","14:00"));
        oc.add(new OneChat(7L,R.drawable.p7,"聊天群7","孔还庆孔还庆孔还庆7","15:00"));
        oc.add(new OneChat(8L,R.drawable.p8,"聊天群8","孔还庆孔还庆孔还庆8","16:00"));*/
    }
}