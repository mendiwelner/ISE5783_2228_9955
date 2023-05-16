package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;

/**
 * The class Geometries composites our geometries shapes to build some complex
 * shapes. Cylinder is finite Tube
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com 
 * 		   Mendy Segal. 211769955.Mendysegal490@gmail.com
 */
public class Geometries implements Intersectable {

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
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * This function adds geometries to the list shapes
	 * 
	 * @param as many geometries as we like
	 */
	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// there is at least 1 intersection, we create our list
		List<Point> intersections = null;
		for (Intersectable shape : shapes) {
			List<Point> shapeIntersections = shape.findIntsersections(ray);
			if (shapeIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<>();
				intersections.addAll(shapeIntersections);
			}
		}
		return intersections;
	}
}
