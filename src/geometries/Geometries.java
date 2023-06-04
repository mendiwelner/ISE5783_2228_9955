package geometries;
import scene.*;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * The class Geometries composites our geometries shapes to build some complex
 * shapes. Cylinder is finite Tube
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class Geometries extends Intersectable {

	// the list of the geometries
	private List<Intersectable> shapes = new LinkedList<>();

	/**
	 * Default constructor to initialize the shapes list to a new LinkedList
	 */
	public Geometries() {
	}

	/**
	 * constructor to initialize the shapes list with geometries
	 * 
	 * @param geometries the geometries to include
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * This function adds geometries to the list shapes
	 * 
	 * @param geometries as many geometries as we like
	 */
	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray)  {
		// there is at least 1 intersection, we create our list
		List<GeoPoint> intersections = null;
		for (Geometry geometry : shapes) {
			List<Point> shapeIntersections = shape.findIntersections(ray);
			if (shapeIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<>();
				intersections.addAll(shapeIntersections);
			}
		}
		return intersections;
	}
}
