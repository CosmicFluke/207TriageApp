package com.group0931.triagephase2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

/**
 * Utility class for load/save operations.  
 * Generally called by an instance the Manager class.<br>
 * Cannot be instantiated.
 * @author Asher MindenWebb, Christina Chung, Angel You.
 *
 */
public class FileManager {
	
	/** Folder name for data storage. */
	public static final String DEFAULTDATAFOLDER = "data";
	
	/** File name for master data file. */
	public static final String DEFAULTMASTERFILENAME = "master_data.ser";
	
	/** NOT IMPLEMENTED IN PHASE 2. */
	public static final String DEFAULTPATIENTSFOLDER = "patients";
	
	/** Filename for the Phase 2-specific input data. */
	public static final String PHASE2TEXTFILENAME = "patient_records.txt";
	public static final String FILEDIR = null;
	
	/** Private empty constructor; FileManager cannot be instantiated. */
	private FileManager(){
	}
	
	/**
	 * **PHASE 2 ONLY** <br>
	 * Loads master list from text file (non-serialized).
	 * @param root The {@code Manager} object where patient data is rooted.
	 * @param activity The {@code Context} from which is method is called.
	 * @throws IOException  When the text file storing patient data cannot be found.
	 * @throws InvalidTimeStampException If a patient's birthdate is not valid.
	 * @throws InvalidIDException When a given health card number
	 * in the text file is invalid.
	 */
	public static void loadDataFromText(Manager root, Context activity) 
		throws IOException, InvalidTimeStampException {
		
		// the text file is kept in the assets folder; access it via AssetManager object
		AssetManager am = activity.getAssets();
		Scanner masterFile = new Scanner(am.open(PHASE2TEXTFILENAME));
		String line;
		
		// Create Patients objects from each line in the text file
		while (masterFile.hasNextLine()) {
			line = masterFile.nextLine();
			Patient newPatient = null;
			try {
				newPatient = createPatientFromText(line);
			} catch (InvalidIDException e1) {
				e1.printStackTrace();
				continue;
			}
			try {
				root.addPatient(newPatient);
			} catch (DuplicateIDException e) {
				System.out.println("File error: contains duplicate health numbers." +
						" Could not add patient:");
				System.out.println(newPatient.getHealthCardNumber().toString());
				e.printStackTrace();
			} catch (NullPointerException e3) {
				System.out.println("Patient object was not constructed.");
				e3.printStackTrace();
			}
		}
		masterFile.close();
	}
		
	/** **PHASE 2 ONLY**<br>
	 * Creates a new {@code Patient} object based on an input line of text 
	 * (according to Phase 2 format).
	 * @throws InvalidIDException if a health card number from the
	 *  file is not valid.
	 * @throws InvalidTimeStampException If the given date is not valid.
	 */ 
	private static Patient createPatientFromText(String line) 
		throws InvalidIDException, InvalidTimeStampException {
		
		String[] components = line.split(",");
		HealthNumber healthNum = new HealthNumber(components[0]);
		PersonName name = new PersonName(components[1]);
		String[] dob = components[2].split("-");
		
		TimeStamp birthDate = TimeStamp.FactoryTimeStampWithoutTime(dob[0], dob[1], dob[2]);
		return new Patient(healthNum, name, birthDate);
	}
	
	/**
	 * Saves all patient information into a .ser file. 
	 * @param manager The {@code Manager} object, which stores all patient data.
	 * @param context TODO
	 */
	public static void writeDatabaseToSer(Manager manager, Context context){
		try {
			// Get internal storage location
			File rootDir = context.getFilesDir();
			System.out.println("Got files dir.");
			
			File dir = new File(rootDir.getAbsolutePath() + "/" + DEFAULTDATAFOLDER);
			dir.mkdirs();
			FileOutputStream fo =
			         new FileOutputStream(new File(dir, DEFAULTMASTERFILENAME));
			ObjectOutputStream out = new ObjectOutputStream(fo);
	        out.writeObject(manager.getPatientsForSerialization());
	        out.close();
	        fo.close();
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Reads all patient information into the application. 
	 * @param manager The {@code Manager}, which stores all patient data. 
	 * @param activity The {@code Context} from which the method is called.
	 * @throws InvalidTimeStampException If a patient's birthdate is a not a
	 * valid {@code TimeStamp}.
	 */
	public static void loadDatabaseFromSer(Manager manager, Context context) 
		throws InvalidTimeStampException {
		
		try{
			// Get internal storage location
			File rootDir = context.getFilesDir();
			
		 	File dir = new File(rootDir.getAbsolutePath() + "/" +
			DEFAULTDATAFOLDER + "/" + DEFAULTMASTERFILENAME);
	        FileInputStream fi = new FileInputStream(dir);
	        ObjectInputStream in = new ObjectInputStream(fi);
	        Object object = in.readObject();
	        if (object instanceof HashMap){
	        	manager.setPatientsFromDeserialization((HashMap<HealthNumber, Patient>) object);
	        } else {
	        	in.close();
	        	throw new ClassNotFoundException("Deserialized object was not"
	        			+ " an instance of HashMap");
	        }
	        in.close();
	        fi.close();
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    	try {
				loadDataFromText(manager, context);
			} catch (IOException e1) {
				System.out.println("Text file could not be loaded.");
				e1.printStackTrace();
			}
	    } catch(IOException e) {
	    	e.printStackTrace();
	    } catch(ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * **NOT IMPLEMENETED IN PHASE 2** <br>
	 * Loads a patient's detail (visits) into the given Manager object.
	 * @param root Manager object where patient data is rooted.
	 * @param healthNum 
	 */
	public static void loadPatientVisits(Manager root, HealthNumber healthNum){
		// TODO: Not yet implemented
	}
	
	/**
	 * **NOT IMPLEMENTED IN PHASE 2**<br>
	 * Saves a patient's data to a file.
	 * @param patient
	 */
	public static void savePatientToFile(Patient patient){
		// TODO: Not yet implemented
	}

}
