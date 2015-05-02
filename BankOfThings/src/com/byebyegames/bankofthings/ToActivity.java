package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ToActivity extends ActionBarActivity {

	Button button_back, button_send;
	EditText et_for, et_to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to);

		button_back = (Button)findViewById(R.id.buttonback);
		button_send = (Button)findViewById(R.id.buttonsend);

		et_for = (EditText)findViewById(R.id.editTextFor);
		et_to = (EditText)findViewById(R.id.editTextTo);
		
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		button_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), ConfirmationActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+getIntent().getExtras().getString("DEBITCARDNUMBER"));
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
				i.putExtra("AMOUNT", getIntent().getExtras().getString("AMOUNT"));
				i.putExtra("TO", "" + et_to.getText());
				i.putExtra("FOR", "" + et_for.getText());
                startActivity(i);
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
