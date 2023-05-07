package renderer;

import primitives.*;
import static primitives.Util.isZero;

public class Camera {
	private Point point;
	private Vector vTo;
	private Vector vUp;
	private Vector vRight;
	private double height;
	private double width;
	private double distance;
	
	public Camera(Point p, Vector vTo, Vector vUp)
	{
		point = p;
		if (!isZero(vTo.dotProduct(vUp)))
			throw new IllegalArgumentException("The vectors are not orthogonals");
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		vRight = vTo.crossProduct(vUp).normalize();
	}
	
	double getHight()
	{
		return height;
	}
	double getWidth()
	{
		return width;
	}
	double getDistance()
	{
		return distance;
	}
	
	public Camera setVPSize(double width, double height)
	{
		this.width = width;
		this.height = height;
		return this;
	}
	
	public Camera setVPDistance(double distance)
	{
		this.distance = distance;
		return this;
	}
	
	public Ray constructRay(int nX, int nY, int j, int i)
	{
		return null;
	}
	
	
	
}
