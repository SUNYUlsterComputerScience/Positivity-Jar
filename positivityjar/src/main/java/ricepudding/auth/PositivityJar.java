package ricepudding.auth;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * A Java class to operate as an electronic Positivity Jar that can perform
 * the functions specified in the Team Rice Pudding mission document, including
 * give a jar report, or summary in either .txt format or .html format.
 * 
 * Is dependent on:
 * 	java.awt.Image
 * 	java.io.File
 * 	java.io.FileWriter
 * 	java.io.IOException
 * 	java.io.Serializable
 * 	java.time.LocalDateTime
 * 	java.util.LinkedList
 * 	Note
 * 	User
 * 	Formatter
 * 
 * @author Colin Stewart
 */
public class PositivityJar implements Serializable
{
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Note> entries;
	private User master;
	private LocalDateTime creationDate;
	private LocalDateTime expirationDate;
	private LinkedList<User> contributors;
	private Image icon;
	private String label;
	/**
	 * Constructor for the PositivityJar class, taking only a master
	 * and an expiration date.
	 * 
	 * @param master The master of the PositivityJar.
	 * @param expirationDate The date at which the PositivityJar will expire.
	 */
	public PositivityJar(User master, LocalDateTime expirationDate)
	{
		this.setMaster(master);
		this.creationDate = LocalDateTime.now();
		this.expirationDate = expirationDate;
		this.contributors = new LinkedList<User>();
		contributors.add(master);
		this.entries = new LinkedList<Note>();
		this.icon = null;
		this.label = new String();
		new String();
	}
	
	/**
	 * Constructor for the PositivityJar class, taking a master, label,
	 * and expirationDate as inputs.
	 * 
	 * @param master The master of the PositivityJar.
	 * @param expirationDate The date at which the PositivityJar will expire.
	 * @param label The label of the PositivityJar.
	 */
	public PositivityJar(User master, LocalDateTime expirationDate, String label)
	{
		this.setMaster(master);
		this.creationDate = LocalDateTime.now();
		this.expirationDate = expirationDate;
		this.contributors = new LinkedList<User>();
		contributors.add(master);
		this.entries = new LinkedList<Note>();
		this.icon = null;
		this.label = label;
		new String();
	}

	/**
	 * A standard getter for the entry notes to the jar.
	 * 
	 * @return The Note entries to the PositivityJar.
	 */
	public LinkedList<Note> getEntries() { return this.entries; }
	
	/**
	 * A method to add a Note to the PositivityJar.
	 * 
	 * @param entry The new entry Note to add.
	 */
	public void addNote(Note entry) { this.entries.add(entry); }
	
	/**
	 * A standard getter for the contributors to the PositivityJar.
	 * 
	 * @return The Users who contribute to the PositivityJar.
	 */
	public LinkedList<User> getContributors() { return this.contributors; }
	
	/**
	 * A method that adds a User to the list of contributors to the PositivityJar.
	 * 
	 * @param contributor The User to add.
	 */
	public void addContributor(User contributor) { this.contributors.add(contributor); }
	
	/**
	 * A method that removes a User from the list of contributors.
	 * 
	 * @param contributor The User to remove.
	 */
	public void removeContributor(User contributor) { this.contributors.remove(contributor); }
	
	/**
	 * A standard getter for the Image icon.
	 * 
	 * @return The Image icon file for the PositivityJar.
	 */
	public Image getIcon() { return this.icon; }
	
	/**
	 * A standard setter for the Image icon.
	 * @param icon The Image icon file to set.
	 */
	public void setIcon(Image icon) { this.icon = icon; }
	
	/**
	 * A method to return whether or not the PositivityJar has expired, based
	 * on the current date and time and the expirationDate date and time.
	 * 
	 * @return True for a jar that has expired, false for one that hasn't.
	 */
	public boolean isExpired()
	{
		LocalDateTime now = LocalDateTime.now();
		if (expirationDate.isBefore(now))
		{ return true; }
		return false;
	}
	
	/**
	 * Method to generate an, albeit unattractive, html summary, fit to post
	 * on a web page and store for further reference by a user. At the top of
	 * the page goes the label, or name, of the jar, next is a detailed list
	 * of contributors (master of jar is listed first), followed by the span
	 * of time during which the jar was "active", and completed by the list of
	 * positivities in chronological order.
	 * 
	 * This summary is stored in the root folder of the project, as summary.html.
	 */
	public void generateHTMLSummary()
	{
		File summaryFile; // the file
		FileWriter pen;   // the writer
		
		try
		{
			summaryFile = new File("summary.html"); // open a new file if there isn't one already
			summaryFile.setWritable(true);          // make the file writable
			pen = new FileWriter("summary.html");   // open a writer to write to the file
			
			                                        // write the html to the file
			pen.write(String.format("%s%n%s%n%s",
					String.format("%s%n%s%n%s%s%s%n%s%n",
							"<!DOCTYPE html>",
							"<html>",
							"<head>",
							"<title>", this.label, "</title>",
							"<body>"
							),
					String.format("%s%s%s%n%s%s%s%n%s%s%s%n%s%s%s%n",
							"<h1>", this.label, "</h1>",
							"<p>", this.contributorList(), "</p>",
							"<p>", this.timeList(), "</p>",
							"<p>", this.noteList(), "</p>"
							),
					String.format("%s%n%s",
							"</body>",
							"</html>"
							)));
			pen.close();
		}
		catch (IOException e)
		{
			System.err.println("An error occurred that shouldn't have. "
					+ "You should report this to Colin Stewart. "
					+ "Tell him that there was a file access error when producing the html summary.");
		}
	}
	
	/**
	 * Method to generate a basic summary in the form of a text file. Will
	 * produce a file, summary.txt in the root folder of the project, which
	 * will contain: The label of the jar, the contributors to the jar, the
	 * span of time during which the jar was "active, and a chronologically
	 * ordered list of positivities placed into the jar.
	 */
	public void generateTxtSummary()
	{
		File summaryFile;
		FileWriter pen;

		try
		{
			summaryFile = new File("summary.txt");
			summaryFile.setWritable(true);
			pen = new FileWriter("summary.txt");

			pen.write(String.format(
					"%s%n%n%s%n%n%s%n%n%s",
					this.label.toUpperCase(),
					this.contributorList(),
					String.format(
							"%s - %s",
							Formatter.getString(creationDate),
							Formatter.getString(expirationDate)),
					this.noteList()
					));
			pen.close();
		}
		catch (IOException e)
		{
			System.err.println("An error occurred that shouldn't have. "
					+ "You should report this to Colin Stewart. "
					+ "Tell him that there was a file access error when producing the txt summary.");
		}
	}

	private String contributorList()
	{
		if (contributors.size() == 0)
			return "";
		String output = new String();
		for (User contributor : contributors)
		{
			output = output.concat(String.format(
					"%s (%s), ",
					contributor.getUsername(),
					contributor.getEmailAddress()));
		}
		output = output.substring(0, output.length() - 2);
		return output;
	}
	
	private String timeList()
	{
		return String.format("%s%s%s",
				Formatter.getString(creationDate),
				" - ",
				Formatter.getString(expirationDate));
	}

	private String noteList()
	{
		String output = new String();
		for (Note entry : entries)
		{
			output = output.concat(Formatter.getString(entry) + "\n\n");
		}
		output = output.substring(0, output.length() - 1);
		return output;
	}

	/**
	 * A standard getter to return the master.
	 * 
	 * @return The master of this PositivityJar.
	 */
	public User getMaster()
	{
		return master;
	}

	/**
	 * A standard setter to set the master.
	 * 
	 * @param master The new master to set.
	 */
	public void setMaster(User master)
	{
		this.master = master;
	}
}