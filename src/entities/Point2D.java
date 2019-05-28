package entities;

public class Point2D {
	private float x;
	private float y;
	private float w;
	
	public Point2D(float x,float y,float w) {
		this.setX(x);
		this.setY(y);
		this.setW(w);
	}
	
	public Point2D(float x,float y) {
		this.setX(x);
		this.setY(y);
	}
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
	
	public void print() {
		System.out.println("x:"+x);
		System.out.println("y:"+y);
	}
}
