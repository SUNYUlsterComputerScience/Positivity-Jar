package jar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.SecurityException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.FormatterClosedException;
import java.util.Scanner;

/**
 * Test class for Entry Class which creates multiple Entry objects and stores one onto a separate file
 * 
 * @author RyanO
 * @version 1.0
 * @since 4/06/2022
 *
 */
public class TempJar {

	private static final String FILE = "entrylist.txt";	//A substitute for the database
	
	private static ObjectInputStream inputStream;	//used to modify the file
	private static ObjectOutputStream outputStream;	//used to read the file
	
	/**
	 * Creates two Entry objects, one null the other with set text, then calls on methods to create and store a user-made Entry onto a separate file
	 * 
	 * @param args N/A
	 */
	public static void main(String[] args) {
		Entry p1 = new Entry("Have you ever danced with the devil in the pale moonlight?");
		Entry p2 = new Entry();
		System.out.println(p1);
		System.out.println(p2);

		//Example of a post being created by the user
		openFile();
		addEntry();
		closeFile();
		//Said example then being read/printed out by the program
		readFile();
		closeFile();
	}
	
	/**
	 * Extracts the Entry object stored in the file
	 */
	private static void readFile() {
		try {
			while(true) {
				Entry record = (Entry) inputStream.readObject();
				System.out.printf("%-10s @%-12s", record.getEntryText(), record.getEntryDate());
			} 
		} catch (ClassNotFoundException | IOException error) {}
	}
	
	
	/**
	 * Opens the file
	 */
	private static void openFile() {
		try {
			inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(FILE)));
			outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(FILE)));
		}  catch (FileNotFoundException e) {
			System.out.println("Error opening file: " + FILE);
			System.exit(1);
		}catch (IOException IOException) {
			System.out.println("Error opening file: " + FILE);
			System.exit(1);
		} catch (SecurityException securityException) {
			System.out.println("Write permission denied");
			System.exit(1);
		}
	}
	
	/**
	 * Tasks the user to input a new text entry and stores the created Entry object onto a File
	 */
	private static void addEntry() {
		boolean continueLoop = true;
		while(continueLoop )
		try {
			Scanner in = new Scanner(System.in);
			String str = "";
			
			System.out.println("Type out a new post: ");
			
			str = in.nextLine(); 
			Entry post = new Entry(str);
			outputStream.writeObject(post);
			
			in.close();
			continueLoop = false;
			
		} catch (FormatterClosedException e) {
			System.out.println("Error writing to file: " + FILE);
			System.exit(1);
		} catch (IOException ioException) {
			System.err.println("Error writing to file. Terminating");
			break;
		}
	}
	
	/**
	 * Closes the file if it has been opened.
	 */
	private static void closeFile() {
	  if (outputStream!=null)
		try {
			outputStream.close();
		} catch (IOException ioException) {
			System.err.println("Error closing file");
		}
	}
	
}