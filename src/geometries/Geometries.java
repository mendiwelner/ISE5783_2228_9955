package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;

/**
 * The class Geometries composites our geometries shapes
 * to build some complex shapes.
 * Cylinder is finite Tube
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com
 *         Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */
public class Geometries implements Intersectable {
	
	// the list of the geometries
	private List<Intersectable> shapes;
	
	/**
	 * Default constructor to initialize the shapes list to a new LinkedList
	 */
	public Geometries(){
		shapes = new LinkedList<Intersectable>();
	}
	
	/**
	 * constructor to initialize the shapes list with geometries
	 * 
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		shapes = List.of(geometries);
	}
	
	/**
	 * This function adds geometries to the list shapes
	 * 
	 * @param as many geometries as we like
	 */
	public void add(Intersectable... geometries) {
		for(Intersectable shape : geometries) {
			shapes.add(shape);
		}
	}
	
	@Override
	public List<Point> findIntsersections(Ray ray){
		boolean hasIntersection = false;
		for(Intersectable shape : shapes){
			if(shape.findIntsersections(ray) != null) {
				hasIntersection = true;
				break;
			}
		}
		if(!hasIntersection) {
			return null;
		}
		List<Point> intsersections = new LinkedList<Point>();
		for(Intersectable shape : shapes) {
			if(shape.findIntsersections(ray) != null) {
				for(Point p : shape.findIntsersections(ray)) {
					intsersections.add(p);
				}
			}
		}
		return intsersections;
	}
}
