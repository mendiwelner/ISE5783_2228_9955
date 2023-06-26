package primitives;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.*;

import java.util.List;

/**
 * Ray class will serve as the ray of a general shape to build the shape later
 * on.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 * 
 */
public class Ray {

	private final Point p0;
	private final Vector dir;
	/** DELTA value to move the point away from original point */
	private static final double DELTA = 0.1;

	/**
	 * Constructor to create a new Ray object with the specified starting point and
	 * direction. The direction vector is normalized to have a length of 1.
	 * 
	 * @param p   the starting point of the Ray
	 * @param vec the direction vector of the Ray
	 */
	public Ray(Point p, Vector vec) {
		p0 = p;
		dir = vec.normalize();
	}

	/**
	 * Constructor for ray deflected by DELTA
	 *
	 * @param p   origin
	 * @param n   normal vector
	 * @param dir direction
	 */
	public Ray(Point p, Vector n, Vector dir) {
		this.dir = dir.normalize();
		double nv = alignZero(n.dotProduct(this.dir));
		this.p0 = nv == 0 ? p : p.add(n.scale(nv < 0 ? -DELTA : DELTA));
	}

	/**
	 * This function returns the starting point of the the ray which is p0
	 * 
	 * @return point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * This function returns a point p0 on the central base and we're adding to it
	 * the direction vector scaled by t. if t equals to 0 then we just return p0.
	 * 
	 * @param t is a scalar
	 * @return point
	 */
	public Point getPoint(double t) {
		return isZero(t) ? p0 : p0.add(dir.scale(t));
	}

	/**
	 * This function returns the direction vector normalized
	 * 
	 * @return direction vector of the axis ray
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * This function returns the closest point from list of points, to the point of
	 * the ray
	 * 
	 * @param points is a list of points
	 * @return the closest point
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}

	/**
	 * This function returns the findClosest GeoPoint from list of points, to the
	 * point of the ray
	 * 
	 * @param points is a list of points
	 * @return the closest point
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
		if (points == null)
			return null;
		
		GeoPoint closestGeoPoint = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for (GeoPoint point : points) {
			double distance = point.point.distance(p0);
			if (distance < minDistance) {
				closestGeoPoint = point;
				minDistance = distance;
			}
		}
		return closestGeoPoint;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Ray r)
			return this.p0.equals(r.p0) && this.dir.equals(r.dir);
		return false;
	}

	@Override
	public String toString() {
		return p0 + "->" + dir;
	}

}
