package renderer;

import primitives.*;
import static primitives.Util.isZero;

/**
 * Camera class will serve as the ability to catch a scene by sending
 * multiple rays through a view plane to the objects
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com 
 * 		   Mendy Segal. 211769955.Mendysegal490@gmail.com
 *         
 *
 */
public class Camera {
	// starting point of camera
	private Point p0;
	// direction to the center of view plane
	private Vector vTo;
	// the up direction of the camera
	private Vector vUp;
	// the right direction of the camera
	private Vector vRight;
	// the right height of the view plane
	private double height;
	// the width of the view plane
	private double width;
	// the distance of view plane from camera
	private double distance;

	/**
	 * Constructor to create a new camera object with the specified 
	 * vector directions and a starting point
	 * 
	 * @param point p-starting point of camera
	 * @param vector vTo-direction to the center of view plane
	 * @param vector vUp-the up direction of the camera  
	 */
	public Camera(Point p, Vector vTo, Vector vUp) {
		p0 = p;
		if (!isZero(vTo.dotProduct(vUp)))
			throw new IllegalArgumentException("The vectors are not orthogonals");
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		vRight = vTo.crossProduct(vUp).normalize();
	}

	/**
	 * 
	 * @return the height of view plane from camera
	 */
	double getHight() {
		return height;
	}
	
	/**
	 * 
	 * @return the width of view plane from camera
	 */
	double getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return the distance of view plane from camera
	 */
	double getDistance() {
		return distance;
	}
	
	/**
	 * 
	 * @param width of view plane
	 * @param height of view plane
	 * @return a new camera with the specified parameters
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	/**
	 * 
	 * @param distance
	 * @return the distance of view plane from camera
	 */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}
	
	/**
	 * 
	 * @param nX-number of rows in view plane
	 * @param nY-number of columns in view plane
	 * @param j-pixel number of columns
	 * @param i-pixel number of rows
	 * @return a ray passing through a cretin pixel in view plane 
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pc = p0.add(vTo.scale(distance));
		double rY = height / (double)nY;
		double rX = width / (double)nX;
		double xJ = (j - ((double)nX - 1) / 2) * rX;
		double yI = -(i - ((double)nY - 1) / 2) * rY;
		Point pIJ = pc;
		
		if(xJ != 0) {
			pIJ = pIJ.add(vRight.scale(xJ));
		}
		if(yI != 0) {
			pIJ = pIJ.add(vUp.scale(yI));
		}
		
		Vector vIJ = pIJ.subtract(p0);
		return new Ray(p0, vIJ);
	}

}
