package jar;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Class which bundles given text information with a marked date & time of its creation
 * 
 * @author RyanO
 * @version 1.0 
 * @since 4/05/2022
 */
public class Entry implements Serializable {
	
	//Allows object to be stored in a file
	private static final long serialVersionUID = 2099408959896104308L;
	//The text data of the entry
	private String entryText;	//Considered removing "entry" from variable names, left them on to keep them clearly understood
	//The date, time, and time-zone of when the object is created
	private ZonedDateTime entryDate;	
	//The User account connected to the entry, tentative whether or not it is to be included
	//	private User account;	//would be set through fetching in DB
	
	/** Entry Constructor
	 * <p>Initializes Entry object to contain a <code>null</code> text entry, along with a marked date/time/time-zone</p> 
	 */
	Entry() {
		this(null);
	}
	/** 
	 * Entry Constructor
	 * 
	 * <p>Initializes Entry object to contain a defined text entry, along with a marked date/time/time-zone</p> 
	 */
	Entry(String text) {
		entryText = text;
		//Set user to what's fetched from database?
		entryDate = ZonedDateTime.now();
	}
	
	/** 
	 * Getter-method which returns the text saved onto the Entry object
	 * 
	 * @return <var>entryText</var> 
	 */
	public String getEntryText() {
		return entryText;
	}
	/** 
	 * Setter-method which changes <var>entryText</var> of the Entry object to a newly defined value
	 */
	public void setEntryText(String newText) {
		entryText = newText;
	}
	/** Getter-method which returns the text saved onto the Entry object
	 * 
	 * @return <var>entryDate</var> 
	 */
	public ZonedDateTime getEntryDate() {
		return entryDate;
	}
	/**
	 * Converts Entry to a String in a standard format
	 * <p>Returns a String representation of the Entry object
	 * in the format: <var>entryText</var> @<var>entryDate</var></p>
	 * 
	 * @return <code>String</code> representation of Entry in a standard format
	 */
	public String toString() {
		return entryText /*+ " -" + user*/ +" @" + entryDate;
	}
	
}
