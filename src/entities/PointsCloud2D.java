package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class PointsCloud2D {
	public ArrayList<Point2D> pointsArray;
	
	public PointsCloud2D(ArrayList<Point2D> pointsArray) {
		this.pointsArray = pointsArray;
	}
	
	public int getSize() {
		return this.pointsArray.size();
	}
	
	public PointsCloud2D() {
		this.pointsArray = new ArrayList<Point2D>();
	}
	
	public void addPoint(Point2D point) {
		pointsArray.add(point);
	}
	
	// prints all the points of the point cloud
	public void print() {
	  Iterator<Point2D> i = pointsArray.iterator();
	  Point2D p = new Point2D(0,0,0);
	      System.out.println("The ArrayList elements are:");
	      while (i.hasNext()) {
	    	  p=(Point2D) i.next();
	    	  p.print();
	      }
	}
	
	// search for point in the point cloud
	public boolean searchForPoint(Point2D point2D) {
		Iterator i = pointsArray.iterator();
		  Point2D p = new Point2D(0,0,0);
		      while (i.hasNext()) {
		    	  p=(Point2D) i.next();
		    	  if(p.getX() == point2D.getX() && p.getY() == point2D.getY()) {
		    		  return true;
		    	  }
		      }
			return false;
	}
	

}
