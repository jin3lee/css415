package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

		// sets background color to white
		setActivityBackgroundColor(Color.WHITE);

		// leashes to textview that says "New to BankOfThings? Sign Up"
		Button textLink_signup = (Button) findViewById(R.id.buttonback);
		textLink_signup.setTextColor(Color.parseColor("#0080FF"));
		textLink_signup.setBackgroundColor(Color.parseColor("WHITE"));
		textLink_signup.setOnClickListener(new View.OnClickListener() 	// goes to signup website
		{
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://byebyegames.com/signup.html"));
				startActivity(browserIntent);
			}
		});

		// leashes to login button 
		Button button_login = (Button) findViewById(R.id.buttonsend);		// leash to specified xml button
		button_login.setBackgroundColor(Color.parseColor("#00BFFF"));	// button color blue
		button_login.setTextColor(Color.parseColor("WHITE"));			// text color white
		
		// defines login button operations
		button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// leash to text view
                TextView debugDisplay = (TextView) findViewById(R.id.textViewDollars);
                
                // declare temp variables
                String loginID = "";
                String password = "";
                EditText editText_email = (EditText) findViewById(R.id.editTextto);
                EditText editText_password = (EditText) findViewById(R.id.editText2);
                
                // initialize temp variables
                loginID = editText_email.getText().toString();
                password = editText_password.getText().toString();
                
                // displays temp variables into debug textview 
                debugDisplay.setText("Your Email Address is: " + loginID 
                		+ "\n Your Password is: " + password);
                
                // TODO: access server, verify info, and switch to different activity
                Intent i = new Intent(getApplicationContext(), SendActivity.class);
                startActivity(i);
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
	
	// changes background color of activity
	public void setActivityBackgroundColor(int color) {
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(color);
	}
}
