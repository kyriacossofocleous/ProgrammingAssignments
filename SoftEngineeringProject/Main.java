
public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Model model = new Model();
		Controller controller = new Controller(model);
		controller.userDeference();		
	}
}