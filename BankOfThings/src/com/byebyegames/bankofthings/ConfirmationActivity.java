package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ConfirmationActivity extends ActionBarActivity {

	Button button_back, button_cancel, button_send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);

		button_back = (Button) findViewById(R.id.buttonback);
		button_cancel = (Button) findViewById(R.id.buttoncancel);
		button_send = (Button) findViewById(R.id.buttonsend);

		button_send.setText("\nTo: " + getIntent().getExtras().getString("TO") 
				+ "\nFor: " + getIntent().getExtras().getString("FOR")
				+ "\nAmount: " + getIntent().getExtras().getString("AMOUNT")
				+"\n\n PRESS HERE TO SEND!");
		button_send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SentActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+getIntent().getExtras().getString("DEBITCARDNUMBER"));
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
				i.putExtra("AMOUNT", getIntent().getExtras().getString("AMOUNT"));
                startActivityForResult(i, 8);
			}
		});
		button_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		button_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finishActivity(0);
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirmation, menu);
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
