package geometries;

import java.util.List;
import primitives.Ray;
import primitives.Point;

/**
 * This abstract class handle in the intersections between the geometries
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 *
 */
public abstract class Intersectable {
	/**
	 * This function returns a list of intersection points between ray and general
	 * geometry
	 * 
	 * @param ray the ray to intersect with
	 * @return List of intersection points
	 */
	public abstract List<Point> findIntersections(Ray ray);
	
	/**
	 * This class...
	 */
	public static class GeoPoint {
		 public Geometry geometry;
		 public Point point;
		 /**
		 * constructor for the class GeoPoint
		 * @param geometry for the geometry
		 * @param point for the point
		 */
		 GeoPoint(Geometry geometry, Point point)
		 {
			 this.geometry = geometry;
			 this.point = point;
		 }
		 
	}
}
