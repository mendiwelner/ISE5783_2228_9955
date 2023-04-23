package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The class Sphere extends the RadialGeometry class
 * and allows us to represent a sphere by radius and a point.
 * 
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com
 *        				Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */
public class Sphere extends RadialGeometry {

	// the center of the sphere
	private final Point center;

	/**
	 * Constructor to initialize sphere with a radius and point
	 * 
	 * @param radius
	 * @param point
	 */
	public Sphere(double radius, Point point) {
		super(radius);
		center = point;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize();
	}

	/**
	 * This function returns the center of the sphere
	 * 
	 * @return center of sphere
	 */
	Point getCenter() {
		return center;
	}
}
