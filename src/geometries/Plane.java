package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
	
	// the starting point in plane
	private final Point point; 
	
	// normal to the plane
	private final Vector normal; 
	
	/**
	 * 
	 * @param point
	 * @param vector normal 
	 */
	public Plane(Point point, Vector normal) {
		
		this.point = point;
		this.normal = normal.normalize();
	}
	
	/**
	 * 
	 * @param p1 for plane creation
	 * @param p2 for plane creation
	 * @param p3 for plane creation
	 */
	public Plane(Point p1, Point p2, Point p3) {
		
		point = p1;
		
		Vector v1 = p1.subtract(p2);
		Vector v2 = p3.subtract(p1);
		normal = v1.crossProduct(v2.normalize());		
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	@Override
	public Vector getNormal(Point p) {
		return normal;
	}
}
