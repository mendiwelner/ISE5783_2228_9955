package renderer;

import primitives.*;
import static primitives.Util.isZero;
import java.util.MissingResourceException;

/**
 * Camera class will serve as the ability to catch a scene by sending multiple
 * rays through a view plane to the objects
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 * 
 *
 */
public class Camera {
	// starting point of camera
	private final Point p0;
	// direction to the center of view plane
	private final Vector vTo;
	// the up direction of the camera
	private final Vector vUp;
	// the right direction of the camera
	private final Vector vRight;
	// the right height of the view plane
	private double height;
	// the width of the view plane
	private double width;
	// the distance of view plane from camera
	private double distance;
	// the image of the scene
	private ImageWriter imageWriter;
	// the tracer of the rays
	private RayTracerBase rayTracerBase;

	/**
	 * Constructor to create a new camera object with the specified vector
	 * directions and a starting point
	 * 
	 * @param p   point starting point of camera
	 * @param vTo vector direction to the center of view plane
	 * @param vUp vector the up direction of the camera
	 */
	public Camera(Point p, Vector vTo, Vector vUp) {
		if (!isZero(vTo.dotProduct(vUp)))
			throw new IllegalArgumentException("The vectors are not orthogonals");

		p0 = p;
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		vRight = vTo.crossProduct(vUp).normalize();
	}

	/**
	 * This function returns the height of view plane
	 * 
	 * @return height
	 */
	double getHeight() {
		return height;
	}

	/**
	 * 
	 * This function returns the width of view plane
	 * 
	 * @return width
	 */
	double getWidth() {
		return width;
	}

	/**
	 * * This function returns the distance of view plane
	 * 
	 * @return distance
	 */
	double getDistance() {
		return distance;
	}

	/**
	 * * This function sets the size of view plane
	 * 
	 * @param width  of view plane
	 * @param height of view plane
	 * @return a new camera with the specified parameters
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * This function sets the distance of view plane
	 * 
	 * @param distance from the camera
	 * @return a new camera with the specified parameter
	 */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * This function sets the Image Writer of scene
	 * 
	 * @param imageWriter creates the png file with the scene
	 * @return camera the camera itself
	 */
	public Camera setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * This function sets the ray tracer for tracing the rays
	 * 
	 * @param rayTracerBase-the tracer of rays
	 * @return camera-the camera itself
	 */
	public Camera setRayTracer(RayTracerBase rayTracerBase) {
		this.rayTracerBase = rayTracerBase;
		return this;
	}

	/**
	 * This function returns ray through a pixel in view plane
	 * 
	 * @param nX-number of rows in view plane
	 * @param nY-number of columns in view plane
	 * @param j-pixel   number of columns
	 * @param i-pixel   number of rows
	 * @return a ray passing through a cretin pixel in view plane
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pc = p0.add(vTo.scale(distance));
		double rY = height / (double) nY;
		double rX = width / (double) nX;
		double xJ = (j - (nX - 1) / 2.0) * rX;
		double yI = -(i - (nY - 1) / 2.0) * rY;
		Point pIJ = pc;

		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(yI));

		Vector vIJ = pIJ.subtract(p0);
		return new Ray(p0, vIJ);
	}

	/**
	 * This function creates the image of the scene using the imageWriter class
	 * @return this camera
	 */
	public Camera renderImage() {
		if (p0 == null || vTo == null || vUp == null || imageWriter == null || rayTracerBase == null)
			throw new MissingResourceException("Required resources are missing.", "Resource", null);
		for (int j = 0; j < imageWriter.getNx(); j++) {
			for (int i = 0; i < imageWriter.getNy(); i++) {
				imageWriter.writePixel(j, i, castRay(imageWriter.getNx(), imageWriter.getNy(), j, i));
			}
		}
		imageWriter.writeToImage();
		return this;
	}

	/**
	 * This function prints the grid of image after drawing it by a color
	 * 
	 * @param interval-amount of pixel skipping before painting on grid
	 * @param color-the       color we paint on the grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("Required resources are missing.", "Resource", null);
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		for (int j = 0; j < nX; j++) {
			for (int i = 0; i < nY; i++) {
				if (j % interval == 0 || i % interval == 0)
					this.imageWriter.writePixel(j, i, color);
			}
		}
		imageWriter.writeToImage();
	}

	/**
	 * This function delegating to writeToImage function that produce png file of
	 * the image
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Required resources are missing.", "Resource", null);
		imageWriter.writeToImage();
	}

	/**
	 * This function helps us to calculate the color of pixel
	 * 
	 * @param nX-rows    of view plane
	 * @param nY-columns of view plane
	 * @param j-rows     indexer
	 * @param i-columns  indexer
	 * @return color of a point in the pixel grid
	 */
	private Color castRay(int nX, int nY, int j, int i) {
		Ray ray = constructRay(nX, nY, j, i);
		return rayTracerBase.traceRay(ray);
	}

}
