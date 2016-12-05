package com.example.lenovo.goaaha;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Lenovo on 2016/11/7.
 */
public class ChatFragment extends Fragment {


    private Chat chat_fragment;
    private Contacts contacts_fragment;

    private Fragment fragments[];
    private Button[] mTabs;
    private int index=0;
    // 当前fragment的index
    private int currentTabIndex=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chat_fragment = new Chat();
        contacts_fragment = new Contacts();
        fragments = new Fragment[]{chat_fragment, contacts_fragment};
        getChildFragmentManager().beginTransaction().add(R.id.id_in_contaner, chat_fragment).add(R.id.id_in_contaner, contacts_fragment).hide(contacts_fragment).show(chat_fragment).commit();
        mTabs = new Button[2];
        mTabs[0] = (Button) container.findViewById(R.id.id_btn_chat);
        mTabs[1] = (Button) container.findViewById(R.id.id_btn_contacts);
        mTabs[0].setSelected(true);

        return inflater.inflate(R.layout.layout_chatfragment, container, false);
    }

    /*private void getViews() {

        mTabs = new Button[2];
        mTabs[0] = (Button) findViewById(R.id.id_btn_chat);
        mTabs[1] = (Button) getActivity().findViewById(R.id.id_btn_contacts);
        mTabs[0].setSelected(true);
        registerForContextMenu(mTabs[1]);
    }*/

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn_chat:
                index = 0;
                break;
            case R.id.id_btn_contacts:
                index = 1;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx =  getChildFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.id_in_contaner, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
}