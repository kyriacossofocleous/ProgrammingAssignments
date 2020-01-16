//Kyriacos Sophocleous 2471955s
//This is the view class of the fruit machine
//view is used to generate a Graphical User Interface for the fruit machine
//and update it when is needed

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.*;


public class FruitMachineView extends JFrame {
	
	//attributes
	JButton spin,newGame;						//two buttons: one to play next turn, one to reset the game when this is finished
	JLabel balance,result,winlose;				//three labels for the three messages
	FruitMachineModel model;					//creation of a model object so view and model work together
	FruitMachineController controller;			//creation of a controller object in order to joint them
	cardsPanel cards;							//declaration of the panel of cards
	buttonsPanel buttons;						//declaration of the panel of buttons
	messagesPanel messages;						//declaration of the panel of messages
	int[] cardsArray;							//an array of integer elements
	Font f = new Font("Serif", Font.PLAIN, 18);	//font object used to make modifications on the fonts of the messages
	
	//constructor
	public FruitMachineView(FruitMachineModel modelObject,FruitMachineController controllerObject) {
		model=modelObject;										//the model object which was created in attributes' block is passed a model object from the main class
		controller=controllerObject;							//the controller object which was created in attributes' block is passed a controller object from the main class
		cardsArray=new int[3];									//cards array has 3 integer elements. one for each card
		spin=new JButton("spin");								//spin button has "spin" written on it
		spin.addActionListener(controller);						//spin button has to do some things when is pressed, so we need to add an action listener which is linked to the controller's class
		newGame=new JButton("new game");						//newGame button has "new game" written on it
		newGame.addActionListener(controller);					//again, the button actions are managed in controller class
		balance=new JLabel();									//label to display the balance
		result=new JLabel();									//label to display the output of each spin
		winlose=new JLabel();									//label which indicates if there is a win/lose in the game
		
		final int HEIGHT = 400;									//variable for height of the window is 400 pixels. This is final, it can not be changed 
		final int WIDTH = 600;									//variable for width of the window is 600 pixels. Again, this is final.
		this.setTitle("Fruitmachine GUI");						//the title of the window
		this.setSize(WIDTH,HEIGHT);								//setting the size of the window using the two variables that have been created prior
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//setting what the window does when user press close button
		this.setLocation(100,100);								//setting the location of the window
		this.setLayout(new BorderLayout());						//border layout is used as the main layout of the gui
		borders();												//calling of borders method to add the margin of the gui
		components();											//calling of components method to add all the components to the gui
	}
	
	//a method to add margin to the gui
	public void borders() {											
		final int space = 20;										//the margin is 20 pixels. Can not be changed
		JPanel northPanel = new JPanel(new FlowLayout()); 			//need to declare 4 panels
        JPanel southPanel = new JPanel(new FlowLayout()); 			//one for each 4 borders
        JPanel eastPanel = new JPanel(new FlowLayout()); 			//flow layout is used
        JPanel westPanel = new JPanel(new FlowLayout());			//all 4 of them will be blank
        northPanel.setPreferredSize(new Dimension(WIDTH, space));	//setting the dimensions of each panel
        southPanel.setPreferredSize(new Dimension(WIDTH, space));	
        eastPanel.setPreferredSize(new Dimension(space,HEIGHT));
        westPanel.setPreferredSize(new Dimension(space,HEIGHT));
        this.add(northPanel,BorderLayout.NORTH);					//add every panel to the main layout
        this.add(southPanel,BorderLayout.SOUTH);					//each one to the corresponding place
        this.add(eastPanel,BorderLayout.EAST);
        this.add(westPanel,BorderLayout.WEST);
	}
	
	//a method that adds the components to the layout
	public void components() {
		JPanel center=new JPanel(new GridLayout(2,2));			//The center panel of the main border layout is a 2X2 grid layout panel
		JPanel empty=new JPanel();								//the upper right panel is an empty panel
		messages=new messagesPanel(balance,result,winlose);		//passing the 3 labels to the messagesPanel class in order to create the message panel
		cards=new cardsPanel();									//cards panel does not require anything to be passed
		buttons=new buttonsPanel(spin,newGame);					//passing the 2 buttons to the buttonsPanel class in order to create the buttons panel 
		
		center.add(messages);									//upper left panel is the messages
		center.add(empty);										//upper right panel is empty
		center.add(cards);										//lower left panel contains the cards
		center.add(buttons);									//lower right panel contains the buttons
		this.add(center,BorderLayout.CENTER);					//the whole center panel is added to the center of the border layout
	}
	
	//a class that resets the gui
	public void resetView() {
		spin.setEnabled(true);		//spin button becomes active
		newGame.setEnabled(false);	//new game button becomes inactive
		messages.resetMessages();	//messages are reinitialized by calling resetMessages method in messagesPanel class
		cards.resetCards();			//cards are reinitialized by calling resetCards method in cardsPanel class
	}
	
	//a class that updates the cards for every spin
	public void updateCards() {
		for (int i=0;i<3;i++) {					//for loop to update each one of the 3 cards
			cardsArray[i]=model.getCards(i);	//the corresponding element of the array is associated with a card in model object
			cards.updateCard(cardsArray[i],i);	//card object has to be updated. Usage of updateCard class method in cardsPanel class, which is passed an integer number (which represents joker, jack, queen, king or ace) and the place of the element
		}
	}
	
	//a class that updates the messages
	public void updateMessages() {
		int modelBalance=model.getBalance();		//local variable which saves the balance of the model object
		int total=model.getPoints();				//local variable which saves each spin's points that need to be added/subtracted
		messages.updateMessage(total,modelBalance);	//calling of updateMessage method in messagesPanel class to do the updates for messages panel. Method is passed the two local variables
	}
}
