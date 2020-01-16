/*Kyriacos Sophocleous 2471955s*/

//This is a class to represent a board
public class Board {
	
	//three attributes
	private int rowsNumber;			//one for the number of the rows
	private int columnsNumber;		//one for the number of the columns
	private Column[] arr;			//this is an array of Column references. Thus, it is actually a two dimension array
	
	//constructor
	public Board(int rowsNumber,int columnsNumber) {
		this.rowsNumber=rowsNumber;			//each attribute associates with the corresponding argument
		this.columnsNumber=columnsNumber;
		arr=new Column[this.columnsNumber];	//the array of Columns has length of columnsNumber
		for (int i=0;i<arr.length;i++) {	//each column has the length of rowsNumber
			arr[i]=new Column(rowsNumber);
		}
	}
	
	//add method
	public boolean add(Counter c, int columnNum) { //takes a Counter object and a column number (integer) as arguments
			return arr[columnNum].add(c);			//if if the board successfully adds the counter to that column return true, else it returns false
			//to achieve this we use the add method from Column class. If the Column[ColumnNum] is full then the board cannot add a Counter.
	}
	
	//a method to check if the board is full
	public boolean isFull() {
		boolean isItFull=true;				//initialization of the outcome
		for(int i=0;i<arr.length;i++) {		//i is the counter for the columns
			if(arr[i].isFull()==false) {	//we check if every column is full using the isFull method from Column class
				isItFull=false;				//if one column is not full then the board is not full also
			}
		}
		return isItFull;					//if all the Columns are full the we return the true value we used at the beginning of the method
	}
	
	//a method to check if a column of a board is full
	public boolean columnFull(int columnNum) {		//we take the column number as an argument
		if(arr[columnNum].isFull()) {				//we use the isFull method from Column class
			return true;							//this method is used in the static main method to check if the move which the player is intended to do, is allowed
		}else {										//if the column is full it is not allowed (it returns true)
			return false;							//if the column is not full the move is allowed (it returns false)
		}
	}
	
	//columnsNumber getter
	 public int getColumnsNumber() {	//it returns the number of the columns of the table
	    	return columnsNumber;		//it is used in the main method to give the limits of the random number
	 }
	 
	//the real challenge
	public boolean win(Counter ofPlayer,int nextMove) {															//it takes the Counter of the player who made the move and the column of the move as arguments
		int rowOfNextMove=arr[nextMove].getN()+1;																//by using the getN method of the column class we can get access to the row that the counter was placed
		boolean resultVertical=verticalWin(ofPlayer,rowOfNextMove,nextMove);									//the problem is split in 4 parts
		boolean resultHorizontal=horizontalWin(ofPlayer,rowOfNextMove);											//we define 4 boolean variables, one for the vertical check, one for the horizontal check, one to check if there is diagonal ascending win and one for the diagonal descending win 
		boolean resultDiagonalAscending=diagonalAscendingWin(ofPlayer, rowOfNextMove,nextMove);					//each method takes the counter of the player who has just play, the column of the move and the row of the move as arguments
		boolean resultDiagonalDescending=diagonalDescendingWin(ofPlayer,rowOfNextMove,nextMove);				//the corresponding boolean variable takes a true value, if there is a win in the way that it describes
		if (((resultVertical)||(resultHorizontal))||((resultDiagonalAscending)||(resultDiagonalDescending))) {	//if any of the boolean variables holds a true value there is a winner
			return true;																						//and the method returns a true value
		}else {
			return false;																						//or a false value if there is not even one true value in these variables
		}
	}
	
	//first check
	public boolean verticalWin(Counter ofPlayer,int rowOfNextMove,int nextMove) {	//it takes the counter of the player who has just played, the column of the move and the row of the move as arguments and returns a boolean value
		boolean output=false;														//the output is initialized as false
		if (rowOfNextMove<rowsNumber-3) {											//we cannot have a vertical win in the bottom three rows, so an if statement is used to limit the number of the checks and to avoid ArrayhIndexOutOfBounds exception
			int counter=1;															//counter is initialized to 1 so we avoid the first check, which is a check between the counter that has just been played and itself (always true). It counts how many same counters are there below each other
			for (int i=1;i<4;i++)  {												//we only need to check the three counters just below the one that has just been played
				if (ofPlayer.equals(arr[nextMove].getCounter(rowOfNextMove+i))) {	//the comparison is done by equals method in counter class. the object that is compared with the counter of player is accessed by getCounter method in Column class. getCounter takes the row Number as an argument, and each time an i is added so we can move to the next element
					counter++;														//if the counter of the player and the counter of the same column in the same row+i of it is the same we add +1 to the counter
				}else {		
					break;															//if any one of them three is not the same with the counter of player, then there is not a win and we do not need to make further checks
				}
			}
			if (counter==4) {														//if the counter is four there are 4 same counters one below each other
				output=true;														//and there is a win, so the method returns true value
			}
		}
		return output;																//if there is not a win the initialized false value is returned by the method
	}
	
	//second check
	public boolean horizontalWin(Counter ofPlayer,int rowOfNextMove) {	//it takes the counter of the player who has just played, the column of the move and the row of the move as arguments and returns a boolean value
		int counter=0;													//the counter here is initialized to 0 because we check the whole row of the board and not only the elements left or right of the counter that has just been played. I believe is more efficient this way, because we would need more if statements to find out how many checks we need to do on the right and on the left of the counter that has just been played.
		for (int i=0;i<columnsNumber;i++) {								//a for loop to check every next column
			if (ofPlayer.equals(arr[i].getCounter(rowOfNextMove))) {	//the comparison is again done by equals method in counter class. This time, row stays constant and what is changed is the column
				counter++;												//for every same consecutive element, we add +1 to the counter
				if (counter==4) {										//if there are four same consecutive elements there is a win
					return true;										//and the method returns a true value
				}
			}else {														
				counter=0;												//but if two consecutive elements are different, we have to reinitialize the counter to 0 and proceed to the next element
			}
		}
		return false;													//if by the complete check of the row, no 4 consecutive same counters are found then there is not a win and a false value is returned
	}
	
	//third check
	public boolean diagonalAscendingWin(Counter ofPlayer,int rowOfNextMove,int nextMove) {				//it takes the counter of the player who has just played, the column of the move and the row of the move as arguments and returns a boolean value
		int counter=1;																					//counter is initialized to 1 so we avoid the first check, which is a check between the counter that has just been played and itself (always true). It counts how many same counters are there diagonally ascending
		int checkThisUp=0;																				//this is an indicator of how many checks we have to do diagonally up and ascending to the counter that has just been played. it is initialized to 0 (it does not really matter)
		int checkThisDown=0;																			//this is an indicator of how many checks we have to do diagonally down and ascending to the counter that has just been played. Again, it is initialized to 0 but that it does not matter
		boolean output=false;																			//we initialize the outcome of the method as false
		if ((rowOfNextMove+nextMove>2)&&(rowOfNextMove+nextMove<(columnsNumber-1)+(rowsNumber-1)-2)){	//a diagonal ascending win is not possible on the left upper corner of the table and on the right lower corner of the table. Thus, we limit the number of checks the program has to do using an if statement
			if(rowOfNextMove+nextMove<rowsNumber) {														//each element of the board demands different amount of checks diagonal up and diagonal down, depending of its location
				checkThisUp=rowOfNextMove;																//3 different zones can describe the behavior of each element accordingly. Each zone is depicted with an if statement 
				checkThisDown=nextMove;																	//if the sum of the column and the row of next move is less than the number of rows of the table, the number of checks we have to do diagonally up is the same as the row of the counter that has just been played. Moreover, the number of checks we have to do diagonally down is the same as the column the counter has just been put into
			}else if ((rowOfNextMove+nextMove>=rowsNumber)&&(rowOfNextMove+nextMove<columnsNumber)){	//but if the sum of the column and the row of next move is greater or even compare to the number of rows of the table and less than the number of columns of the table
				checkThisUp=rowOfNextMove;																//the amount of diagonal up checks we have to perform is still the same as the row that the counter is into 
				checkThisDown=rowsNumber-1-rowOfNextMove;												//the amount of diagonal down checks we have to perform equals to the number of rows of the table - 1 subtracting the row that the counter has just been put into
			}else if((rowOfNextMove+nextMove>rowsNumber)&&(rowOfNextMove+nextMove>=columnsNumber)) {	//if the sum of the column and the row of next move is greater than the number of rows of the table and equal or greater than the number of columns of the table
				checkThisUp=columnsNumber-1-nextMove;													//the amount of diagonal up are defined with by subtracting the column of the next move to the number of columns - 1
				checkThisDown=rowsNumber-1-rowOfNextMove;												//but now the diagonal down checks is the same as in the previous zone
			}
			//it is a complicated algorithm. It works perfect for tables that the number of columns is greater or even to the number of rows, but I have to admit that it does not work if the number of rows is greater than the number of columns.
			//in order to achieve this we could make some additional checks, but the algorithm will get even more complicated and I also, do not believe that there is a reason someone can choose a board like this.
			
			if (checkThisUp>4) {	//if any of the amount of checks we need to perform is greater than 4
				checkThisUp=4;		//we reduce this to just 4
			}
			if (checkThisDown>4) {	//so we limit the number of checks the machine has to do
				checkThisDown=4;	//and also prevent ArrayIndexOutOfBounds exception
			}
			
			for(int i=1;i<=checkThisUp;i++) {											//a for loop to perform the diagonal up ascending check
				if (ofPlayer.equals(arr[nextMove+i].getCounter(rowOfNextMove-i))) {		//again, we use the equal method of counter class and the getCounter method of column class. This time, we change both the row and the column of the next counter
					counter++;															//for every next equal counter to the player's counter we add +1 to the counter
					if(counter==4) {													//if the counter is 4, that means there are 4 consecutive diagonal up ascending equal counters 
						return true;													//and the method returns a true value
					}
				}else {	
					break;																//but if the next counter is not equal to the player's counter we are not proceeding to more diagonal up checks
				}																		//we do not reinitialize counter to 0, because we also have to check for diagonal down counters
			}
		}
		
			
			for (int i=1;i<=checkThisDown;i++) {										//a for loop to perform the diagonal down ascending check
				if (ofPlayer.equals(arr[nextMove-i].getCounter(rowOfNextMove+i))) {		//same concept but now we increase the row and decrease the column
					counter++;															//this is the same counter that we used in the previous loop, so it holds the value
					if (counter==4) {													//if the counter are 4, that means there are 4 consecutive diagonal ascending equal counters
						output=true;													//and the output is now a true value
						break;															//and we do not need to perform more checks
					}
				}else {																	//but if any of the counters is not equal to the player's counter
					break;																//then there is not a win
				}
			}
			return output;																//if the program reaches this point it returns the initialized output value(false)
	}
	
	//fourth check 
	public boolean diagonalDescendingWin(Counter ofPlayer,int rowOfNextMove,int nextMove) {																//it takes the counter of the player who has just played, the column of the move and the row of the move as arguments and returns a boolean value
		int counter=1;																																	//counter is initialized to 1 so we avoid the first check, which is a check between the counter that has just been played and itself (always true). It counts how many same counters are there diagonally descending
		int checkThisUp=0;																																//this is an indicator of how many checks we have to do diagonally up and descending to the counter that has just been played. it is initialized to 0 (it does not really matter)
		int checkThisDown=0;																															//this is an indicator of how many checks we have to do diagonally down and descending to the counter that has just been played. Again, it is initialized to 0 but that it does not matter
		boolean output=false;																															//we initialize the outcome of the method as false
		if (((rowOfNextMove<rowsNumber-3)&&(nextMove-rowOfNextMove<columnsNumber-3))||((rowOfNextMove>=rowsNumber-3)&&(rowOfNextMove-nextMove<3))){		//a diagonal descending win is not possible on the left lower corner of the table and on the right upper corner of the table. Thus, we limit the number of checks the program has to do using an if statement
			if (rowOfNextMove>=nextMove) {																	//again each element of the board demands different amount of checks diagonal up and diagonal down, depending of its location. Thus, we have to split the table in 3 different zones
				checkThisUp=nextMove;																		//each one of them is represented with an if statement
				checkThisDown=rowsNumber-1-rowOfNextMove;													//if the row of the move is greater or even to the column of the move, then the amount of checks we have to do diagonally up is the same as the column number.The one for diagonally down is defined by subtracting the row from the number of rows-1 
			}else if(((rowOfNextMove<nextMove)) && (nextMove-rowOfNextMove<=columnsNumber-rowsNumber)){		//if the column of the next move is greater than the row of it and the difference among them is less than the difference among the number of columns and number of rows
				checkThisUp=rowOfNextMove;																	//diagonal up checks that have to be performed are the same as the row of the counter that has just been played
				checkThisDown=rowsNumber-1-rowOfNextMove;													//and diagonal down checks are equal to the subtraction of the number of rows -1 minus the row of the move
			}else if((rowOfNextMove<nextMove)&&(nextMove-rowOfNextMove>columnsNumber-rowsNumber)) {			//but if the deference among them is greater or equal to the difference between the number of columns and the number of rows
				checkThisUp=rowOfNextMove;																	//diagonal up checks that have to be performed are still the same as the row of the counter that has just been played
				checkThisDown=columnsNumber-1-nextMove;														//and diagonal down checks can be calculated by subtracting the column of the next move from the number of the columns of the table -1
			}
			
			if (checkThisUp>4) {	//if any of the amount of checks we need to perform is greater than 4
				checkThisUp=4;		//we reduce this to just 4
			}
			if (checkThisDown>4) {	//so we limit the number of checks the machine has to do
				checkThisDown=4;	//and also prevent ArrayIndexOutOfBounds exception
			}
			
			for(int i=1;i<=checkThisUp;i++) {										//a for loop to perform the diagonal up descending check
				if (ofPlayer.equals(arr[nextMove-i].getCounter(rowOfNextMove-i))) {	//same procedure. We gain access to the counter using getCounter method from column method and compare it with the counter of the player using the equals method of counter class.
					counter++;														//for every next equal counter to the player's counter we add +1 to the counter
					if(counter==4) {												//if the counter is 4, that means there are 4 consecutive diagonal up descending equal counters
						return true;												//and the method returns a true value
					}
				}else {																//but if the next counter is not equal to the player's counter we are not proceeding to more diagonal up checks
					break;															//we do not reinitialize counter to 0, because we also have to check for diagonal down counters
				}
			}
			
			for (int i=1;i<=checkThisDown;i++) {									//a for loop to perform the diagonal down descending check
				if (ofPlayer.equals(arr[nextMove+i].getCounter(rowOfNextMove+i))) {	//we manipulate the row and the column we want to check with i
					counter++;														//this is the same counter that we used in the previous loop, so it holds the value
					if (counter==4) {												//if the counter are 4, that means there are 4 consecutive diagonal descending equal counters
						output=true;												//and the output is now a true value
						break;														//no further check needed
					}
				}else {																//but if any of the counters is not equal to the player's counter
					break;															//then there is not a win
				}
			}
		}	
		return output;																//if the program reaches this point it returns the initialized output value(false)
	}
	
	
	//toString() method of Board Class
    public String toString() {
        String output = "";					//declaration of an empty String so we can manipulate it
        for (int i=0;i<arr.length;i++) {	//a for loop to add the |num of the header. Repeat this for every number
        	output+="|"+i;	
        }
        output+="|"+"\n";					//add another | in the header and change line
        for (int i=0;i<arr.length;i++) {	//a for loop to create the dashed line below the header
        	output+="--";					//add 2 dashes for every array element
        }
        output+="\n";						//change line
        for(int j=0;j<rowsNumber;j++) {					//one row at a time. a for loop to change rows
        	for(int i=0;i<arr.length;i++) {				//a for loop to move to the next column
        		output+="|"+arr[i].displayRow(j);		//i is the counter for columns, j is the on for rows. We use the displayRow method of Column Class
        	}
        	output+="|"+"\n";							//add a | and change line after the display of each Column
            }
        return output;									//return what is saved inside output
        }
    }

