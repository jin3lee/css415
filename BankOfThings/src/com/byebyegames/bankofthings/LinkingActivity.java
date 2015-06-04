package com.byebyegames.bankofthings;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.byebyegames.bankofthigns.library.JSONParser;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LinkingActivity extends ActionBarActivity {

	Button[] button_numbers;

	Button button_abc, button_del, button_next;
	EditText tv_debitCardNumber;
	TextView tv_error;


	//URL to get JSON Array
	private static String url = "https://uwb.lumengaming.com/css415/rest.php?test=foo&a=register&phone=2534726639&card_number=2934982938472";
	
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
		button_abc = (Button) findViewById(R.id.buttonabc);
		button_del = (Button) findViewById(R.id.buttondel);

		// leash text view
		tv_debitCardNumber = (EditText) findViewById(R.id.textViewDollars);
		tv_error = (TextView) findViewById(R.id.textViewError);
		tv_error.setVisibility(tv_error.INVISIBLE);
		
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

				if(tv_debitCardNumber.getText().toString().length() != 16)
				{
					tv_error.setVisibility(tv_error.VISIBLE);
				}	
				else
				{
					// TODO Auto-generated method stub
					SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					
					// stores username
					// stores it in server but for now in shared preference
					editor.putString("cardNumber", tv_debitCardNumber.getText().toString());
					editor.commit();
					
					runChecker();
				}

			}


		});
	}
	
	
	
	
	private void runChecker()
	{
		SharedPreferences sp = getSharedPreferences("bankofdata",Context.MODE_PRIVATE);
		 
		JSONParse j = new JSONParse();
		j.setup(""+sp.getString("username","n/a"), ""+sp.getString("password","n/a"));
		Log.d("JDT","Trying " + sp.getString("username", "n/a") + " " + sp.getString("password", "n/a"));
		j.execute();
	}
	
	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;
		String jUsername = null;
		String jPassword = null;
		int isPassed;

		
		protected void setup(String username, String password)
		{
			jUsername = username;
			jPassword = password;
			isPassed = -1;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(LinkingActivity.this);
			pDialog.setMessage("Registering ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected JSONObject doInBackground(String... args) {

			JSONParser jParser = new JSONParser();

			String jUrl = "https://uwb.lumengaming.com/css415/rest.php?test=foo&a=register&phone=2534726639&card_number=2934982938472&username="+jUsername+"&password="+jPassword;

			Log.d("JDT","step 2\n\n" + jUrl);
			// Getting JSON from URL
			JSONObject json = jParser.getJSONFromUrl(jUrl);

			
			Log.d("JDT","doInBackground End");
			return json;
		}
		@Override
		protected void onPostExecute(JSONObject json) {

			pDialog.dismiss();

			
			
			if(json == null)
			{
				Log.d("JDT","isNULLLLLL!!!");
			}
			else if( json.toString().contains("Success"))
			{
				isPassed = 1;

				Log.d("JDT","onPostExecute Start " + json.toString());
				Log.d("JDT","onPostExecute passed");
			}
			else
			{
				isPassed = 2;

				Log.d("JDT","onPostExecute failed");
			}

			if(isPassed == 1)
			{
				Intent i = new Intent(getApplicationContext(), RegistrationSuccessActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);	
			}
			else
			{
				// tell user password and username don't match
				Intent i = new Intent(getApplicationContext(), RegistrationFailedActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);	
			}

			Log.d("JDT","onPostExecute End");
		}
	}
}
