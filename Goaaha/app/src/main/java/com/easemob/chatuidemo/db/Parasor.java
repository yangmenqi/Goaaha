package com.easemob.chatuidemo.db;

import com.easemob.chatuidemo.domain.User_my;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77532 on 2016/12/20.
 */
public class Parasor {
    public static List<User_my> parase(String string){
        try {
            List<User_my> users = new ArrayList<User_my>();
            JSONArray jsonArray = new JSONArray(string);

            for (int i = 0;i<jsonArray.length();i++){
                users.add(new User_my(jsonArray.getJSONObject(i).getString("id"),jsonArray.getJSONObject(i).getString("password"),jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("from"),jsonArray.getJSONObject(i).getString("avatar")));
            }
            return users;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
