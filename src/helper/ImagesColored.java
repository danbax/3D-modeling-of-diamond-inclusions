package helper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import entities.Point2D;
import entities.PointsCloud2D;
import entities.PointsCloud3D;
import java.awt.Color;

/*
 * This object contains a set of the diamond images with coloured pixels on the inclusions 3D points cloud
 */
public class ImagesColored {
	public static ArrayList<BufferedImage> imagesArray = new ArrayList<BufferedImage>();

	// draw pixels of 3D cloud point on each image in set. 
	public ImagesColored(PointsCloud3D pointsCloud3D) throws CloneNotSupportedException, NumberFormatException, IOException, JSONException {
		ArrayList<BufferedImage> imagesArray = helper.ImagesLoader.imagesArray;
		PointsCloud2D p2d = new PointsCloud2D();
		// for each image
	 	for(int i=0;i<imagesArray.size();i++){
		 	p2d = pointsCloud3D.create2DCloud(i);
	 		BufferedImage image = imagesArray.get(i);
	 		
	 		Color mc = Color.blue;
	 		
	 		BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
	 	      for(int xx = 0; xx < image.getWidth(); xx++) {
	 	         for(int yy = 0; yy < image.getHeight(); yy++) {
	 	               copy.setRGB(xx, yy, image.getRGB(xx,yy));
	 	         }
	 	      }
	 		ArrayList<Point2D> pointsArray2D = p2d.pointsArray;
	 		for(Point2D point2D: pointsArray2D) {
	 			int x = (int) point2D.getX();
	 			int y = (int) point2D.getY();
	 			copy.setRGB(x, y, mc.getRGB());
	        }
	 		ImagesColored.imagesArray.add(copy);
	        }
	 	}
	}
    
