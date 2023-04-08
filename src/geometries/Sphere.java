package geometries;
import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	
	// the center of the sphere
	private final Point center;
	
	/**
	 * @param radius
	 * @param point
	 */
	public Sphere(double radius, Point point){ // is it should be Package Friendly
		super(radius);
		center = point;
	}

	/**
	 * @param point
	 * @return normal to the sphere
	 */
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize(); 
	}
	
	/**
	 * @return center
	 */
	Point getCenter(){
		return center;
	}
}
