package com.group0931.triagephase2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Top-level abstract class for managing Patient data that
 * provides access to Patient records, and has several tools for use by Users.
 * <br>- Patients can only be accessed by their HealthNumber.
 * <br>- Individual Patient objects will be returned only by 
 * the getPatient() method.
 * <br>- [NOT IMPLEMENTED] Implicit contract with all User subclasses: 
 * Users must call openSession method before any other actions.
 * 
 *  @author Christina Chung, Angel You, Asher Minden Webb.
 */
public class Manager implements Serializable {
	
	// NOT IMPLEMENTED: Current session/User Information
	// private User currentUser;
	// private boolean sessionOpen;
	
	/** Serialization */
	private static final long serialVersionUID = -1213204778196820798L;

	/** An Activity record of all user actions since last save. */
	private transient Set<UserAction> activityRecord;

	/** A HealthNumber-to-Patient map (master list). */
	private Map<HealthNumber, Patient> patients;
	
	/** A List of active patients who have not yet been seen by a doctor. */
	private ArrayList<HealthNumber> unseenPatients;
	
	private static Manager _singleton;
	
	/**
	 * Constructs a new Manager. 
	 */
	private Manager() {
		// Initialize instance variables
		this.patients = new HashMap<HealthNumber, Patient>();
		this.unseenPatients = new ArrayList<HealthNumber>();
		this.activityRecord = new TreeSet<UserAction>();
	}
	
	/**
	 * Returns the Manager singleton.
	 * @return The singleton Manager object.
	 */
	public static Manager getManager() {
		if (_singleton == null){
			_singleton = new Manager();
		}
		return _singleton;
	}
		
	/**
	 * Returns the set of all health card numbers for all patients. 
	 * @return The set of HealthNumbers for all patients. 
	 */
	public Set<HealthNumber> getAllPatients() {
		return patients.keySet();
	}

	/**
	 * Returns the set of all health card numbers for all unseen patients.
	 * @return The set of HealthNumbers for all active patients who have 
	 * not yet been seen by a doctor.
	 */
	public ArrayList<HealthNumber> getUnseenPatients() {
		return unseenPatients;
	}
	
	/**
	 * The primary method to access individual patient information with
	 * the patient's health card number.
	 * @param healthNum The patient's HealthNumber.
	 * @return The Patient object corresponding to the given HealthNumber.
	 */
	public Patient getPatientRecord(HealthNumber healthNum) {
		return this.patients.get(healthNum);
	}
	
	/**
	 * Adds a patient to the database.  
	 * <br>- Only accepts Patient objects, which must contain a valid 
	 * non-duplicate HealthNumber.
	 * <br>- Registers action in activityRecord.
	 * @param patient The patient to add to the map of all patients. 
	 */
	protected void addPatient (Patient patient) throws DuplicateIDException {
		HealthNumber healthNum = patient.getHealthCardNumber();
		
		// Checks to ensure there is not already a patient with the same 
		// HealthNum in the database
		if (this.patients.containsKey(healthNum)){
			throw new DuplicateIDException();
		}
		else {
			// Add patient to patients map
			this.patients.put(healthNum, patient);
		}
		
		// Add patient to unseenPatients if necessary
		if (patient.hasNotBeenSeen()){
			this.makePatientUnseen(healthNum);
		}
		this.activityRecord.add(new UserAction("addPatient", patient));
	}
	
	/**
	 * ** Not implemented in Phase 2.<br>
	 * Deletes a patient from the master record.<br>
	 * ** Cannot be undone.
	 * @param healthNum The patient's health card number. 
	 */
	public void removePatient(HealthNumber healthNum){
	}
	
	/**
	 * ** Not implemented in Phase 2.<br>
	 * Adds a patient to the list of unseen patients ({@code unseenPatients}). 
	 * @param patientNum The patient's health card number. 
	 */
	public void makePatientUnseen(HealthNumber patientNum){
	}
	
	/**
	 * ** Not implemented in Phase 2.<br>
	 * Removes a patient form the list of unseen patients ({@code unseenPatients}). 
	 * @param patientNum The Patient's health card number. 
	 */
	public void makePatientSeen(HealthNumber patientNum){
	}
	
	/**
	 * ** To be used for serialization only. **<br>
	 * Returns the map of all patients. 
	 * @return A copy of the patients map (HealthNumber to Patient).
	 */
	public Map<HealthNumber, Patient> getPatientsForSerialization(){
		return new HashMap<HealthNumber, Patient>(this.patients);
	}
	
	/**
	 * ** To be used for deserialization only. **<br>
	 * Sets the map of all patients to the deserialized map.
	 * @param fromSer A copy of the patients map (HealthNumber to Patient).
	 */
	public void setPatientsFromDeserialization(Map<HealthNumber, Patient> 
	fromSer){
		this.patients = fromSer;
	}
	
// ----------------------------------------------------------------------------
// -------       NOT YET IMPLEMENTED (below the line)               -----------
// ----------------------------------------------------------------------------

/*
	/**
	 * NOT IMPLEMENTED<br>
	 * Creates a new User
	 * @param userType
	 * @param newUserID
	 * @return
	 *

	public User newUser(String userType, String newUserID){
		return null; // Not yet implemented!
		// TODO: userType should be Enum
	}
	
	/**
	 * NOT IMPLEMENTED<br>
	 * Opens a new session, registers the user
	 * <br>- Implicit contract with User classes that this method will be called 
	 * before any other actions
	 * <br>- Cannot be called while a session is open; call closeSession 
	 * first to switch users.
	 * 
	public void openSession(){
	}
	
	/** NOT IMPLEMENTED<br>
	 * Closes the current session, archives the activity log 
	 *
	public void closeSession(){
	}
*/
	
}