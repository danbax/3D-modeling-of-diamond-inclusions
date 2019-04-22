package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helper.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import helper.ImagesLoader;

public class MainScreen implements Initializable  {
	@FXML private Text appName;
	@FXML private Text folderText;
	
	@FXML private Button selectImagesFolder;
	Stage thisStage;
	Settings settings;
	String folderPath;
	
		public void selectImagesFolder(ActionEvent event) throws Exception {
			
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(thisStage);

			if(selectedDirectory == null){
			     //No Directory selected
			}else{
					folderPath = selectedDirectory.getAbsolutePath();
			     	folderText.setText(folderPath);
					settings = new Settings();
					settings.setFolderLocation(folderPath);
					String imagesFolderLocation = settings.getFolderLocation();

					
					ImagesLoader imgLoad = new ImagesLoader();
				 	ArrayList<BufferedImage> imagesArray = imgLoad.getImages(imagesFolderLocation);
				 	for(int i=0;i<imagesArray.size();i++){
				 		BufferedImage img = imagesArray.get(i);
				 	}
				 	
			}
			
			
			/*
			GUIcontroller guic = new GUIcontroller();
			guic.loadFxml("MainScreen.fxml");
			*/
		}
		
		public void create3dModel(ActionEvent event) throws Exception {
		}
		
		public void start(Stage primaryStage) throws Exception {
			
			/*
			 * start frame
			 */
			
			Parent root = FXMLLoader.load(getClass().getResource("/main/resources/MainScreen.fxml"));
					
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/main/resources/AppStyle.css").toExternalForm());
			//gui controller
			
			GUIcontroller.setCurrentScene(scene);
			
			folderText = new Text();
			
			thisStage = primaryStage;
			
			//scene.getStylesheets().add(getClass().getResource("/gui/ClientIpSetForm.css").toExternalForm());
			primaryStage.setTitle("Diamonds Modeling");
			primaryStage.setScene(scene);
			
			primaryStage.show();
			

		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
			
		}
	
}
