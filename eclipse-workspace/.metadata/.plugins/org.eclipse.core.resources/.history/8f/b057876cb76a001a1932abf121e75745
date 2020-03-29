//Make abstract
public abstract class StreamingMedia {
	private String title;
	private int length = 10;
	
	public StreamingMedia(String aTitle, int aLength)
	{
		this.title = aTitle.toUpperCase();
		if (aLength > 0)
		{
			this.length = aLength;
		}
	}
	public StreamingMedia(StreamingMedia toCopy)
	{
		title = toCopy.title;
		length = toCopy.length;
	}
	public String getTitle()
	{
		return title;
		
	}
	protected void setTitle(String aTitle)
	{
		this.title = aTitle.toUpperCase();
	}
	public int getLength()
	{
		return length;
		
	}
	protected void setLength(int aLength)
	{
		if (aLength > 0)
		{
			this.length = aLength;
		}
	}
	public char getCategory()
	{
		char category;
		if (getRating() >= 9)
		{
			category = 'A';
		}
		else if (getRating() >= 7)
		{
			category = 'B';
		}
		else if (getRating() >= 5)
		{
			category = 'C';
		}
		else if (getRating() >= 3)
		{
			category = 'D';
		}
		else 
			category = 'F';
		return category;
		
	}
	public abstract int getRating();
	
	public String toString()
	{
		return ("Title: " + title + " Length: " + length);
		
	}
	
}
