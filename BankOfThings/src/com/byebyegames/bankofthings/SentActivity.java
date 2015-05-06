package com.byebyegames.bankofthings;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class SentActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sent);

		// store into history
		SharedPreferences sp = getSharedPreferences("bankofdata", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		
		// increments counter
		if(sp.getInt("historyCounter",-1) == -1)
		{
			editor.putString("historyTo0", sp.getString("to", "-1"));
			editor.putString("historyFor0", sp.getString("for", "-1"));
			editor.putString("historyAmount0", sp.getString("sendingAmount", "-1"));

			// store time
			Calendar c = Calendar.getInstance(); 
			String time = c.getTime().toString();
			editor.putString("historyTime0", time);
			editor.putInt("historyCounter", 1);

		}
		else
		{

			editor.putString("historyTo" + sp.getInt("historyCounter", -1), sp.getString("to", "-1"));
			editor.putString("historyFor" + sp.getInt("historyCounter", -1), sp.getString("for", "-1"));
			// store time
			Calendar c = Calendar.getInstance(); 
			String time = c.getTime().toString();
			editor.putString("historyTime" + sp.getInt("historyCounter", -1), time);
			editor.putString("historyAmount" + sp.getInt("historyCounter", -1), sp.getString("sendingAmount", "-1"));
			editor.putInt("historyCounter", sp.getInt("historyCounter", -1) + 1);

		}
		
		// commits
		editor.commit();

		
		new CountDownTimer(2000, 1000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
				startActivity(i);
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sent, menu);
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
