package com.byebyegames.bankofthings;

import java.util.Random;

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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class SendActivity extends ActionBarActivity {
	Button button_review = null;
	RadioButton radio_goodOrServ = null;
	RadioButton radio_friendsOrFam = null;
	EditText et_to = null;
	EditText et_amount = null;
	EditText et_message = null;
	
	TextView funds = null;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);

		// sets background color to white
		setActivityBackgroundColor(Color.WHITE);

		// leashes to radio buttons
		radio_friendsOrFam = (RadioButton)findViewById(R.id.radioButton1);
		radio_goodOrServ = (RadioButton)findViewById(R.id.radioButton2);
		button_review = (Button)findViewById(R.id.button1);
		et_to = (EditText)findViewById(R.id.editText0);
		et_amount = (EditText)findViewById(R.id.editText1);
		et_message = (EditText)findViewById(R.id.editText2);
		funds = (TextView)findViewById(R.id.textView2);
		
		//TODO: update funds
		Random r = new Random();
		funds.setText("Available Funds: $" + (int)(r.nextInt(100)) + ".00");
		funds.setTextColor(Color.BLACK);
		// sets review button
		button_review.setBackgroundColor(Color.parseColor("#B2ECFF"));
		button_review.setTextColor(Color.parseColor("White"));
		
		// sets button to unactive by default
		button_review.setClickable(false);
		
		radio_friendsOrFam.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                //handle the boolean flag here. 
                  if(arg1==true){
                	  if(radio_goodOrServ.isChecked()) {
                		  radio_goodOrServ.setChecked(false);
                	  }
                  }

        		  button_review.setClickable(isReadyForReview(radio_friendsOrFam, 
        				  radio_goodOrServ, button_review));
            }
        });
		
		radio_goodOrServ.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                //handle the boolean flag here. 
                  if(arg1==true){
                	  if(radio_friendsOrFam.isChecked()) {
                		  radio_friendsOrFam.setChecked(false);
                	  }
                  }
        		  button_review.setClickable(isReadyForReview(radio_friendsOrFam, 
        				  radio_goodOrServ, button_review));
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
	
	public boolean isReadyForReview(RadioButton arg0, RadioButton arg1, Button reviewButton)
	{
		boolean retVal = false;
		
		// tests for review
		if((arg0.isChecked() 
				|| arg1.isChecked()))
		{
			retVal = true;
		}
		
		// changes button color
		if(retVal)
		{
			reviewButton.setBackgroundColor(Color.parseColor("#00BFFF"));
			reviewButton.setTextColor(Color.parseColor("White"));
		}else{
			reviewButton.setBackgroundColor(Color.parseColor("#B2ECFF"));
			reviewButton.setTextColor(Color.parseColor("White"));
		}
		return retVal;
	}
}
