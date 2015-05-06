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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendActivity extends ActionBarActivity {

	Button[] button_numbers;
	
	Button button_period, button_del, button_send,
		button_settings, button_history;
	TextView tv_Dollars;
	
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
		tv_Dollars= (TextView) findViewById(R.id.textViewDollars);
		tv_Dollars.setText("$0");
		
		// initialize button on click methods
		initializeButtons();
		button_send.setClickable(false);
		
	}

	
	@Override
	public void onResume()
	{
		super.onResume();
		if(tv_Dollars.getText().toString().equals("$0"))
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
				int length = tv_Dollars.getText().length();
				if(length > 1)
				{
					tv_Dollars.setText(tv_Dollars.getText().subSequence(0, length-1));
				}
				
				length = tv_Dollars.getText().length();
				if(length == 1)
				{
					tv_Dollars.setText("$0");
					button_send.setClickable(false);
				}
			}
		});
		button_period.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean hasPeriod = false;
				// TODO Auto-generated method stub
				for(int i = 0; i < tv_Dollars.getText().toString().length(); i++)
				{
					if(tv_Dollars.getText().toString().charAt(i) == '.') 
					{
						hasPeriod = true;
					}
				}
				
				if(!hasPeriod)
				{
					tv_Dollars.setText(tv_Dollars.getText() + ".");	
				}
			}
		});
		button_send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(getCountOfCharactersAfterPeriod())
				{
				case -1:
					tv_Dollars.setText(tv_Dollars.getText() + ".00");	
					break;
				case 0:
					tv_Dollars.setText(tv_Dollars.getText() + "00");	
					break;
				case 1:
					tv_Dollars.setText(tv_Dollars.getText() + "0");	
					break;
				default:
						break;
				}
				
				SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString("sendingAmount", ""+tv_Dollars.getText().toString());
				editor.putBoolean("pending", true);
				editor.commit();
				
				Intent i = new Intent(getApplicationContext(), ToActivity.class);
                startActivity(i);
			}
		});
		button_settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivityForResult(i,6);
			}
		});
		button_history.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
				startActivityForResult(i,4);
			}
		});
	}
	
	public void initializeHelper(int number)
	{
		if(tv_Dollars.getText().toString().equals("$0"))
		{
			tv_Dollars.setText("$"+ Integer.toString(number));
		}
		else if(getCountOfCharactersAfterPeriod() > 2)
		{
			// do not enter a number after two decimals
		}
		else if(getCountOfCharactersAfterPeriod() == -1)
		{
			// caps the amount possible to send to 7 digits, excluding cents
			if(!(tv_Dollars.getText().toString().length() > 7))
			{
				tv_Dollars.setText(tv_Dollars.getText() + Integer.toString(number));
			}
		}
		else 
		{
			tv_Dollars.setText(tv_Dollars.getText() + Integer.toString(number));
		}
		
		button_send.setClickable(true);
	}
	
	// returns amount of characters after the period
	public int getCountOfCharactersAfterPeriod()
	{
		int retVal = -1;	// -1 = no period | any other number represents how many characters are after the period
		int locationOfPeriod = -1;
		boolean hasPeriod = false;
		
		for(int i = 0; i < tv_Dollars.getText().toString().length(); i++)
		{
			if(tv_Dollars.getText().toString().charAt(i) == '.')
			{
				locationOfPeriod = i;
				hasPeriod = true;
			}
		}
		
		if(hasPeriod)
		{
			retVal = tv_Dollars.getText().toString().length() - locationOfPeriod;
		}
		
		return retVal;
	}
}
