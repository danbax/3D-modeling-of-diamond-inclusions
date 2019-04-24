package client;

import gui.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * Loading the Main screen
 */
public class Client extends Application {
	
	private static MainScreen mainScreen;
		
	public static void main( String args[] ) throws Exception
	   { 
        launch(args);		
	  } // end main
	
	@Override
	public void start(Stage arg0) throws Exception {
		mainScreen = new MainScreen(); // create StudentFrame
		mainScreen.start(arg0); 
	}

	
}
