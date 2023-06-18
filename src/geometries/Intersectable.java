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
	public final List<Point> findIntersections(Ray ray) {
		List<GeoPoint> geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * This function returns a list of intersection points between ray and general
	 * geometry
	 * 
	 * @param ray the ray to intersect with
	 * @return List of intersection points
	 */
	public final List<Point> findIntersections(Ray ray, double distance) {
		List<GeoPoint> geoList = findGeoIntersections(ray);

		return geoList == null ? null
				: geoList.stream().filter(gp -> ray.getP0().distance(gp.point) < distance).map(gp -> gp.point).toList();
	}

	/**
	 * This function returns a list of intersection points between ray and general
	 * by calling findGeoIntersectionsHelper
	 * 
	 * @param ray the ray to intersect with
	 * @return List of intersection GeoPoint
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray);
	}

	/**
	 * This function returns a list of intersection points between ray and general
	 * 
	 * @param ray the ray to intersect with
	 * @return List of intersection GeoPoint
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

	/**
	 * This class...
	 */
	public static class GeoPoint {
		/** the geometry part of the GeoPoint */
		public Geometry geometry;
		/** the point part of the GeoPoint */
		public Point point;

		/**
		 * constructor for the class GeoPoint
		 * 
		 * @param geometry for the geometry
		 * @param point    for the point
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj instanceof GeoPoint g)
				return geometry == g.geometry && point.equals(g.point);
			return false;
		}

		@Override
		public String toString() {
			return geometry + " " + point;
		}

	}

}
