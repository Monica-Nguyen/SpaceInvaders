public abstract class Animal{
	private char type;
	private int health;
	
	public Animal(char type, int health) {
		
		this.type = (type == 'm') ? type : (type == 'b') ? type : (type == 'f') ? type : (type == 'r') ? type : (type == 'a') ? type : 'n'; 
		
		this.health = (health > 0) ? health : 50;
	}
	
	public Animal(Animal toCopy){
		
		type = toCopy.type;
		health = toCopy.health;
	}
	
	public char getType() {
		return type;
	}
	
	protected void setType(char aType) {
		this.type = (aType == 'm') ? aType : (aType == 'b') ? aType : (aType == 'f') ? aType : (aType == 'r') ? aType : (aType == 'a') ? aType : 'n'; 
	}
	public int getHealth() {
		
		return health;
	}
	public void setHealth(int health) {
		
		this.health = (health > 0) ? health : 50;
	}
	
	public abstract double getRelativeHealth();
	
	public String getStatus() {
		
		return (this.getRelativeHealth() < 25) ? "critical" : (this.getRelativeHealth() < 50.0) ? "tenuous" : (this.getRelativeHealth() < 75.0) ? "good" :  "excellent";
	}
	
	public String toString() {
		return "Type: " + this.type + " Health: " + getRelativeHealth();
	}
}