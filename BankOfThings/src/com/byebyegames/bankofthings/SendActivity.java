package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendActivity extends ActionBarActivity {

	Button[] button_numbers;
	
	Button button_period, button_del, button_send,
		button_settings, button_history;
	EditText editText_Dollars;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);
		
		// initialize button array
		button_numbers = new Button[10];
		
		// leash all buttons
		button_numbers[0] = (Button) findViewById(R.id.button0);
		button_numbers[1] = (Button) findViewById(R.id.button1);
		button_numbers[2] = (Button) findViewById(R.id.button2);
		button_numbers[3] = (Button) findViewById(R.id.button3);
		button_numbers[4] = (Button) findViewById(R.id.button4);
		button_numbers[5] = (Button) findViewById(R.id.button5);
		button_numbers[6] = (Button) findViewById(R.id.button6);
		button_numbers[7] = (Button) findViewById(R.id.button7);
		button_numbers[8] = (Button) findViewById(R.id.button8);
		button_numbers[9] = (Button) findViewById(R.id.button9);

		button_send = (Button) findViewById(R.id.buttonsend);
		button_send.setClickable(false);
		
		button_history = (Button) findViewById(R.id.buttonhistory);
		button_settings = (Button) findViewById(R.id.buttonsettings);
		button_period = (Button) findViewById(R.id.buttonperiod);
		button_del = (Button) findViewById(R.id.buttondel);
		
		// leash text view
		editText_Dollars= (EditText) findViewById(R.id.editViewDollars);
		editText_Dollars.setText("$0");
		
		// initialize button on click methods
		initializeButtons();
		button_send.setClickable(false);
		

        Log.d("JDT","PHONE OR EMAIL: "+getIntent().getExtras().getString("PHONEOREMAIL"));
        Log.d("JDT","DEBIT CARD: "+getIntent().getExtras().getString("DEBITCARDNUMBER"));
	}

	
	@Override
	public void onResume()
	{
		super.onResume();
		if(editText_Dollars.getText().toString().equals("$0"))
			button_send.setClickable(false);
		else
			button_send.setClickable(true);
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
		button_numbers[0].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(0);

				button_send.setClickable(false);
			}
		});
		button_numbers[1].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(1);
			}
		});
		button_numbers[2].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(2);
			}
		});
		button_numbers[3].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(3);
			}
		});
		button_numbers[4].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(4);
			}
		});
		button_numbers[5].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(5);
			}
		});
		button_numbers[6].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(6);
			}
		});
		button_numbers[7].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(7);
			}
		});
		button_numbers[8].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(8);
			}
		});
		button_numbers[9].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				initializeHelper(9);
			}
		});
		button_del.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				int length = editText_Dollars.getText().length();
				if(length > 1 && editText_Dollars.getText().charAt(1) != '0')
					editText_Dollars.setText(editText_Dollars.getText().subSequence(0, length-1));
				length = editText_Dollars.getText().length();
				if(length == 1)
				{
					editText_Dollars.setText("$0");
					button_send.setClickable(false);
				}
			}
		});
		button_period.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editText_Dollars.setText(editText_Dollars.getText() + ".");
			}
		});
		button_send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), ToActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+getIntent().getExtras().getString("DEBITCARDNUMBER"));
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
				i.putExtra("AMOUNT", editText_Dollars.getText());
                startActivity(i);
			}
		});
		button_settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SettingActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+getIntent().getExtras().getString("DEBITCARDNUMBER"));
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
                startActivityForResult(i,6);
			}
		});
		button_history.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+getIntent().getExtras().getString("DEBITCARDNUMBER"));
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
				startActivityForResult(i,4);
			}
		});
	}
	
	public void initializeHelper(int number)
	{
		if(editText_Dollars.getText().toString().equals("$0"))
		{
			editText_Dollars.setText("$"+ Integer.toString(number));
		}else{
			editText_Dollars.setText(editText_Dollars.getText() + Integer.toString(number));
		}
		
		button_send.setClickable(true);
	}
}
