package entities;

import java.io.IOException;

import org.json.JSONException;

import helper.Settings;

public class Point3D implements Cloneable {
	private float x;
	private float y;
	private float z;
	private float w;
	

			
	
	public Point3D(float x,float y,float z,float w) {
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
	
	public Point3D(float x,float y,float z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setW((float) 0.5);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
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
	
	/*********************************************************************************************************
	 *  converts point from 3D to 2D by currentFframe(number of the image in the set) and parameters from settings
	*********************************************************************************************************/
	public Point2D convertPoint3dToPoint2d(double currentFrame) throws CloneNotSupportedException, IOException, NumberFormatException, JSONException
    {
		// get settings
		Settings settings = new Settings();
		float  scale= Float.parseFloat(settings.getScale());
		float  rotationCenter= Float.parseFloat(settings.getRotationCenter());
		float  angleStep= Float.parseFloat(settings.getAngleStep());
		float  SImageWidth= Integer.parseInt(settings.getImageWidth());
		float  ImageHeight= Integer.parseInt(settings.getImageHeight());
		float  ImageCount= Integer.parseInt(settings.getImageCount());
				  
		
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
