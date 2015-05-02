package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LinkingActivity extends ActionBarActivity {

	Button[] button_numbers;
	
	Button button_abc, button_del, button_next, button_skip;
	EditText tv_debitCardNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linking);
		
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

		button_next = (Button) findViewById(R.id.buttonnext);
		button_skip = (Button) findViewById(R.id.buttonskip);
		button_abc = (Button) findViewById(R.id.buttonabc);
		button_del = (Button) findViewById(R.id.buttondel);
		
		// leash text view
		tv_debitCardNumber = (EditText) findViewById(R.id.textViewDollars);
		
		// initialize button on click methods
		initializeButtons();
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
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "0");
			}
		});
		button_numbers[1].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "1");
			}
		});
		button_numbers[2].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "2");
			}
		});
		button_numbers[3].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "3");
			}
		});
		button_numbers[4].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "4");
			}
		});
		button_numbers[5].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "5");
			}
		});
		button_numbers[6].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "6");
			}
		});
		button_numbers[7].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "7");
			}
		});
		button_numbers[8].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "8");
			}
		});
		button_numbers[9].setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				tv_debitCardNumber.setText(tv_debitCardNumber.getText() + "9");
			}
		});
		button_del.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				int length = tv_debitCardNumber.getText().length();
				if(length > 0)
					tv_debitCardNumber.setText(tv_debitCardNumber.getText().subSequence(0, length-1));
			}
		});
		button_abc.setClickable(false);
		button_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+tv_debitCardNumber.getText().toString());
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
                startActivity(i);
			}
		});
		button_skip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
				i.putExtra("DEBITCARDNUMBER", ""+tv_debitCardNumber.getText().toString());
				i.putExtra("PHONEOREMAIL", ""+getIntent().getExtras().getString("PHONEOREMAIL"));
                startActivity(i);
			}
		});
	}
}
