//Kyriacos Sophocleous 2471955s
//This is the model class of the fruit machine
//model is an object that initialize the game
//and also assigns a value to each card, calculates the points of a hand and updates the Balance for a spin

import java.util.Random;			//import of Random utility of Java. It is used to generate every next card
public class FruitMachineModel {
	
	//attributes of the class
	int[] cards=new int[3];			//declaration of an array containing 3 integer elements. Each one of them represents a card of the game
	int jokers,balance,points;		//jokers attribute indicates how many jokers are in a spin. Balance stores the total points. Points saves the points that have to be added to/subtracted from balance for a spin 
	Random r=new Random();			//declaration of r attribute. R is used to generate random numbers
	
	//constructor of the class
	public FruitMachineModel() {
		resetGame();				//calling of resetGame method to initialize the game
	}
	
	//resetGame class is used to set the initial values of the cards and balance
	public void resetGame() {
		cards[0]=3;					//0=Joker, 1=Jack, 2=Queen, 3=King, 4=Ace
		cards[1]=2;					//first card is set as King, second is set as Queen, third is set as Jack
		cards[2]=1;					//each card is represented by the respective element of cards array
		balance=100;				//balance is initialized to 100
	}
	
	//nextMove class calculates how many points have to be added to/subtracted from balance and updates balance for a spin
	public void nextMove() {
		jokers=0;					//jokers attribute is locally initialized to 0
		points=0;					//points attribute is also initialized to 0. Zero values is also used if there are no jokers and no cards are identical
		for (int i=0;i<3;i++) {		//a for loop to count how many jokers are in a particular hand
			if (cards[i]==0) {		//if any of three cards is zero (0 represents jokers)
				jokers++;			//add 1 to the total number of jokers
			}
		}
		if (jokers==0) {																	//if there are not any jokers in the hand
			if ((cards[0]==cards[1])&&(cards[1]==cards[2])){								//and every card is identical
				points=50;																	//add 50 points to the balance
			}else if((cards[0]==cards[1])||(cards[1]==cards[2])||(cards[0]==cards[2])) {	//if only two cards are identical (and there are no jokers)
				points=20;																	//add 20 points to the balance
			}
		}else {																				//but if there are jokers
			points=(-25)*jokers;															//subtract 25 points for each one of them
		}
		balance+=points;			//balance update
	}
	
	
	//spin class provides a new hand of cards
	public void spin() {
		for (int i=0;i<3;i++) {		//for each one of three cards
			cards[i]=r.nextInt(5);	//set a new random integer number (upper limit is 4)
		}
	}
	
	//this is a getter for a single card
	public int getCards(int i) { //to get a particular card, method is passed the corresponding integer
		return cards[i];		 //and returns that card
	}
	
	//this is a getter for the balance
	public int getBalance() {
		return balance;			//it returns the balance
	}
	
	//this is a getter for the points
	public int getPoints() {
		return points;			//it returns the points of a single spin
	}
}
