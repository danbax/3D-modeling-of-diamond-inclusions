package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helper.Settings;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import helper.ImagesLoader;
import gui.GUIcontroller;

public class Results extends GUIcontroller  {
	@FXML private ImageView diamondImage;
	
	@FXML private Button selectImagesFolder;
	Stage thisStage;
	Settings settings;
	String folderPath;
	
		
		
		public void start(Stage primaryStage) throws Exception {
			
			
			Parent root = FXMLLoader.load(getClass().getResource("/main/resources/Results.fxml"));
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/main/resources/AppStyle.css").toExternalForm());
			//gui controller
			
			GUIcontroller.setCurrentScene(scene);
			
			thisStage = primaryStage;
			diamondImage = new ImageView();
			
			//scene.getStylesheets().add(getClass().getResource("/gui/ClientIpSetForm.css").toExternalForm());
			primaryStage.setTitle("Results");
			primaryStage.setScene(scene);
			
			primaryStage.show();
			//Image image = SwingFXUtils.toFXImage(imagesArray.get(0), null);
			//diamondImage.setImage(image);
			
			
			System.out.println("sizeY");
			

		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
			/*
			 * start frame
			 */
			
			/*
			ArrayList<BufferedImage> imagesArray = helper.ImagesLoader.imagesArray;
		 	System.out.println("sizeX"+ imagesArray.size());
		 	for(int i=0;i<imagesArray.size();i++){
		 		BufferedImage img = imagesArray.get(i);
		 		if(img!=null) {
			 		Image card = SwingFXUtils.toFXImage(img, null );
			 		//if(card != null)
			 		//diamondImage.setImage(card);
		 		}
		 		

                //System.out.println("image: " + f.getName());
                //System.out.println(" width : " + img.getWidth());
                //System.out.println(" height: " + img.getHeight());
                //System.out.println(" size  : " + f.length());
		 	}
		 	*/

		 	
			
		}
	
}
