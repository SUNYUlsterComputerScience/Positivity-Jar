package ricepudding.auth;

import java.time.LocalDateTime;

public class Formatter
{
	public static String getString(LocalDateTime ldt)
	{
		return String.format("%s, %s %d, %d, at %d:%d", 
				ldt.getDayOfWeek().toString().substring(0, 1) + 
				ldt.getDayOfWeek().toString().substring(1).toLowerCase(),
				ldt.getMonth().toString().substring(0, 1) + 
				ldt.getMonth().toString().substring(1).toLowerCase(),
				ldt.getDayOfMonth(), ldt.getYear(), ldt.getHour(),
				ldt.getMinute());
	}
	
	public static String getString(User u)
	{
		return String.format("%s (%s)", u.getUsername(),
				u.getEmailAddress());
	}
	
	public static String getString(Note n)
	{
		return String.format("%s, on %s%n%s",
				Formatter.getString(n.getAuthor()),
				Formatter.getString(n.getTimeEntered()),
				n.getContent().strip());
	}
}