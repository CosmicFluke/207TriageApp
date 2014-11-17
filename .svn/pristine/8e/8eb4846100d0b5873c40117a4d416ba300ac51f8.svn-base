package com.group0931.triagephase2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * An {@code Activity} that displays a given message. 
 * @author Christina Chung, Angel You, Asher MindenWebb.
 *
 */
public class MessageDisplayActivity extends Activity {
	
	/**The {@code TextView} that will display a given message.*/
	TextView messageDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_display);
		Intent intent = getIntent();
		String visitMessage = intent.getStringExtra("DISPLAYMESSAGE");
		messageDisplay = (TextView) findViewById(R.id.displayMessageView);
		messageDisplay.setText(visitMessage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_display, menu);
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
	
	/**
	 * Closes this {@code MessageDisplayActivity}.
	 */
	public void close(){
		this.finish();
	}
}
