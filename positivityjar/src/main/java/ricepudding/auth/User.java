package positivityjar;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class User
{
	private String password;
	private String username;
	private String emailAddress;
	private LocalDateTime whenCreated;
	private LinkedList<PositivityJar> masterJars;
	private LinkedList<PositivityJar> contributorJars;
	
	public User(String emailAddress, String password, String username)
	{
		this.emailAddress = emailAddress;
		this.password = password;
		this.username = username;
		this.whenCreated = LocalDateTime.now();
		this.masterJars = new LinkedList<PositivityJar>();
		this.contributorJars = new LinkedList<PositivityJar>();
	}

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getEmailAddress() { return emailAddress; }

	public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

	public LocalDateTime getWhenCreated() { return whenCreated; }

	public void setWhenCreated(LocalDateTime whenCreated) { this.whenCreated = whenCreated; }
}