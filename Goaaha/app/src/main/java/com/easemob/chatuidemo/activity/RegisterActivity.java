/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easemob.chatuidemo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.db.Add;
import com.easemob.exceptions.EaseMobException;

/**
 * 注册页
 * 
 */
public class RegisterActivity extends BaseActivity {
	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;
	private String F1;
	private Spinner spinner_f;
	private ArrayAdapter<String> spinner_adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		confirmPwdEditText = (EditText) findViewById(R.id.confirm_password);
		spinner_f = (Spinner) findViewById(R.id.id_spinner_f);
		spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RegisterActivity.this.getResources().getStringArray(R.array.s_spinner));
		spinner_f.setAdapter(spinner_adapter);
		selectLanguage();
	}

	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		final String username = userNameEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		String confirm_pwd = confirmPwdEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
			userNameEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(confirm_pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
			confirmPwdEditText.requestFocus();
			return;
		} else if (!pwd.equals(confirm_pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Two_input_password), Toast.LENGTH_SHORT).show();
			return;
		}

		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
			final ProgressDialog pd = new ProgressDialog(this);
			pd.setMessage(getResources().getString(R.string.Is_the_registered));
			pd.show();

			new Thread(new Runnable() {
				public void run() {
					try {
						// 调用sdk注册方法
						EMChatManager.getInstance().createAccountOnServer(username, pwd);
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								// 保存用户名
								DemoApplication.getInstance().setUserName(username);
								//保存到APP服务器
								new Thread(new Add(handler,username, F1)).start();
								Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_LONG).show();
								finish();
							}
						});
					} catch (final EaseMobException e) {
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								int errorCode=e.getErrorCode();
								if(errorCode== EMError.NONETWORK_ERROR){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.USER_ALREADY_EXISTS){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.UNAUTHORIZED){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.ILLEGAL_USER_NAME){
								    Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name),Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed) + e.getMessage(), Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				}
			}).start();

		}
	}

	public void back(View view) {
		finish();
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:
					Toast.makeText(RegisterActivity.this, "APP注册成功", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Toast.makeText(RegisterActivity.this, "APP用户名已存在", Toast.LENGTH_SHORT).show();
					break;
			}
		}
	};
	private void selectLanguage() {
		spinner_f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String a = RegisterActivity.this.getResources().getStringArray(R.array.s_spinner)[position];
				if (a.equals("中文")) {
					F1 = "zh";
				} else if (a.equals("英语")) {
					F1 = "en";
				} else if (a.equals("粤语")) {
					F1 = "yue";
				} else if (a.equals("文言文")) {
					F1 = "wyw";
				}else if (a.equals("日语")) {
					F1 = "jp";
				}else if (a.equals("韩语")) {
					F1 = "kor";
				}else if (a.equals("法语")) {
					F1 = "fra";
				}else if (a.equals("西班牙")) {
					F1 = "spa";
				}else if (a.equals("泰语")) {
					F1 = "th";
				}else if (a.equals("阿拉伯语")) {
					F1 = "ara";
				}else if (a.equals("俄语")) {
					F1 = "ru";
				}else if (a.equals("葡萄牙语")) {
					F1 = "pt";
				}else if (a.equals("德语")) {
					F1 = "de";
				}else if (a.equals("意大利语")) {
					F1 = "it";
				}else if (a.equals("希腊语")) {
					F1 = "el";
				}else if (a.equals("荷兰语")) {
					F1 = "nl";
				}else if (a.equals("波兰语")) {
					F1 = "pl";
				}else if (a.equals("保加利亚语")) {
					F1 = "bul";
				}else if (a.equals("爱沙尼亚语")) {
					F1 = "est";
				}else if (a.equals("丹麦语")) {
					F1 = "dan";
				}else if (a.equals("芬兰语")) {
					F1 = "fin";
				}else if (a.equals("捷克语")) {
					F1 = "cs";
				}else if (a.equals("罗马尼亚语")) {
					F1 = "rom";
				}else if (a.equals("斯洛文尼亚语")) {
					F1 = "slo";
				}else if (a.equals("瑞典语")) {
					F1 = "swe";
				}else if (a.equals("匈牙利语")) {
					F1 = "hu";
				}else if (a.equals("繁体中文")) {
					F1 = "cht";
				}else if (a.equals("越南语")) {
					F1 = "vie";
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}
}
