package geometries;

import java.util.List;

import primitives.*;

/**
 * The class Cylinder is the extension of Tube. but unlike Tube, Cylinder is
 * finite Tube
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */

public class Cylinder extends Tube {

	// the height of the Cylinder
	private final double height;

	/**
	 * Constructor to initialize Cylinder
	 * 
	 * @param radius  of the shape
	 * @param axisRay of the shape
	 * @param height  of the shape
	 */
	public Cylinder(double radius, Ray axisRay, double height) { // is it should be Package Friendly
		super(radius, axisRay);
		if (height <= 0)
			throw new IllegalArgumentException("The height of a cylinder must be greater then 0");
		this.height = height;
	}

	/**
	 * This function returns the height of the shape
	 * 
	 * @return the height
	 */
	double getHeight() {
		return height;
	}

	/**
	 * This function calculates the normal of the Cylinder at a certain point
	 * 
	 * @param point at the shell of the Cylinder
	 */
	public Vector getNormal(Point point) {
		if (isBetweenCaps(point))
			return super.getNormal(point);

		Vector v = axisRay.getDir();
		Point p0 = axisRay.getP0();

		if (point.equals(p0) || v.dotProduct(point.subtract(p0)) == 0) {
			return v.scale(-1);
		}
		return v;
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray)  {
		return null;
	}

	/**
	 * This function returns True if the point is between the bases, Otherwise it
	 * returns False.
	 * 
	 * @param point
	 * @return boolean value
	 */
	private boolean isBetweenCaps(Point p) {
		Vector n = axisRay.getDir();
		Point p0 = axisRay.getP0();
		Point topPoint = axisRay.getPoint(height);

		if (p.equals(p0) || p.equals(topPoint))
			return false;

		return n.dotProduct(p.subtract(p0)) > 0 && n.dotProduct(p.subtract(topPoint)) < 0;
	}
}
