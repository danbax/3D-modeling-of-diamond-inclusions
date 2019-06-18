package helper;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import entities.Point2D;
import entities.PointsCloud2D;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BufferedImagesColored {
	public static ArrayList<Image> imagesArray = new ArrayList<Image>();

    // File representing the folder that you select using a FileChooser
	
	public BufferedImagesColored(PointsCloud2D pointsCloud2D) {
		ArrayList<BufferedImage> imagesArray = helper.ImagesLoader.imagesArray;
	 	for(int i=0;i<imagesArray.size();i++){
	 	
	 		BufferedImage img = imagesArray.get(i);
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
	                Color red = Color.RED;
	                
	                // Now write a brighter color to the PixelWriter.
	                Point2D pixel2D = new Point2D(readX,readY);
	                if(pointsCloud2D.searchForPoint(pixel2D)) {
	                	//System.out.println("x:"+readX+", y: "+readY);
	                	pixelWriter.setColor(readX,readY,red);
	                }
	                else {
	                	pixelWriter.setColor(readX,readY,color);
	                }
	            }
	        }
	        BufferedImagesColored.imagesArray.add(wImage);
	        }
	 		
	 	}
	}
    
