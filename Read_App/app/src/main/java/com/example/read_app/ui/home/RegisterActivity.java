/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
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
package com.example.read_app.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.read_app.R;
import com.example.read_app.base.BaseActivity;
import com.example.read_app.entity.User;
import com.example.read_app.source.DemoDBManager;


/**
 * register screen
 * 
 */
public class RegisterActivity extends BaseActivity {
	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.em_activity_register);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		confirmPwdEditText = (EditText) findViewById(R.id.confirm_password);
	}

	public void register(View view) {
		final String username = userNameEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		String confirm_pwd = confirmPwdEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this,"帐号不能为空", Toast.LENGTH_SHORT).show();
			userNameEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(confirm_pwd)) {
			Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show();
			confirmPwdEditText.requestFocus();
			return;
		} else if (!pwd.equals(confirm_pwd)) {
			Toast.makeText(this, "密码不一直", Toast.LENGTH_SHORT).show();
			return;
		}

		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {

			new AsyncTask<Void,Void,Void>(){
				boolean fign=true;
				@Override
				protected void onPostExecute(Void aVoid) {
					super.onPostExecute(aVoid);
					if(fign){
						Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
						finish();
					}
				}

				@Override
				protected Void doInBackground(Void... voids) {
					User user=new User();
					user.setName(username);
					user.setPass(pwd);
					DemoDBManager.getInstance().addUser(user);
					return null;
				}
			}.execute();

		}
	}

	public void back(View view) {
		finish();
	}

}
