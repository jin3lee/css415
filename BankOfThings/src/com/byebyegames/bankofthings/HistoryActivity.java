package com.byebyegames.bankofthings;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HistoryActivity extends ActionBarActivity {
	
	Button button_back;
	ListView lv_recieved;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		populateListView();
		
		button_back = (Button) findViewById(R.id.buttonback);
		button_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), SendActivity.class);
                startActivity(i);
			}
		});
		
	}

	private void populateListView() {
		// populate the string array
		SharedPreferences sp = getSharedPreferences("bankofdata", Context.MODE_PRIVATE);
		int historyCount = sp.getInt("historyCounter", 0);
		
		// create array
		String[] myItems = new String[historyCount];
		
		// populate the array
		for(int i = 0; i < historyCount; i++)
		{
			myItems[i] = "To: " + sp.getString("historyTo" + i, "error" + i) + i;
			myItems[i] += "\nFor: " + sp.getString("historyFor" + i, "error" + i);
			myItems[i] += "\nAmount: " + sp.getString("historyAmount" + i, "error" + i);
			myItems[i] += "\n" + sp.getString("historyTime" + i, "error" + i);
		}
		
		// build adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,
				R.layout.historyitem,
				myItems);
		
		// configure listview
		ListView list = (ListView) findViewById(R.id.listViewHistory);
		list.setAdapter(adapter);
		
		Log.d("JDT", "hitttit" + sp.getInt("historyCounter",-1));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
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
