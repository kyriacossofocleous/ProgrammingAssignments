//Kyriacos Sophocleous 2471955s
//a class for the panel of the messages

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class buttonsPanel extends JPanel{		//messages panel is a JPanel
	
	//attributes
	JButton spin,newGame;  //two buttons
	
	//constructor
	public buttonsPanel(JButton spin,JButton newGame) {
		this.spin=spin;									//two buttons are passed from the view method
		this.newGame=newGame;							//the two buttons are linked to the attributes of the class
        this.setLayout(new GridLayout(2,1));			//messages panel is a 2X1 Grid Layout JPanel
        this.setBorder(new EmptyBorder(40,60,40,60));	//the space around the buttons is empty thus, we need to add empty borders
        buttons();										//calling of buttons method to add the buttons to the panel
	}

	//a method that adds buttons to the panel
	public void buttons() {
		this.add(spin);				//addition of spin button
		this.add(newGame);			//addition of new game button
		newGame.setEnabled(false);  //new game is initialized as inactive
	}
}
