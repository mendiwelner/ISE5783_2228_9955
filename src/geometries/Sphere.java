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
	Sphere(double radius, Point point){
		super(radius);
		center = point;
	}

	/**
	 * returns null in the mine time
	 */
	public Vector getNormal(Point p) {
		return null; 
	}
	
	/**
	 * @return center
	 */
	Point getCenter(){
		return center;
	}

}
