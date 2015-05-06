package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends ActionBarActivity {

	Button button_back, button_logout;
	TextView tv_phoneOrEmail, tv_debitCardNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		button_back = (Button) findViewById(R.id.buttonback);
		button_back.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				System.exit(0);
			}
		});
		
		
		button_logout = (Button) findViewById(R.id.buttonlogout);
		button_logout.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
		
		tv_phoneOrEmail = (TextView) findViewById(R.id.textViewPhoneOrEmail);
		tv_debitCardNumber = (TextView) findViewById(R.id.textViewDebitCardNumber);
		
		SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
		
		if(sp.getString("username", "no username found") != "no username found")
		{
			tv_phoneOrEmail.setText("Username: " + sp.getString("username", "no username found"));	
		}
		
		if(sp.getString("cardNumber", "-1") == "-1")
		{
			tv_debitCardNumber.setText("Not Linked Yet");
		}
		else
		{
			tv_debitCardNumber.setText("************" + sp.getString("cardNumber", "no cardnumber found").substring(12));	
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
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
}
