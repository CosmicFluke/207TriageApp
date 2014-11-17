package com.group0931.triagephase2.deprecated;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import com.group0931.triagephase2.BloodPressureReading;
import com.group0931.triagephase2.HealthNumber;
import com.group0931.triagephase2.HeartRateReading;
import com.group0931.triagephase2.Manager;
import com.group0931.triagephase2.Visitor.VisitsNotLoadedException;
import com.group0931.triagephase2.TemperatureReading;
import com.group0931.triagephase2.TimeStamp;

/**
 * A User of the triage application. 
 * @author Christina Chung
 *
 */
public abstract class User{
	
	private Manager manager;
	/**
	 * Creates a new User. 
	 */	
	public User() {
	}
	
	/**
	 * Adds a new Visit to a patient's record.
	 * @param healthCardNumber A health card number
	 * @param dateOfArrival A date of arrival
	 */
	public void addVisit (HealthNumber healthCardNumber, Date dateOfArrival) {
		
	}
	
	/**
	 * Adds the date seen by doctor to a patient's record.
	 * @param healthCardNumber A health card number
	 * @param dateSeen A date seen by doctor
	 */
	public void addSeenDate (HealthNumber healthCardNumber, Date dateSeen) {
		
	}
	
    /**
     * Adds a temperature entry to a patient's record.
     * @param healthCardNumber The health card number of the patient
     * @param temp The temperature
     * @param data The date and time the reading was record
     * @param time Recorded time
     * @throws VisitsNotLoadedException 
     */
	public void addTemperatureEntry (HealthNumber healthCardNumber, TemperatureReading temp, TimeStamp time) throws com.group0931.triagephase2.Visitor.VisitsNotLoadedException {
		manager.getPatientRecord(healthCardNumber).getLastVisit().addTemperatureReading(temp);
	}
	
	/**
     * Adds a heart rate entry to a patient's record.
     * @param healthCardNumber The health card number of the patient
     * @param rate The heart rate
     * @param date The date and time the reading recorded
     * @param time Recorded time
	 * @throws VisitsNotLoadedException 
     */
	public void addHeartRateReading (HealthNumber healthCardNumber, HeartRateReading rate, TimeStamp time) throws VisitsNotLoadedException {
		manager.getPatientRecord(healthCardNumber).getLastVisit().addHeartRateReading(rate); 
	}
	
	/**
     * Adds a new blood pressure entry to a patient's record.
     * @param healthCardNumber The health card number of the patient
     * @param systolic The systolic blood pressure
     * @param diastoli The diastolic blood pressure
     * @param date The date and time this temperature was recorded
     * @param Recorded time
	 * @throws VisitsNotLoadedException 
     */
	public void addBloodPressureEntry (HealthNumber healthCardNumber, BloodPressureReading bpReading, TimeStamp time) throws VisitsNotLoadedException {
		manager.getPatientRecord(healthCardNumber).getLastVisit().addBloodPressureReading(bpReading); 
	}
    
	

}
