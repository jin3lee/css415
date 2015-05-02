package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
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

		new CountDownTimer(2000, 1000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+getIntent().getExtras().getString("DEBITCARDNUMBER"));
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
				startActivityForResult(i, 3);
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
