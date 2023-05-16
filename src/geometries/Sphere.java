package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * The class Sphere extends the RadialGeometry class and allows us to represent
 * a sphere by radius and a point.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com 
 * 		   Mendy Segal. 211769955.Mendysegal490@gmail.com
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

	@Override
	public List<Point> findIntsersections(Ray ray) {

		Point p0 = ray.getP0();

		// if p0 is the center of the sphere, we return the point on the shell
		if (center.equals(p0)) {
			return List.of(ray.getPoint(radius));
		}

		Vector u = center.subtract(p0);
		double tm = ray.getDir().dotProduct(u);
		if (u.lengthSquared() < tm * tm) {
			return null;
		}

		double d2 = u.lengthSquared() - tm * tm; // d squared
		double th2 = alignZero(radius2 - d2);
		// if d >= radius then there is no intersection
		if (th2 <= 0) {
			return null;
		}

		double th = Math.sqrt(th2);
		double t2 = alignZero(tm + th);
		// if both points are behind the ray head = return nothing
		if (t2 <= 0)
			return null;

		double t1 = alignZero(tm - th);
		return t1 <= 0 ? List.of(ray.getPoint(t2)) //
				: List.of(ray.getPoint(t1), ray.getPoint(t2));
	}

	/**
	 * This function returns the center of the sphere
	 * 
	 * @return center of sphere
	 */
	Point getCenter() {
		return center;
	}

	/**
	 * This function returns the radius of the sphere
	 * 
	 * @return radius of sphere
	 */
	double getRadius() {
		return radius;
	}
}
