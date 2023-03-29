package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
	
	private Point point;
	private Vector normal;
	
	
	public Plane(Point point, Vector normal) {
		
		this.point = point;
		this.normal = normal.normalize(); // making sure that the vector is normalized
	}
	
	public Plane(Point p1, Point p2, Point p3) {
		
		point = p1; // Point of reference
		normal = null;
		
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	@Override
	public Vector getNormal(Point p) {
		return normal; // The function has to return null for now
	}

}
