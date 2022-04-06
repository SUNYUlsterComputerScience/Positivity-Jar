package positivityjar;

import java.time.LocalDateTime;

public class Note
{
	private String content;
	private LocalDateTime timeEntered;
	private User author;
	
	public Note(String content, User author)
	{
		this.setAuthor(author);
		this.setContent(content);
		this.setTimeEntered(LocalDateTime.now());
	}
	
	public String getContent(){return content;}
	public void setContent(String content){this.content = content;}
	public LocalDateTime getTimeEntered(){return timeEntered;}
	public void setTimeEntered(LocalDateTime localDateTime){this.timeEntered = localDateTime;}
	public User getAuthor(){return author;}
	public void setAuthor(User author){this.author = author;}
}
