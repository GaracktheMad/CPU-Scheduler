package application;

import controller.ViewController;
import javafx.application.Application;

/**
 * A main method to launch the view controller application. 
 * @author Peter Vukas
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Application.launch(ViewController.class, args);
	}

}
