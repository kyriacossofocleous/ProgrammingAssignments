//Kyriacos Sophocleous 2471955s
//A class for the panel of the messages

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class messagesPanel extends JPanel {	//messages panel is a JPanel
	
	//attributes	
	JLabel balance,result,winlose;				//three labels, one for each of the three messages	
	Font f = new Font("Serif", Font.PLAIN, 18); //a font variable to modify the fonts of the labels
	
	//constructor
	public messagesPanel(JLabel balance,JLabel result,JLabel winlose) {
		this.balance=balance;					//three labels are passed from the view method
		this.result=result;						//one for each message
		this.winlose=winlose;					//and are linked to the attributes of the class
		
		balance.setFont(f);						//the font of every message is set using the f font variable
		result.setFont(f);
		winlose.setFont(f);
		
		this.setLayout(new BorderLayout());		//usage of border layout as the layout of the panel
		this.add(balance,BorderLayout.NORTH);	//each message goes to the corresponding place
		this.add(result,BorderLayout.CENTER);
		this.add(winlose,BorderLayout.SOUTH);
		resetMessages();						//calling of resetMessages method of the class to initialize the messages
	}
	
	//a method that (re)initializes the messages
	public void resetMessages() {
		balance.setText("balance is 100"); //initial text of balance
		result.setText("Welcome!");		   //initial text of result
		winlose.setText("");			   //last message only appears when there is win/lose, so is set as null
	}
	
	//a method that updates the result and balance messages
	public void updateMessage(int total,int modelBalance) {
		balance.setText("balance is "+modelBalance);				//method is passed the balance from the model object and the message of balance is set appropriately
		if (total==-75) {											//the points of each spin is also passed to the method and its is used to set the corresponding message
			result.setText("3 JOKERS: you loose 75 points");		//if there are 3 jokers the player looses 75 points
		}else if(total==-50) {
			result.setText("2 JOKERS: you loose 50 points");		//if there are 2 jokers the player looses 50 points
		}else if(total==-25) {
			result.setText("1 JOKER: you loose 25 points");			//if there is 1 joker player looses 25 points
		}else if (total==0) {
			result.setText("Balance unchanged");					//if there are not any identical cards and no jokers balance is unchanged
		}else if (total==20) {
			result.setText("Two of a kind - you win 20 points");	//if there are 2 identical cards player wins 20 points
		}else if (total==50) {
			result.setText("Three of a kind - you win 50 points");	//if all 3 cards are identical player wins 50 points
		}
	}
}
