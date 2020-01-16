//Kyriacos Sophocleous 2471955s
//This is the controller class of the fruit machine
//the controller listens for user interaction events

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FruitMachineController implements ActionListener {
	
	//attributes
	FruitMachineModel model;	//a model object
	FruitMachineView view;		//a view object
	
	//constructor
	public FruitMachineController(FruitMachineModel model) {
		this.model=model;	//a model object is passed from the main method and it is linked to the class attribute
	}
	
	//a method to link the view object of the main method to the one of the controller
	public void setView(FruitMachineView view) {	//controller needs to know about the view and view need to know about the controller
		this.view=view;								//main passes the view object to the controller object by using this method
	}
	
	//actionPerformed method
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==view.spin) {				//if the spin button is pressed
			model.spin();							//spin method of model object is called
			model.nextMove();						//nextMove method of model object is called
			view.updateCards();						//updateCards method of view object is called
			view.updateMessages();					//updateMessages method of view object is called
			if (model.getBalance()>=150) {			//if balance is equal or greater than 150
				view.winlose.setText("You win!");	//player wins and the appropriate message is shown
				view.newGame.setEnabled(true);		//new game button is active
				view.spin.setEnabled(false);		//spin button is inactive
			}else if(model.getBalance()<0) {		//if balance is less than 0
				view.winlose.setText("You lose");	//player looses and the appropriate message is shown
				view.newGame.setEnabled(true);		//new game buttons is active
				view.spin.setEnabled(false);		//spin button is inactive
				}
		}else if(e.getSource()==view.newGame) {		//if new game button is pressed
			model.resetGame();						//model is reinitialized
			view.resetView();						//view is reinitialized
		}
	}
}
