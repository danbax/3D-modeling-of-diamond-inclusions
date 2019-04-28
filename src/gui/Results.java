package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import helper.ImagesLoader;
import gui.GUIcontroller;

public class Results extends GUIcontroller  {
	@FXML private ImageView diamondImage = new ImageView();
	@FXML private Slider sliderImages;
	@FXML private Text AppName;
	
	
	Stage thisStage;
	Settings settings;
	String folderPath;
	
	public void create3dModel(ActionEvent event) throws Exception {
		GUIcontroller guic = new GUIcontroller();
		guic.loadFxml("MainScreen.fxml");
	}
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
			/*
			 * start frame
			 */
			/*
	 		diamondImage = new ImageView();
	 		diamondImage.setStyle("-fx-background-color: BLACK");
	 		sliderImages = new Slider();
	 		sliderImages.setValue(40);
	 		AppName = new Text("XXX");
	 		AppName.setText("xxxx");
	 		*/
			
			ArrayList<BufferedImage> imagesArray = helper.ImagesLoader.imagesArray;
		 	for(int i=0;i<imagesArray.size();i++){
		 	
		 		         
		 		//Loading image from URL 
		 		//Image image = new Image(new FileInputStream("url for the image));

		 		
		 		BufferedImage img = imagesArray.get(i);
		 		Image card = SwingFXUtils.toFXImage(img, null );
		 		diamondImage.setImage(card);
		 		
		 		
		 		System.out.println(card.getPixelReader());
		 		System.out.println(card.getHeight());
		 		
		 	}
		 	

		 	
			
		}
	
}
