/*Kyriacos Sophocleous 2471955s*/

//ConnectFour Class
import java.util.Random;	//the two players are using the random utility to choose their next move
public class ConnectFour {
	
	//static main method
	public static void main(String[] args) {
			Board board=new Board(6,7);						//creation of a board object with 6 rows and 7 columns
			playToWin(board);								//call of randomPlay method
		}
	
	//a static method in which two players play randomly and exits when the board is full
	public static void randomPlay(Board board) {
		//initialization
				int nextMove;									//an integer which stores the player's next move
				Random r=new Random();							//r is a Random object
				Player p1 = new Player("Clive",'o');			//player object p1 with a String attribute for the name and a character attribute for the counter
				Player p2 = new Player("Sharon",'x');			//player object p2
				Player nextPlayer=p1;							//player object nextPlayer to determine who is the next player. The initial value is the reference of p1 
				
				//for loop
				for(;;) {											//an infinite loop. Ends when the board is full
				nextMove=r.nextInt(board.getColumnsNumber());		//the next move is a random number. we use the getColumnsNumber method from board class so we can define the upper limit of the random number.
				while(board.columnFull(nextMove)) {					//while the column of the number which was picked randomly is full
					nextMove=r.nextInt(board.getColumnsNumber());	//pick another one. Until the column is not full
				}
				board.add(new Counter(nextPlayer),nextMove);		//when the column is suitable, use add method to add the counter of the player in turn to the appropriate column
				System.out.println(board.toString());				//print the board. //I chose to print the board after every move. If we want the board to be printed after the end of the game we have to move this instruction after the for loop
				if (nextPlayer==p1) {								//if statement to check the winner or change to the next player
					nextPlayer=p2;									//if there is not a win then go to the next player
				}else {												//if the player is not p1 (so it is p2)
					nextPlayer=p1;									//if there is not a win then go to the next player
				}
				if (board.isFull()) {								//if the board is full
					break;											//exit the infinite loop
				}
					}
		}
	
	//a static method in which two players play randomly and exits when the board is full or someone wins
	public static void playToWin(Board board) {
		//initialization
		int nextMove;									//an integer which stores the player's next move
		boolean win=false;								//a boolean value to determine if there is a winner
		Random r=new Random();							//r is a Random object
		Player p1 = new Player("Clive",'o');			//player object p1 with a String attribute for the name and a character attribute for the counter
		Player p2 = new Player("Sharon",'x');			//player object p2
		Counter c1=new Counter(p1);						//counter object c1 linked to the corresponding player
		Counter c2=new Counter(p2);						//counter object c2 linked to p2
		Player nextPlayer=p1;							//player object nextPlayer to determine who is the next player. The initial value is the reference of p1 
		
		//for loop
		for(;;) {											//an infinite loop. Ends when someone wins or the board is full//player object nextPlayer to determine who is the next player. The initial value is the reference of p1 
		nextMove=r.nextInt(board.getColumnsNumber());		//the next move is a random number. we use the getColumnsNumber method from board class so we can define the upper limit of the random number.
		while(board.columnFull(nextMove)) {					//while the column of the number which was picked randomly is full
			nextMove=r.nextInt(board.getColumnsNumber());	//pick another one. Until the column is not full
		}
		board.add(new Counter(nextPlayer),nextMove);		//when the column is suitable, use add method to add the counter of the player in turn to the appropriate column
		System.out.println(board.toString());				//print the board. //I chose to print the board after every move. If we want the board to be printed after the end of the game we have to move this instruction after the for loop
		if (nextPlayer==p1) {								//if statement to check the winner or change to the next player
			win=board.win(c1, nextMove);					//boolean value stores the value that is returned from the win method in board class
			if (win) {										//if the boolean value is true
				System.out.println(p1.getName()+" wins!");	//print a win message
				break;										//exit the infinite loop
			}
			nextPlayer=p2;									//if there is not a win then go to the next player
		}else {												//if the player is not p1 (so it is p2)
			win=board.win(c2, nextMove);					//check if player 2 wins	
			if (win) {										//if the boolean value is true
				System.out.println(p2.getName()+" wins!");	//print a win message
				break;										//exit the infinite loop
			}
			nextPlayer=p1;									//if there is not a win then go to the next player
		}
		if (board.isFull()) {								//if the board is full
			break;											//exit the infinite loop
		}
			}
		}
	}
			
			
			
