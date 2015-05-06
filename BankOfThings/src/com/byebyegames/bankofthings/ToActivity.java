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

public class ToActivity extends ActionBarActivity {

	Button button_back, button_send;
	EditText et_for, et_to;
	TextView tv_amount, tv_error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to);

		button_back = (Button)findViewById(R.id.buttonback);
		button_send = (Button)findViewById(R.id.buttonsend);

		et_for = (EditText)findViewById(R.id.editTextFor);
		et_to = (EditText)findViewById(R.id.editTextTo);
		tv_amount = (TextView)findViewById(R.id.textViewAmount);
		tv_error = (TextView)findViewById(R.id.textViewError);
		tv_error.setVisibility(tv_error.INVISIBLE);

		SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
		tv_amount.setText("Amount: " + sp.getString("sendingAmount", "Loading Amount..."));

		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putBoolean("pending", false);

				System.exit(0);
			}
		});

		button_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				// Case: to/for data is valid
				if(et_to.getText().toString().length() > 0 && et_for.getText().toString().length() > 0)
				{
					// store to/for data
					SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("to", et_to.getText().toString());
					editor.putString("for", et_for.getText().toString());
					editor.commit();

					Intent i = new Intent(getApplicationContext(), ConfirmationActivity.class);
					startActivity(i);
				}
				// Case: tells user to fill the data before continuing
				else
				{
					tv_error.setVisibility(tv_error.VISIBLE);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to, menu);
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
