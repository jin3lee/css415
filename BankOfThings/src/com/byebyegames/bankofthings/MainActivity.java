package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// leashes to textview that says "New to BankOfThings? Sign Up"
		TextView textLink_signup = (TextView) findViewById(R.id.textView1);
		textLink_signup.setTextColor(Color.parseColor("#0080FF"));

		// eashes to login button 
		Button button_login = (Button) findViewById(R.id.button1);		// leash to specified xml button
		button_login.setBackgroundColor(Color.parseColor("#00BFFF"));	// button color blue
		button_login.setTextColor(Color.parseColor("WHITE"));			// text color white
		
		// defines login button operations
		button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// leash to text view
                TextView debugDisplay = (TextView) findViewById(R.id.textView2);
                
                // declare temp variables
                String loginID = "";
                String password = "";
                EditText editText_email = (EditText) findViewById(R.id.editText1);
                EditText editText_password = (EditText) findViewById(R.id.editText2);
                
                // initialize temp variables
                loginID = editText_email.getText().toString();
                password = editText_password.getText().toString();
                
                // displays temp variables into debug textview 
                debugDisplay.setText("Your Email Address is: " + loginID 
                		+ "\n Your Password is: " + password);
                
                // TODO: access server, verify info, and switch to different activity
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
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
