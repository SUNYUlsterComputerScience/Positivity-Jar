package ricepudding.test;

import ricepudding.auth.Password;

import java.util.Scanner;

/**
 * Java Program used to create a Password Object to the proper specifications
 * @author RyanO
 * @author JordanC
 * @version 0.5 <!-- I think it does most of what it needs to -->
 */
public class PasswordTest {
	//	I guess I could also implement try-catches but that can wait I think -Ryan
	/**
	 * Asks user to create a password that follows specific requirements, then prints the created password 
	 * <p>with at least 1 lower case letter, 1 upper case letter, 1 digit, 1 special character and has a 
	 * length within a specified range of values </p>
	 * @param args N/A
	 */
	public static void main(String[] args) {
		boolean continueLoop = true;
		
		while (continueLoop) {
			Scanner in = new Scanner(System.in);
			
			System.out.println("A Password must be between " + Password.getMinLength() + "-" +
					Password.getMaxLength() + " characters long, and must contain at least 1 uppercase letter, " + 
					"1 lowercase letter, 1 number, and 1 special character\nPlease enter a password: ");
			Password pass = new Password(in.next());	// Test does not accept whitespace values (Password should though)
			
			if(pass.getPassword() != null) {	//	I think we should instead include try-catches in password and throw an error if password is invalid -Ryan
				System.out.print(pass.getPassword());
				in.close();
				continueLoop = false;
			} else System.out.println("Invalid Password Try Again");
		}
	}

}
