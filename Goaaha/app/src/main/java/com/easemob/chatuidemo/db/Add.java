package com.easemob.chatuidemo.db;

import android.os.Handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 77532 on 2016/12/20.
 */
public class Add implements Runnable {
    private Handler handler;
    private String id;
    private String from;

    public Add(Handler handler, String id, String from) {
        this.handler = handler;
        this.id =id;
        this.from = from;

    }

    @Override
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://192.168.1.102/blog/register?id="+id+"&from="+from);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            StringBuffer stringBuffer = new StringBuffer("");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            String str = null;
            while ((str=bufferedReader.readLine())!=null){
                stringBuffer.append(str);
            }
            if ("ok".equals(stringBuffer.toString())){
                handler.sendEmptyMessage(0);
            }else {
                handler.sendEmptyMessage(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
