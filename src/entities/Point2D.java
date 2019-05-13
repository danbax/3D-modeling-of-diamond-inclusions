package entities;

public class Point2D {
	private double x;
	private double y;
	private double w;
	
	public Point2D(double x,double y,double w) {
		this.setX(x);
		this.setY(y);
		this.setW(w);
	}
	
	public Point2D(double x,double y) {
		this.setX(x);
		this.setY(y);
	}
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
	
	public void print() {
		System.out.println("x:"+x);
		System.out.println("y:"+y);
	}
}
