package com.group0931.triagephase2;

import android.util.Log;

/**
 * The current Patient of interest.
 * @author Christina Chung, Asher MindenWebb, Angel You.
 *
 */
public class CurrentPatient {

	/** The singleton instance. */
	private static CurrentPatient hcn = null;
	
	/** The patient stored by the singleton instance. */	
	private Patient patient = null;
	
	/**
	 * Constructs a CurrentPatient with health card number from healthNum.
	 * @param healthNum The health card number of the current patient.
	 * @throws RecordNotFoundException When the record of this patient is
	 * not found.
	 */
	private CurrentPatient(HealthNumber healthNum, Manager root) 
			throws RecordNotFoundException{
		setPatient(healthNum, root);
	}
	
	/**
	 * Sets the current patient.
	 * @param healthNum The health card number of the current patient.
	 * @throws RecordNotFoundException When the record of this patient is
	 * not found.
	 */
	public static void set(HealthNumber healthNum, Manager root) 
			throws RecordNotFoundException {
		if (hcn == null){
			hcn = new CurrentPatient(healthNum, root);
		}
		else {
			hcn.setPatient(healthNum, root);
		}
	}
	
	/**
	 * Returns the current patient.
	 * @return The current patient.
	 * @throws CurrentPatientNotSetException When the current patient is
	 * not set.
	 */
	public static Patient get() throws CurrentPatientNotSetException {
		if (hcn.patient != null) {
			return hcn.patient;
		} else {
			throw new CurrentPatientNotSetException();
		}
	}
	
	/**
	 * Sets the current patient.
	 * @param healthNum The health card number of the current patient.
	 * @throws RecordNotFoundException When the record of this patient is
	 * not found.
	 */
	private void setPatient(HealthNumber healthNum, Manager root) 
			throws RecordNotFoundException{
		Patient patient = root.getPatientRecord(healthNum);
		if (patient == null){
			throw new RecordNotFoundException();
		} else {
			this.patient = patient;
		}
	}
	
}
