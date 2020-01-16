/*Kyriacos Sophocleous 2471955s*/

//This is a class to represent a Player 
public class Player {
	
	//attributes
	private String name;	//for the player's name
	private char counter;	//to store the player's counter symbol
	//both attributes are private so others are not allowed to change them
	
	//constructor
	public Player(String name,char counter) { //defines what we have to pass
		this.name=name;						  //in order to distinguish between the two different attributes (class' and local) we use "this" to refer to the class' attribute
		this.counter=counter;				  //this.counter is the class attribute and counter is the local one
	}
	public String getName() {	//this is a getter for the name attribute
		return name;
	}
	public char getCounter() {	//this is a getter for the counter attribute
		return counter;
	}
	public String toString() {	//toString() method tells Java how to interpret the Player object as a String
		return name;			//in this case it just returns the name
	}
}
