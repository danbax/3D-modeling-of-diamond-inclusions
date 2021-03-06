package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONException;

import helper.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import helper.ImagesLoader;

public class MainScreen extends GUIcontroller implements Initializable  {
	@FXML private Text AppName = new Text();
	@FXML private Text folderText = new Text();
	@FXML private Text NumberOfImages = new Text();
	
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
			     	
			     	// save images folder location to settings.json
					settings = new Settings();
					settings.setFolderLocation(folderPath);
					String imagesFolderLocation = settings.getFolderLocation();
					
					// Adjust name length for text field
					if(folderPath.length() >23)
						folderPath = folderPath.substring(0,23)+"...";
			     	folderText.setText(folderPath);
			     	
			     	
			     	laodImagesFromFolder(folderPath);
			}
			
		
		}
		
		public void create3dModel(ActionEvent event) throws Exception {
			System.out.println(ImagesLoader.imagesArray.size());
			if(ImagesLoader.imagesArray.size()==0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				alert.setTitle("שגיאה");
				alert.setHeaderText("לא נמצאו תמונות");
				alert.setContentText("עליך לבחור תיקיית תמונות!");

				alert.showAndWait();
			}else {
			
				GUIcontroller guic = new GUIcontroller();
				guic.loadFxml("Results.fxml");
			}
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
			
			thisStage = primaryStage;
			
			primaryStage.setTitle("Diamonds Modeling");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			

			primaryStage.show();
			

		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	

			Settings settings = null;
			String folderLocation = null;
			try {
				settings = new Settings();
				folderLocation = settings.getFolderLocation();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(folderLocation != null) {
				folderText.setText(folderLocation);
				laodImagesFromFolder(folderLocation);
			}
			
	        
	        
			
		}
		
		private void laodImagesFromFolder(String imagesFolderLocation) {
			// Loading images from selected folder to static arrayList varaiable
			ImagesLoader imgLoad = new ImagesLoader();
		 	ArrayList<BufferedImage> imagesArray = imgLoad.getImages(imagesFolderLocation);
		 	if(imagesArray.size()==0) {
		 		NumberOfImages.setFill(Color.RED);
			 	NumberOfImages.setText("לא נמצאו תמונות בתיקייה");
		 	}else {
			 	NumberOfImages.setFill(Color.GREEN);
			 	NumberOfImages.setText("נמצאו בתיקייה "+ imagesArray.size()+ " תמונות");
		 	}
		}

	
}
