package ricepudding.auth;

/**
 * Used to store a String that matches desired specifications
 * @author RyanO 
 * @author Jordan Carboy
 * @version 0.5
 * @since 3/15/2022
 */
public class Password {
	//What are our requirements??
	/**
	 * String value of the Password
	 */
	private String passcode; //	was originally also password but I thought that may be confusing idk -Ryan
	/**
	 * Minimum character length <var>passcode</var> may be
	 */
	private final static int MIN_LENGTH = 8;	//inclusive
	/**
	 * Maximum character length <var>passcode</var> may be
	 */
	private final static int MAX_LENGTH = 20;	//inclusive
	
	/**
	 * Password Constructor
	 * <p>Initializes <var>passcode</var> to null </p>
	 */
	public Password() {
		passcode = null;
	}
	
	/**
	 * Password Constructor
	 * <p> Initializes <var>passcode</var> to a specified String (<var>str</var>) 
	 * if it follows all requirements, else <var>passcode</var> is set to null</p>
	 * @param str Potential String value of <var>passcode</var>
	 */
	public Password(String str) {	//Throw check?? -Ryan
		if(checkPassword(str)) passcode = str;
		else passcode = null;
	}
	
	/**
	 * Checks if String <var>str</var> follows given requirements
	 * <p>Checks if <var>str</var> is of the right length and contains at least 1 upper case letter, lower case letter, digit, and special character
	 * @param str String being scanned for compliancy
	 * @return true if <var>str</var> is acceptable, otherwise false
	 */
	public static boolean checkPassword(String str) {
		if(isRightLength(str) && containsUpperCase(str) && containsLowerCase(str) && containsNumber(str) 
				&& containsSpecialCharacter(str)) return true;
		else return false;
	}
	
	//Should these next few methods be private since they are only intended for checkPassword()? -Ryan
	/**
	 * Checks if the length of String <var>str</str> is within minimum and maximum character lengths
	 * @param str String of text being compared
	 * @return true if length of <var>str</var> falls within range, otherwise false
	 */
	public static boolean isRightLength(String str) {
		int length = str.length();
		if (length >= MIN_LENGTH && length <= MAX_LENGTH) return true;
		else return false;
	}
	
	/**
	 * Checks if the String <var>str</str> contains at least 1 upper case letter
	 * @param str String of text being scanned
	 * @return true if <var>str</var> contains at least 1 upper case letter, otherwise false
	 */
	public static boolean containsUpperCase(String str) {
		for(int index = 0; index < str.length(); index++) {
			if(Character.isUpperCase(str.charAt(index))) return true;
		} return false;
	}
	
	/**
	 * Checks if the String <var>str</str> contains at least 1 lower case letter
	 * @param str String of text being scanned
	 * @return true if <var>str</var> contains at least 1 lower case letter, otherwise false
	 */
	public static boolean containsLowerCase(String str) {
		for(int index = 0; index < str.length(); index++) {
			if(Character.isLowerCase(str.charAt(index))) return true;
		} return false;
	}
	
	/**
	 * Checks if the String <var>str</str> contains at least 1 digit
	 * @param str String of text being scanned
	 * @return true if <var>str</var> contains at least 1 digit, otherwise false
	 */
	public static boolean containsNumber(String str) {
		for(int index = 0; index < str.length(); index++) {
			if(Character.isDigit(str.charAt(index))) return true;
		} return false;
	}
	
	/**
	 * Checks if the String <var>str</str> contains at least 1 character that is neither a letter nor a digit
	 * @param str String of text being scanned
	 * @return true if <var>str</var> contains at least 1 character that is neither a letter nor a digit , otherwise false
	 */
	public static boolean containsSpecialCharacter(String str) {
		for(int index = 0; index < str.length(); index++) {
			if(!Character.isLetter(str.charAt(index)) && !Character.isDigit(str.charAt(index))) return true;
		} return false;
	}
	
	/**
	 * Returns the <var>passcode</var> of the Password object <!--Weak comment? -Ryan -->
	 * @return <var>passcode</var> of the Password Object
	 */
	public String getPassword() {
		return passcode;
	}
	
	/**
	 * Sets the <var>passcode</var> of the Password object <!--Weak comment? -Ryan -->
	 */
	public void setPassword(String str) {
		if(checkPassword(str)) passcode = str;
	}
	/**
	 * Returns the minimum amount of characters needed for a valid passcode
	 * @return <var>MIN_LENGTH</var> of the <var>passcode</var> for the Password Object
	 */
	public static int getMinLength() {	//	Should it be getMIN_LENGTH() ? -Ryan
		return MIN_LENGTH;
	}
	/**
	 * Returns the maximum amount of characters allowed for a valid passcode
	 * @return <var>MAX_LENGTH</var> of the <var>passcode</var> for the Password Object
	 */
	public static int getMaxLength() {	//	Should it be getMAX_LENGTH() ? -Ryan
		return MAX_LENGTH;
	}

}
