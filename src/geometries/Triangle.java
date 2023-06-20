package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.alignZero;

/**
 * The class Triangle extends the Polygon class and allows us to represent a
 * Triangle by 3 points.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal. 211769955.
 *         Mendysegal490@gmail.com
 */
public class Triangle extends Polygon {

	/**
	 * Constructor to initialize Triangle with 3 points
	 * 
	 * @param p1 1st vertex
	 * @param p2 2nd vertex
	 * @param p3 3rd vertex
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){ 
		var intersections = plane.findGeoIntersections(ray);
		if (intersections == null) return null;
		
		Point p0 = ray.getP0();
		Vector dir = ray.getDir();

		Vector v1 = vertices.get(0).subtract(p0);
		Vector v2 = vertices.get(1).subtract(p0);
		Vector n1 = v1.crossProduct(v2).normalize();
		double res1 = alignZero(dir.dotProduct(n1));
		if (res1 == 0)
			return null;

		Vector v3 = vertices.get(2).subtract(p0);
		Vector n2 = v2.crossProduct(v3).normalize();
		double res2 = alignZero(dir.dotProduct(n2));
		if (res1 * res2 <= 0)
			return null;

		Vector n3 = v3.crossProduct(v1).normalize();
		double res3 = alignZero(dir.dotProduct(n3));
		if (res1 * res3 <= 0)
			return null;
		
		intersections.get(0).geometry = this;
		return intersections;
	}
}
