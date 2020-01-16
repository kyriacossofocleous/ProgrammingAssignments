/*Kyriacos Sophocleous 2471955s*/

//This is a class to represent a Counter
public class Counter {
	
	//One attribute
	private Player p;	//reference to a Player object, private
	
	//Constructor
	public Counter(Player p) {	//this is the player that the counter belongs to
		this.p=p;				//we pass the reference of the player to the attribute of the class
	}
	
	//this is a getter for the Player attribute
	public Player getPlayer() {	
		return p;				//it returns a reference to a Player object
	}
	
	//toString() method
	public String toString() {	
		String line=String.format("%c",p.getCounter());	//we define a String object named line. We use the "format" method of this object and "%c" to pass a character.
		//getCounter is a getter of a Player Object, so we use it here in order to get the character of the counter which is associated with Player p
		return line;									//the method returns the String line which contains just what is in counter attribute in players method for player p(a character).
	}
	
	//equals(Object o) method
	public boolean equals(Object o) {				   //it will compare the current object with an "o" object
		if (o instanceof Counter) {					   //first we check if the current object and o object have the same type (counter)
			Counter other=(Counter) o;				   //we create a new Counter object (other) and we pass the reference of object o. (Counter) means that we treat o as a Counter object
			if (this.getPlayer()==other.getPlayer()) { //a statement to check if the current object and object o belong to the same player
				return true;						   //if yes, we return a logical true value
			}else
				return false;						   //if no, then we return o false value
		}else {
			return false;							   //if o is not a Counter object we return a false value, because we cannot make a comparison between objects of different type
		}
	}
}
