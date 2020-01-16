//Kyriacos Sophocleous 24715955s
//a class for the panel of the cards

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class cardsPanel extends JPanel{ //cards panel is a JPanel
	
	//attributes
	JPanel[] cardsP;																//an array of JPanels
	JLabel[] labels ={new JLabel("King"),new JLabel("Queen"),new JLabel("Jack")};	//an array of 3 JLabels each one of them is initialized to the corresponding card
	Border blackline = BorderFactory.createLineBorder(Color.black);					//declaration of a border which will be used around the cards
	Font f = new Font("Serif", Font.PLAIN, 25);										//a font variable to change the fonts of the cards
	
	//constructor
	public cardsPanel() {
		cardsP=new JPanel[3];							//the array of JPanels has 3 elements
		for(int i=0;i<3;i++) {							//for each one of them
			cardsP[i]=new JPanel(new GridBagLayout());	//GridBagLayout is used
		}
        this.setLayout(new GridLayout(1,3,20,20));		//layout of the whole panel is a 1X3 Grid Layout with margin of 20 pixels between the elements
        this.setBorder(new EmptyBorder(20,0,20,0));		//setting of the margin around the whole panel
        cards();										//calling of cards method to add the cards on the panel
	}
	
	//a method that adds the cards to the panel
	public void cards() {
		for (int i=0;i<3;i++) {						//for each one of the three panels
			labels[i].setFont(f);					//set the font of the corresponding label	
			cardsP[i].add(labels[i]);				//add the label to each card
			cardsP[i].setBorder(blackline);			//set the border for each card
			cardsP[i].setBackground(Color.yellow);	//set the color for the cards
			this.add(cardsP[i]);					//add each panel to the main panel
		}
	}
	
	//a method that updates the labels of each card
	public void updateCard(int cardNumber,int i) { //method needs to be passed an integer number which represents the kind of the card(Joker,Jack,Queen,King,Ace) and an integer which indicates which one of the three cards is updated
		if (cardNumber==0) {					   //if the number that represents the kind of the card is 0
			labels[i].setText("Joker");			   //the text in label has to be set to "Joker"
		}else if (cardNumber==1) {				   //if the number that represents the kind of the card is 1
			labels[i].setText("Jack");			   //the text in label has to be set to "Jack"
		}else if(cardNumber==2) {				   //if the number that represents the kind of the card is 2
			labels[i].setText("Queen");			   //the text in label has to be set to "Queen"
		}else if(cardNumber==3) {				   //if the number that represents the kind of the card is 3
			labels[i].setText("King");			   //the text in label has to be set to "King"
		}else if(cardNumber==4) {				   //if the number that represents the kind of the card is 4
			labels[i].setText("Ace"); 		       //the text in label has to be set to "Ace"
		}
	}
	
	//a method to (re)initialize the texts of the labels
	public void resetCards() {
		labels[0].setText("King");	//the text of first card is initialized to "King"
		labels[1].setText("Queen");	//the text of second card is initialized to "Queen"
		labels[2].setText("Jack");	//the text of third card is initialized to "Jack"
	}
	
	//a getter for a particular JLabel
	public JLabel getLabel(int i) { //it is passed the corresponding integer
		return labels[i];			//and returns the associated label
	}
	
}
