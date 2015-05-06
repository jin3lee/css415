package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends ActionBarActivity 
{
	
	Button button_next;
	EditText editTextUsername;
	TextView tv_errorEmpty, tv_errorAlreadyInUse;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
//		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//	    getSupportActionBar().hide();
//	    setContentView(R.layout.splash);
	    
		// leash next button
		button_next = (Button) findViewById(R.id.buttonnext);
		
		// leash text view
		editTextUsername = (EditText) findViewById(R.id.editTextUsername);
		tv_errorEmpty = (TextView) findViewById(R.id.textViewErrorEmpty);
		tv_errorEmpty.setVisibility(tv_errorEmpty.INVISIBLE);
		
		// initialize button on click methods
		initializeButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
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
	
	public void initializeButtons()
	{
		button_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), PasswordActivity.class);
				
				// Case: username to short
				if(editTextUsername.getText().toString().length() < 1)
				{
					tv_errorEmpty.setText("Enter username to continue.");
					tv_errorEmpty.setVisibility(tv_errorEmpty.VISIBLE);
				}
				else if(editTextUsername.getText().toString().equals("-1"))
				{
					tv_errorEmpty.setText("Invalid Username");
					tv_errorEmpty.setVisibility(tv_errorEmpty.VISIBLE);
				}
				// Case: username already in server
				else if(1 == 2)
				{
					// TODO: add server method here
				}
				// Case: valid username
				else
				{
					SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					
					// stores username
					// stores it in server but for now in shared preference
					editor.putString("username", ""+editTextUsername.getText().toString());
					editor.commit();
					
					startActivity(i);
				}
			}
		});
	}
}
