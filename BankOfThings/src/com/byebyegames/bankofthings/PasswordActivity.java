package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordActivity extends ActionBarActivity 
{
	
	Button button_next;
	EditText editTextPassword, editTextConfirmPassword;
	TextView tv_error;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		
		// leash next button
		button_next = (Button) findViewById(R.id.buttonnext);
		
		// leash text view
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
		
		tv_error = (TextView) findViewById(R.id.textViewErrorEmpty);
		tv_error.setVisibility(tv_error.INVISIBLE);
		
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
				Intent i = new Intent(getApplicationContext(), LinkingActivity.class);
				
				// Case: username to short
				if(editTextPassword.getText().toString().length() < 1)
				{
					tv_error.setVisibility(tv_error.VISIBLE);
					tv_error.setText("Enter a password to continue");
				}
				else if(editTextConfirmPassword.getText().toString().length() < 1)
				{
					tv_error.setVisibility(tv_error.VISIBLE);
					tv_error.setText("Enter confirmation password to continue");
				}
				// Case: passwords don't match
				else if(!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString()))
				{
					tv_error.setVisibility(tv_error.VISIBLE);
					tv_error.setText("Passwords Don't Match");
				}
				// Case: valid username
				else
				{
					SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					
					// stores username
					// stores it in server but for now in shared preference
					editor.putString("password", editTextPassword.getText().toString());
					editor.commit();
					
					startActivity(i);
				}
			}
		});
	}
}
