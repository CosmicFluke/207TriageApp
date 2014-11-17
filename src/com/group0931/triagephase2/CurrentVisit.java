package com.group0931.triagephase2;

/**
 * The current visit of interest.
 * @author Asher MindenWebb, Angel You, Christina Chung.
 *
 */
public class CurrentVisit {

	/** The singleton instance. */
	private static CurrentVisit instance = null;
	
	/** The patient's visit stored by the singleton instance. */	
	private PatientVisit visit = null;
	
	/**
	 * Constructs a CurrentVisit with visit from visit. 
	 * @param visit The {@code PatientVisit} that is to be set as the current visit. 
	 */
	private CurrentVisit (PatientVisit visit) {
		setVisit(visit);
	}
	
	/**
	 * Sets the current visit.
	 * @param visit The {@code PatientVisit} that is to be set as the current visit. 
	 */
	public static void set(PatientVisit visit) {
		if (instance == null){
			instance = new CurrentVisit(visit);
		}else {
			instance.setVisit(visit);
		}
	}
	 
	/**
	 * Returns the current visit. 
	 * @return The current visit. 
	 * @throws CurrentVisitNotSetException When there is no current visit 
	 * (i.e. the visit attribute is set to null).
	 */
	public static PatientVisit get() throws CurrentVisitNotSetException {
		if (instance.visit != null) {
			return instance.visit;
		}else {
			throw new CurrentVisitNotSetException();
		}
	}
	
	/**
	 * Sets the current visit.
	 * @param visit The {@code PatientVisit} to be set as the current visit. 
	 */
	private void setVisit(PatientVisit visit) {
		this.visit = visit;
	}
	
}
