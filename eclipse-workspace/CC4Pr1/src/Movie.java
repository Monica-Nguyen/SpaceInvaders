//inheritance from streamingMedia 
public class Movie extends StreamingMedia {
	private int rating;
	
	//Constructor 
	public Movie(String aTitle, int aLength, int aRating)
	{
		super(aTitle, aLength);
		if(aRating >= 0 && aRating <= 10)
		{
			this.rating = aRating;
		}
	}
	//Copy constructor 
	public Movie(Movie toCopy)
	{
		super(toCopy);
		rating = toCopy.rating;
	}
	public void setRating(int aRating)
	{
		if(aRating >= 0 && aRating <= 10)
		{
			this.rating = aRating;
		}
	}
	public int getRating()
	{
		return rating;
		
	}
	@Override
	public String toString()
	{
		//calling the super calls the parent class and also brings in the title and length from their too 
		return super.toString() + " Rating: " + rating;
	}
	
}
