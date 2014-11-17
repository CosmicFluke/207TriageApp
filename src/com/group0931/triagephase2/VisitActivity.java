package com.group0931.triagephase2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

/**
 * An Activity for displaying a {@code PatientVisit}.
 * @author Christina Chung, Asher MindenWebb, Angel You.
 *
 */
public class VisitActivity extends Activity {

	
	PatientVisit visit;
	Patient patient;
	
	/** Custom list adapters for vitals lists */
	VitalsAdapter tempAdapter;
	VitalsAdapter hrAdapter;
	VitalsAdapter bpAdapter;
	
	/** ListViews for vitals lists */
	ListView tempListView;
	ListView hrListView;
	ListView bpListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit);
		
		this.visit = null;
		
		// Get current visit to display
		try {
			this.visit = CurrentVisit.get();
		} catch (CurrentVisitNotSetException e) {
			System.out.println("No patient to display.");
			e.printStackTrace();
			finish();
		}
		
		this.patient = null;
		String patientName;
		
		// Gets the current patient to display
		try {
			this.patient = CurrentPatient.get();
			patientName = patient.getName().toString();
		} catch (CurrentPatientNotSetException e) {
			System.out.println("No patient to display.");
			e.printStackTrace();
			patientName = "[Patient Name]";
		}
		
		// Sets the activity title to the patient's name
		setTitle(patientName + " (Visit View)");
		
		// Set text for visit date
		TextView arrivalTimeView = (TextView) findViewById(R.id.visitDate);
		String headerText = "Visit on " + visit.getArrivalTime().toString() + 
				" for " + patientName;
		arrivalTimeView.setText(headerText);
		
		// Finds the ListViews for displaying patient visits
		this.tempListView = (ListView) findViewById(R.id.tempListView);
		this.hrListView = (ListView) findViewById(R.id.heartrateListView);
		this.bpListView = (ListView) findViewById(R.id.bpListView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visit, menu);
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
	
	protected void onResume() {
		super.onResume();
		// Ensures that the patient has vital signs to load, and loads them
		ArrayList<VitalSignReading> bpReadings;
		ArrayList<VitalSignReading> tempReadings;
		ArrayList<VitalSignReading> hrReadings;
		
		// Blood pressure
		if (this.visit.getBloodPressureRecord().isEmpty()) {
			bpReadings = 
					new ArrayList<VitalSignReading>();
		} else {
			bpReadings = new ArrayList<VitalSignReading>
						(this.visit.getBloodPressureRecord());
			// Reverse the list
			ArrayList<VitalSignReading> reversed = 
					new ArrayList<VitalSignReading>();
			for (int i = bpReadings.size() - 1 ; i >= 0; i--){
				reversed.add(bpReadings.get(i));
			}
			bpReadings = reversed;
		}
		
		// Temperature
		if (this.visit.getTemperatureRecord().isEmpty()) {
			tempReadings = 
					new ArrayList<VitalSignReading>();
		} else {
			tempReadings = new ArrayList<VitalSignReading>
						(this.visit.getTemperatureRecord());
			// Reverse the list
			ArrayList<VitalSignReading> reversed = 
					new ArrayList<VitalSignReading>();
			for (int i = tempReadings.size() - 1 ; i >= 0; i--){
				reversed.add(tempReadings.get(i));
			}
			tempReadings = reversed;
		}
		
		// Heart Rate
		if (this.visit.getHeartRateRecord().isEmpty()) {
			hrReadings = 
					new ArrayList<VitalSignReading>();
		} else {
			hrReadings = new ArrayList<VitalSignReading>
						(this.visit.getHeartRateRecord());
			// Reverse the list
			ArrayList<VitalSignReading> reversed = 
					new ArrayList<VitalSignReading>();
			for (int i = hrReadings.size() - 1 ; i >= 0; i--){
				reversed.add(hrReadings.get(i));
			}
			hrReadings = reversed;
		}
		
		// Attempts to populate the list of visits
		populateVitalsLists(hrReadings, tempReadings, bpReadings);
	}
	
	/**
	 * Populates the listViews for vital signs readings from this visit.
	 * @param hrReadings
	 * @param tempReadings
	 * @param bpReadings
	 */
	private void populateVitalsLists(ArrayList<VitalSignReading> hrReadings,
			ArrayList<VitalSignReading> tempReadings,
			ArrayList<VitalSignReading> bpReadings) {
		 this.hrAdapter = new VitalsAdapter(this, hrReadings);
		 this.tempAdapter = new VitalsAdapter(this, tempReadings);
		 this.bpAdapter = new VitalsAdapter(this, bpReadings);
		 
	        
        // Assign the VitalsAdapters to the ListViews for each vitals list
        this.hrListView.setAdapter(this.hrAdapter);
        this.bpListView.setAdapter(this.bpAdapter);
        this.tempListView.setAdapter(this.tempAdapter);
		
	}

	/**
	 * Starts the activity for adding vital signs.
	 * @param view The View in which this method was invoked. 
	 */
	public void goToAddVital(View view){
		Intent i = new Intent(this, AddVitalActivity.class);
		this.startActivity(i);
	}
	
	/**
	 * Starts the activity for viewing vital signs.
	 * @param view The View in which this method was invoked.
	 */
	public void goToMakePatientSeen(View view){
		Intent i = new Intent(this, MarkPatientSeen.class);
		this.startActivity(i);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_visit,
					container, false);
			return rootView;
		}
	}
	
}
