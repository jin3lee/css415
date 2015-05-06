package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ActionBarActivity {

	EditText et_username;
	EditText et_password;
	TextView tv_error;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
		Button buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
		tv_error = (TextView) findViewById(R.id.textViewError);
		et_username = (EditText) findViewById(R.id.editTextUsername);
		et_password = (EditText) findViewById(R.id.editTextPassword);
		
		tv_error.setVisibility(tv_error.INVISIBLE);
		
		SharedPreferences sp = getSharedPreferences("bankofdata", Context.MODE_PRIVATE);
		if(sp.getString("username", "-1").equals("-1"))
		{
			Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(i);
		}
		
		
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(checkUserNameAndPassword(et_username.getText().toString(), et_password.getText().toString()))
				{
					Intent i = new Intent(getApplicationContext(), SendActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	                startActivity(i);	
				}
				else
				{
					// tell user password and username don't match
					tv_error.setVisibility(tv_error.VISIBLE);
				}
			}
		});
		
		buttonSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);	
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean checkUserNameAndPassword(String username, String password)
	{
		boolean retVal = false;
		SharedPreferences sp = getSharedPreferences("bankofdata", Context.MODE_PRIVATE);
		if(sp.getString("username", "-1").equals(username) 
				&& sp.getString("password", "-1").equals(password))
		{
			retVal = true;
		}
		return retVal;
	}
}
