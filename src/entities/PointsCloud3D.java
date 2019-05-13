package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class PointsCloud3D {
	private ArrayList<Point3D> pointsArray;
	
	public PointsCloud3D(ArrayList<Point3D> pointsArray) {
		this.pointsArray = pointsArray;
	}
	
	public int getSize() {
		return this.pointsArray.size();
	}
	
	public PointsCloud3D() {
		this.pointsArray = new ArrayList<Point3D>();
	}
	
	// adds point to the points cloud
	public void addPoint(Point3D point) {
		pointsArray.add(point);
	}
	
	// prints the 3D points cloud
	public void print() {
	  Iterator<Point3D> i = pointsArray.iterator();
	  Point3D p = new Point3D(0,0,0,0);
	      System.out.println("The ArrayList elements are:");
	      while (i.hasNext()) {
	    	  p=(Point3D) i.next();
	    	  p.print();
	      }
	}
	
	// create 2D points cloud from 3D points Cloud by currentFrame(ImageNumber)
	public PointsCloud2D create2DCloud(double currentFrame) throws CloneNotSupportedException {
		PointsCloud2D pointCloud2D = new PointsCloud2D();
		
		for(Point3D point3D: this.pointsArray) {
			Point2D point2D = new Point2D(0,0);
			point2D = point3D.convertPoint3dToPoint2d(currentFrame);
			pointCloud2D.addPoint(point2D);
        }
	      return pointCloud2D;
	}
	

}
