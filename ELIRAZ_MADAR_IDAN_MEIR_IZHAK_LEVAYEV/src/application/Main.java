package application;

import controller.Controller;
import model.Store;
import view.ControllerView;

public class Main {
	
	public static void main(String[] args) {
		Store store = new Store();
		Controller controller = new Controller(store);
		new ControllerView(controller);
	}
}
