package geometries;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import primitives.*;
public class Geometries implements Intersectable {
	private List<Intersectable> shapes;
	
	public Geometries(){
		shapes = new LinkedList<Intersectable>();
	}
	
	public Geometries(Intersectable... geometries) {
		shapes = List.of(geometries);
	}
	
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
		List<Point> intsersections = new LinkedList();
		for(Intersectable shape : shapes) {
			List<Point> geoInters = shape.findIntsersections(ray);
			if(geoInters != null) 
				intsersections.addAll(geoInters);
		}
		return intsersections;
	}
}
