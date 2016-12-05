package com.example.lenovo.goaaha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 刘萌 on 2016-12-01.
 */
public class LoginActivity extends AppCompatActivity {
    TextView register;
    Button btn;
    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn = (Button) findViewById(R.id.btn);
        register = (TextView) findViewById(R.id.register);
        img = (ImageView)findViewById(R.id.back);
        //新用户注册
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        //登录成功
    }
}
