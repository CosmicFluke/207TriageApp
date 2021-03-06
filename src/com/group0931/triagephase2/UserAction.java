package com.group0931.triagephase2;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * **NOT IMPLEMENTED IN PHASE 2.**<br>
 * Represents a User Profile<br>
 * - Tracks user actions
 * - 
 * @author Christina Chung, Asher MindenWebb, Angel You.
 */
public class UserAction implements Serializable, Comparable<Object> {

	/** Serialization */
	private static final long serialVersionUID = 8096954146656426273L;
	
	/** (Placeholder) will be an Enum, probably?
	 * TODO: do something with this!  */
	private String actionType;
	
	/** Time of the action */
	private TimeStamp timeStamp;
	
	/** Which object the action was performed on */
	private Object associatedObject;
	
	/**
	 * Constructs a {@code UserAction} object
	 * @param action
	 * @param assocObj
	 */
	public UserAction(String action, Object assocObj /*[, User currentUser]*/) {
		this.actionType = action;
		//this.user = currentUser;
		this.associatedObject = assocObj;
		this.timeStamp = new TimeStamp();
	}
	
	/**
	 * UserActions are compared using their time stamps ({@code TimeStamp} 
	 * objects)<br>
	 * Can be compared to other {@code UserAction} objects, 
	 * {@code TimeStamp} objects, {@code Calendar} objects, or {@code Date} 
	 * objects only
	 * @param compare : the {@code Calendar} or {@code TimeStamp} for comparison
	 */
	public int compareTo(Object compare){
		// Compare with Calendar
		if (compare instanceof Calendar){
			return this.timeStamp.getDate().compareTo((Calendar) compare);
		}
		// Compare with Date
		else if (compare instanceof Date){
			return ((Date) compare).compareTo(this.timeStamp.getDate().getTime());
		}
		// Compare with UserAction
		else if (compare instanceof UserAction) {
			return ((UserAction) compare).compareTo(this.timeStamp);
		}
		else if (compare instanceof TimeStamp) {
			return ((TimeStamp) compare).compareTo(this.timeStamp);
		}
		else throw new ClassCastException();
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(timeStamp.toString() + ", ");
		output.append(actionType.toString() + ", ");
		output.append(associatedObject.toString());
		return new String(output);
	}

}
