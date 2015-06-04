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


public class LoginActivity extends ActionBarActivity {
	//JSON Node Names 
	private static final String TAG_PAYLOAD = "payload";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_RESULT = "result";

	EditText et_username;
	EditText et_password;
	TextView tv_error;

	//URL to get JSON 
	private static String url = "http://uwb.lumengaming.com/css415/rest.php?test=1&a=login";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
		Button buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
		tv_error = (TextView) findViewById(R.id.textViewError);
		et_username = (EditText) findViewById(R.id.editTextUsername);
		et_password = (EditText) findViewById(R.id.editTextPassword);

		tv_error.setVisibility(tv_error.INVISIBLE);


		// Case: User doesn't have info on the phone so the activity goes straight to registration
		SharedPreferences sp = getSharedPreferences("bankofdata", Context.MODE_PRIVATE);
		if(sp.getString("username", "-1").equals("-1"))
		{
			Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
			startActivity(i);
		}

		buttonLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				checkUserNameAndPassword(et_username.getText().toString(), et_password.getText().toString());
			}
		});

		buttonSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);	
			}
		});


	}

	// HTTP 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	public boolean checkUserNameAndPassword(String username, String password)
	{
		boolean retVal = false;
		//		SharedPreferences sp = getSharedPreferences("bankofdata", Context.MODE_PRIVATE);
		//		if(sp.getString("username", "-1").equals(username) 
		//				&& sp.getString("password", "-1").equals(password))
		Log.d("JDT", "STARTING");
		JSONParse j = new JSONParse();
		j.setup(username, password);
		j.execute();

		Log.d("JDT", "ENDING");
		if(j.getPassed() == 1)
		{
			retVal = true;
		}
		return retVal;
	}

	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;
		String jUsername = null;
		String jPassword = null;
		int isPassed;

		protected int getPassed()
		{
			return isPassed;
		}
		protected void setup(String username, String password)
		{
			jUsername = username;
			jPassword = password;
			isPassed = -1;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(LoginActivity.this);
			pDialog.setMessage("Attempting to Login ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();

			String jUrl = url + "&username=" + jUsername + "&password=" + jPassword;
			// Getting JSON from URL
			JSONObject json = jParser.getJSONFromUrl(jUrl);

			return json;
		}
		@Override
		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();

			if(json.toString().contains("\"payload\":true"))
			{
				isPassed = 1;
				Log.d("JDT", "isPassed = true");
			}
			else
			{
				isPassed = 2;
			}

			if(isPassed == 1)
			{
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);	
			}
			else
			{
				// tell user password and username don't match
				tv_error.setVisibility(tv_error.VISIBLE);
			}
		}
	}
}
