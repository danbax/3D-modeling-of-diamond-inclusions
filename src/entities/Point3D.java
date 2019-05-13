package entities;

public class Point3D implements Cloneable {
	private double x;
	private double y;
	private double z;
	private double w;
	

			
	
	public Point3D(double x,double y,double z,double w) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setW(w);
	}
	
	public Point3D(int x,int y) {
		this.setX(x);
		this.setY(y);
		this.setZ(0);
		this.setW(0);
	}
	
	public Point3D(double x,double y,double z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setW(0.5);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
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
	
	// converts point from 3D to 2D by currentFframe(number of the image in the set) and parameters from settings
	public Point2D convertPoint3dToPoint2d(double currentFrame) throws CloneNotSupportedException
    {
		  double  scale=0.013046;
		  double  rotationCenter=511.996;
		  double  SImageWidth=1024;
		  double  ImageHeight=1280;
		  double  ImageCount=300;
		  double  angleStep=1.2;
				  
		
        Point2D point2D = new Point2D(0, 0);
        Point3D point3d = (Point3D) this.clone();
        point3d.x /= scale;
        point3d.y /= -1 * scale;
        point3d.z /= -1 * scale;

        double angle = Math.atan2(point3d.y, point3d.x) - Math.PI / 2f;
        double radius = Math.sqrt(point3d.x * point3d.x + point3d.y * point3d.y);
        //point2D.X = (int)(radius * Math.Cos(2 * Math.PI * currentFrame/frames + angle) + rotationCenter);
        double currentFrameAngle = (double)(currentFrame * angleStep / 360f * 2f * Math.PI);
        point2D.setX((int)(radius * Math.cos(currentFrameAngle + angle) + rotationCenter));
        point2D.setY((int)point3d.z);
        
        return point2D;
    }
	
	public void print() {
		System.out.println("x:"+x);
		System.out.println("y:"+y);
		System.out.println("z:"+z);
		System.out.println("w:"+w);
	}
}
