package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
	
	private Point q0;
	private Vector normal;
	
	
	public Plane(Point p, Vector _normal) {
		
		q0 = p;
		normal = _normal.normalize(); // making sure that the vector is normalized
	}
	
	public Plane(Point a, Point b,Point c) {
		
		q0 = a; // Point of reference
		normal = null;
		
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	@Override
	public Vector getNormal(Point p) {
		return normal; // the function has to return null for now
	}

}
