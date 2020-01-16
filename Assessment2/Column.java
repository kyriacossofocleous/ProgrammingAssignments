/*Kyriacos Sophocleous 2471955s*/

//This is a class to represent a Column
public class Column {
	
	//attributes
	private int numRows;	//attribute which defines the number of the rows of the column
	private Counter[] arr;	//an array of Counters
	private int n;			//this is an attribute to define the next element of the array that will be filled
	
	//constructor
	public Column(int numRows) {
		this.numRows=numRows;				//definition of numRows in the constructor
		arr= new Counter[this.numRows];		//the length of the Counters' array is numRows
		n=numRows-1;						//this is the position of the bottom. The position the first counter will fall into.						
	}
	
	//a Counter getter
	public Counter getCounter(int row) {	//it takes the row of the array is an argument
		return arr[row];					//it returns the Counter which is located in that row
	}										//this method is used in the winning method of the board, so we can compare the Counters. It is different than a standard getter of a class.
	
	//n getter
	public int getN() {						//this is a method to get the next element of the array which is to be filled
		return n;							//this getter is also used in the winning method, so we know where the counter was added
	}
	
	//a method to tell if a column is full
	public boolean isFull() {
		if (n>=0) {
			//if the next element to be filled is less than 0 then the column is full. The top of the column in the game is represented with 0
			return false;	//returns true if the column is full
		}else {
			
			return true;	//return false if the column is not full
		}
	}
	
	//a method to add a Counter object in the column
	public boolean add(Counter c) {	//it takes as argument a Counter object
		if (!isFull()) {			//if the column is not full
			arr[n]=c;				//adds a counter to the corresponding position
			n--;					//reduces n to move to the next array element
			return true;			//return true value
		}else {
			return false;			//if the columns is full it returns a false value and does not add a Counter to the Column
		}
		
	}
	
	
	//a method that returns the character in the corresponding row
	public String displayRow(int rowNumber) {	//takes a row Number as an argument
		String line;							//it will return a String
		if (arr[rowNumber]==null) {				//if the array's element of this row number is null
			line=String.format("%c",' ');		//the string is formatted so it contains a space character
			
		}else {									//if the array's element of this row is not null then
			line=String.format("%c", arr[rowNumber].getPlayer().getCounter());	//the line is formatted so it contains the character which is in the element of this row
			//in order to do so, we use the getter which is inside the Counter method, for the Counter of the arr[rowNumber]. After we get the player we can get access to the counter(character) of him through the getter in the Player object method.
		}
		return line;		//the String line is returned
	}
	
	//a method to display each row in a separate line
	public void display() {
		for (int i=0;i<arr.length;i++) {			//i is the counter. It indicates the row
			System.out.println(displayRow(i));		//We use the displayRow method to get the character of each row and then we display it
		}
	}
}



