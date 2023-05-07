package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * The class Plane will serve as the representation of the shape plane created
 * with 3 points or with point and vector.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal. 211769955.
 *         Mendysegal490@gmail.com
 */
public class Plane implements Geometry {

	// the starting point in plane
	private final Point point;

	// normal to the plane
	private final Vector normal;

	/**
	 * Constructor to initialize Plane with a point and vector
	 * 
	 * @param point
	 * @param vector normal
	 */
	public Plane(Point point, Vector normal) {
		this.point = point;
		this.normal = normal.normalize();
	}

	/**
	 * Constructor to initialize Plane with 3 points
	 * 
	 * @param p1 for plane creation
	 * @param p2 for plane creation
	 * @param p3 for plane creation
	 */
	public Plane(Point p1, Point p2, Point p3) {
		point = p1;
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		normal = v1.crossProduct(v2).normalize();
	}

	/**
	 * 
	 * @return the normal to the plane
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point p) {
		return normal;
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {

		// if the starting point of plane equals to the start of ray, no intersection
		if (point.equals(ray.getP0()))
			return null;

		// if t == 0, no intersection
		double nQMinusP0 = normal.dotProduct(point.subtract(ray.getP0()));
		if (isZero(nQMinusP0)) {
			return null;
		}

		// we cannot divide by zero
		double nv = normal.dotProduct(ray.getDir());
		if (isZero(nv)) {
			return null;
		}

		// t has to be greater then 0 to avoid zero vector
		double t = alignZero(nQMinusP0 / nv);
		return t <= 0 ? null : List.of(ray.getPoint(t));
	}
}
