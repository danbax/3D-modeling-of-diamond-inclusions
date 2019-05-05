package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIcontroller extends Application implements Initializable {
	private static Scene currentScene;
	private  Stage primaryStage;
	private  static Stage currentStage;
	private  FXMLLoader loader;
	private  Pane root;
	
	public GUIcontroller(){
		
	}
	
	/*
	 * Loads FXML scene
	 */
	public void loadFxml(String fxmlFile) throws IOException
	{
		// For avoiding bugs
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try {
					
					currentScene.getWindow().hide(); //hiding primary window
					primaryStage = new Stage();
					//primaryStage.initStyle(StageStyle.UNDECORATED); // remove close button
					GUIcontroller.setCurrentStage(primaryStage);
					loader = new FXMLLoader();
					root = loader.load(getClass().getResource("/main/resources/"+fxmlFile).openStream());
					Scene scene = new Scene(root);	
					scene.getStylesheets().add(getClass().getResource("/main/resources/AppStyle.css").toExternalForm());
					GUIcontroller.currentScene = scene;
					primaryStage.setScene(scene); 	
					//primaryStage.setResizable(false);
					primaryStage.setTitle(fxmlFile);
					primaryStage.setMaximized(true);
					primaryStage.show(); 
					
				} catch (IOException e) {
					// show problems
					e.printStackTrace();
				}
			}
			
			});
		
	}

	
	

	public static Scene getCurrentScene() {
		return currentScene;
	}

	public static void setCurrentScene(Scene currentScene) {
		GUIcontroller.currentScene = currentScene;
	}

	public static Stage getCurrentStage() {
		return currentStage;
	}

	public static void setCurrentStage(Stage currentStage) {
		GUIcontroller.currentStage = currentStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
