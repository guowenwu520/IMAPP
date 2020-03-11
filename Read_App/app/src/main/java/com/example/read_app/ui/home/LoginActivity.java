package com.example.read_app.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.read_app.R;
import com.example.read_app.base.BaseActivity;
import com.example.read_app.entity.DongTai;
import com.example.read_app.entity.Imgs;
import com.example.read_app.source.DemoDBManager;

/**
 * Login screen
 * 
 */
public class LoginActivity extends BaseActivity {
	private static final String TAG = "LoginActivity";
	public static final int REQUEST_CODE_SETNICK = 1;
	private EditText usernameEditText;
	private EditText passwordEditText;

	private boolean progressShow;
	private boolean autoLogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// enter the main activity if already logged in
		SharedPreferences sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
		String name=sharedPreferences.getString("name","kjkj");
		String pass=sharedPreferences.getString("pass","kjkj");
		new AsyncTask<Void,Void,Void>(){
			boolean fign=false;
			@Override
			protected void onPostExecute(Void aVoid) {
				super.onPostExecute(aVoid);
				if(getIntent().getIntExtra("fign",0)==1)fign=false;
				if(fign){
					startActivity(new Intent(LoginActivity.this, MainActivity.class));finish();
				}
			}

			@Override
			protected Void doInBackground(Void... voids) {
				fign=DemoDBManager.getInstance().isLogn(pass,name);
				return null;
			}
		}.execute();
		setContentView(R.layout.em_activity_login);

		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);

		// if user changed, clear the password
		usernameEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passwordEditText.setText(null);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))) {
					login(null);
					return true;
				}
				else{
					return false;
				}
			}
		});



	}

	/**
	 * login
	 * 
	 * @param view
	 */
	public void login(View view) {
		String currentUsername = usernameEditText.getText().toString().trim();
		String currentPassword = passwordEditText.getText().toString().trim();

		if (TextUtils.isEmpty(currentUsername)) {
			Toast.makeText(this,"帐号不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(currentPassword)) {
			Toast.makeText(this, "密码不能空", Toast.LENGTH_SHORT).show();
			return;
		}

		new AsyncTask<Void,Void,Void>(){
			boolean fign=false;
			@Override
			protected void onPostExecute(Void aVoid) {
				super.onPostExecute(aVoid);
				if(fign){
					SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
					SharedPreferences.Editor editor=sharedPreferences.edit();
					editor.putString("name",currentUsername);
					editor.putString("pass",currentPassword);
					editor.putString("url","");
					editor.commit();
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);finish();
				}else {
					Toast.makeText(LoginActivity.this, "帐号或者密码错误", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			protected Void doInBackground(Void... voids) {
				 fign=DemoDBManager.getInstance().isLogn(currentPassword,currentUsername);
				return null;
			}
		}.execute();
	}

	/**
	 * register
	 * 
	 * @param view
	 */
	public void register(View view) {
		startActivityForResult(new Intent(this, RegisterActivity.class), 0);
	}

	/**
	 * SDK service check
	 *
	 */

	@Override
	protected void onResume() {
		super.onResume();
		if (autoLogin) {
			return;
		}
	}
}
