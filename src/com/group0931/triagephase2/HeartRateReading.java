package com.group0931.triagephase2;

/**
 * A heart rate reading of a {@code PatientVisit}.
 * @author Angel You, Christina Chung, Asher MindenWebb.
 *
 */
public class HeartRateReading extends VitalSignReading 
implements Comparable<HeartRateReading>{
	
	/** The heart rate of this {@code HeartRateReading}. */
	private int heartRate;
	/** The date and time of this {@code HeartRateReading}.*/
	private TimeStamp readingTime;
	/** The urgency point of this {@code HeartRateReading}. */
	private int urgency;
	
	/**
	 * Constructs a {@code HeartRateReading} with heart rate from heartRate 
	 * and date and time from date.
	 * @param The heart rate of this {@code HeartRateReading}.
	 * @param date The date and time this {@code HeartRateReading}.
	 */
	public HeartRateReading(int heartRate, TimeStamp date) {
			this.heartRate = heartRate;
			this.readingTime = date;
			this.urgency = calculateUrgencyPoint(); 
	}
	
	/**
	 * Returns a {@code HeartRateReading} object iff the given Strings of the 
	 * heart rate and date is valid.
	 * @param heartRate The heart rate of this {@code HeartRateReading}.
	 * @param date The date and time of this {@code HeartRateReading}.
	 * @return The HeartRateReading object.
	 * @throws InvalidVitalSignException If the given parameters are invalid.
	 */
	public static HeartRateReading FactoryHeartRateReading(String heartRate, 
			TimeStamp date) throws InvalidVitalSignException{
		if (HeartRateReading.isValid(heartRate))
			return new HeartRateReading(Integer.parseInt(heartRate), date);
		else 
			throw new InvalidVitalSignException();	
	}
	
	/**
	 * Returns whether the given string is valid.
	 * @param s The String to be checked.
	 * @return True iff s is valid, false otherwise.
	 */
	public static boolean isValid(String s){
		try{
			Integer.parseInt(s);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the heart rate of this {@code HeartRateReading}.
	 * @return The heart rate of this {@code HeartRateReading}.
	 */
	public int getHeartRate(){
		return this.heartRate;
	}

	@Override
	public TimeStamp getReadingTime(){
		return this.readingTime;
	}

	@Override
	public int calculateUrgencyPoint(){
		if (this.heartRate >= 100 || this.heartRate <= 50)
			return 1;
		else
			return 0;
	}
	
	@Override
	public int getUrgency() {
		return this.urgency;
	}

	@Override
	public int compareTo(HeartRateReading another) {
		TimeStamp otherTime = another.getReadingTime();
		return this.readingTime.compareTo(otherTime);
	}

	/**
	 * Returns the String representation of this HeartRateReading in the
	 * format of "yyyy-MM-dd HH:mm  Heart Rate: bpm".
	 */
	@Override
	public String toString() {
		String time = this.getReadingTime().toString();
		String heartRate = String.valueOf(this.getHeartRate());
		return time + "  Heart Rate: " + heartRate;
	}
	
	public String getReadingAsString() {
		return String.valueOf(this.heartRate);
	}

}