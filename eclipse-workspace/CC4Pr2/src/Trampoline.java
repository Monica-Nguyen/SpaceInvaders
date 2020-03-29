//inheritance with Bouncer
public class Trampoline extends Bouncer {
	private int weightOfJumper;
	
	public Trampoline(int weightOfJumper, double height)
	{
		super(height);
		
		if (weightOfJumper >= 50 && weightOfJumper <= 300)
		{
			this.weightOfJumper = weightOfJumper;
		}
		else
		{
			this.weightOfJumper = 140;
		}
	}
	public Trampoline(Trampoline toCopy)
	{
		super(toCopy);
		weightOfJumper = toCopy.weightOfJumper;
	}
	public void setWeightOfJumper (int weight)
	{
		if (weight >= 50 && weight <= 300)
		{
			this.weightOfJumper = weight;
		}
		else
		{
			this.weightOfJumper = 140;
		}
	}
	public int getWeightOfJumper()
	{
		return weightOfJumper;
	}
	public double heightAfterBounces(int numOfBounces)
	{
		double height = super.getHeight();
		double bounciness = ((double) getWeightOfJumper() + height) / (3.5 * height);
		//bounciness += bounciness; 
		height = height * Math.pow((bounciness/100), numOfBounces);
		
		if (height < 1)
		{
			height = 0;
		}
		
		return height;
	}

	
	public String toString()
	{
		return "[Trampoline] " + super.toString();
		
	}	
}
