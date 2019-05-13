package gui;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helper.Settings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import gui.GUIcontroller;

public class Results extends GUIcontroller  {
	@FXML private ImageView diamondImage = new ImageView();
	@FXML private Slider sliderImages = new Slider();
	@FXML private Text AppName;
	@FXML private CheckBox showMask;
	
	Stage thisStage;
	Settings settings;
	String folderPath;
	
	public void create3dModel(ActionEvent event) throws Exception {
		GUIcontroller guic = new GUIcontroller();
		guic.loadFxml("MainScreen.fxml");
	}
	
	public void goBack(ActionEvent event) throws Exception {
		GUIcontroller guic = new GUIcontroller();
		guic.loadFxml("MainScreen.fxml");
	}
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
			showMask.setSelected(true);
			
			showMask.selectedProperty().addListener(new ChangeListener<Boolean>() {
			    @Override
			    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			    	ArrayList<BufferedImage> images = helper.ImagesColored.imagesArray;
			    	if(showMask.isSelected()) {
		 				images = helper.ImagesColored.imagesArray;
		 			}
		 			else {
		 				images = helper.ImagesLoader.imagesArray;
		 			}
			    	
			    	Image card = SwingFXUtils.toFXImage(images.get(0), null );
					diamondImage.setImage(card);
			    }
			});
			
			ArrayList<BufferedImage> imagesArray = helper.ImagesColored.imagesArray;
			Image card = SwingFXUtils.toFXImage(imagesArray.get(0), null );
			diamondImage.setImage(card);

		 	sliderImages.setMin(0);
		 	sliderImages.setMax(imagesArray.size());
		 	// setting the image view to the right image
		 	sliderImages.valueProperty().addListener((observable, oldValue, newValue) -> {
		 		int value = newValue.intValue();
		 		{
	 				ArrayList<BufferedImage> images = helper.ImagesColored.imagesArray;
		 			if(showMask.isSelected()) {
		 				images = helper.ImagesColored.imagesArray;
		 			}
		 			else {
		 				images = helper.ImagesLoader.imagesArray;
		 			}
		 			
			 		if(images.get(value) != null) {
						Image imgColored = SwingFXUtils.toFXImage(images.get(value), null );
				 		//Image card = SwingFXUtils.toFXImage(img, null );
				 		diamondImage.setImage(imgColored);
				 		
				        }
			 		}
	        });
		 	
		 	

		 	

		 	
			
		}
	
}
