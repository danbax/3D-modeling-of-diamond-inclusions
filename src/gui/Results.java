package gui;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helper.Settings;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import gui.GUIcontroller;

public class Results extends GUIcontroller  {
	@FXML private ImageView diamondImage = new ImageView();
	@FXML private Slider sliderImages = new Slider();
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
		
			// showing images in image view
			ArrayList<BufferedImage> imagesArray = helper.ImagesLoader.imagesArray;
		 	for(int i=0;i<imagesArray.size();i++){
		 	
		 		BufferedImage img = imagesArray.get(i);
		 		Image card = SwingFXUtils.toFXImage(img, null );
		 		diamondImage.setImage(card);
		 		
		 	}

		 	sliderImages.setMin(0);
		 	sliderImages.setMax(imagesArray.size());
		 	// setting the image view to the right image
		 	sliderImages.valueProperty().addListener((observable, oldValue, newValue) -> {
		 		int value = newValue.intValue();
		 		{
			 		if(imagesArray.get(value) != null) {
				 		BufferedImage img = imagesArray.get(value);
				 		Image card = SwingFXUtils.toFXImage(img, null );
				 		
				 		 PixelReader pixelReader = card.getPixelReader();
				 		 WritableImage wImage = new WritableImage(
				                 (int)card.getWidth(),
				                 (int)card.getHeight());
				         PixelWriter pixelWriter = wImage.getPixelWriter();
				       
				        // Determine the color of each pixel in a specified row
				        for(int readY=0;readY<card.getHeight();readY++){
				            for(int readX=0; readX<card.getWidth();readX++){
				                Color color = pixelReader.getColor(readX,readY);
				                
				                // Now write a brighter color to the PixelWriter.
				                if(readX==readY || readX == 5 || readY==80 || (readX>30 && readX<60 && readY>40 && readY<80)) {
				                	color = color.brighter();
				                }
				                pixelWriter.setColor(readX,readY,color);
				            }
				        }
				 		
				 		diamondImage.setImage(wImage);
			 		}
			 		
		 		}
	        });
		 	
		 	

		 	

		 	
			
		}
	
}
