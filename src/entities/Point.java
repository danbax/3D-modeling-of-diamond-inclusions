package entities;

public class Point {
	private int x;
	private int y;
	private int z;
	private double w;
	
	public Point(int x,int y,int z,double w) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setW(w);
	}
	
	public Point(int x,int y,int z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setW(0.5);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
}
