//Kyriacos Sophocelous 2471955s
//This is the main class of the program

public class FruitMachine {
	public static void main(String[] args) {
		FruitMachineModel model=new FruitMachineModel();						//model object
		FruitMachineController controller=new FruitMachineController(model);	//controller object needs to know about model
		FruitMachineView gui=new FruitMachineView(model,controller);			//view object needs to know about model and controller
		controller.setView(gui);												//controller also needs to know about view, so we first create the the view object(which also needs to know about the controller) and the we pass it to a special method in controller's class
		gui.setVisible(true);													//gui is set visible
	}
}
