package entities;

import java.util.ArrayList;

public class PointsCloud {
	private ArrayList<Point> pointsArray;
	
	public PointsCloud(ArrayList<Point> pointsArray) {
		this.pointsArray = pointsArray;
	}
	
	public void addPoint(Point point) {
		pointsArray.add(point);
	}
}
