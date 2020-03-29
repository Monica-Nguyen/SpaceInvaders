//inheritance with StreamingMedia
public class Song  extends StreamingMedia {
	
	private int numOfLikes = 0;
	
	public Song(String aTitle, int aLength)
	{
		super(aTitle, aLength);
		
	}
	public Song(Song toCopy)
	{
		super(toCopy);
		numOfLikes = toCopy.numOfLikes;
	}
	public void addLikes(int amount)
	{
		if (amount > 0)
			this.numOfLikes = amount + getNumOfLikes();
	}
	//overrides the rating in the parent class 
	public int getRating()
	{
		int rating;
		if (numOfLikes >= 5000)
		{
			rating = 9;
		}
		else if (numOfLikes >= 500)
		{
			rating = 7;
		}
		else if (numOfLikes >= 50)
		{
			rating = 5;
		}
		else if (numOfLikes >= 10)
		{
			rating = 3;
		}
		else 
			rating = 1;
		
		return rating;
		
	}
	public int getNumOfLikes()
	{
		return numOfLikes;
	}
	
	public String toString()
	{
		return super.toString() + " Likes: " + numOfLikes;
		
	}
}
