package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The class Sphere extends the RadialGeometry class
 * and allows us to represent a sphere by radius and a point.
 * 
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com
 *        				Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */
public class Sphere extends RadialGeometry {

	// the center of the sphere
	private final Point center;

	/**
	 * Constructor to initialize sphere with a radius and point
	 * 
	 * @param radius
	 * @param point
	 */
	public Sphere(double radius, Point point) {
		super(radius);
		center = point;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.subtract(center).normalize();
	}
	
	@Override
	public List<Point> findIntsersections(Ray ray){
		
		// if p0 is in the center of the sphere
		if (center.equals(ray.getP0())) {
			return List.of(ray.getPoint(radius));
		}	
		
		Vector u = center.subtract(ray.getP0());
		double tm = ray.getDir().dotProduct(u);
		if(u.lengthSquared() < tm * tm) {
			return null;
		}
		
		double d = Math.sqrt(u.lengthSquared() - tm * tm);
		if (d >= radius) {
			return null;
		}
			
		double th = Math.sqrt(radius * radius - d * d);
		double t1 = tm + th;
		double t2 = tm - th;
		
		if(t1 > 0 && t2 > 0) {
			Point p1 = ray.getPoint(t1);
			Point p2 = ray.getPoint(t2);
			return List.of(p1,p2);
		}
		
		if(t1 > 0) {
			return List.of(ray.getPoint(t1));
		}
		
		if(t2 > 0) {
			return List.of(ray.getPoint(t2));
		}
		
		return null;
	}

	/**
	 * This function returns the center of the sphere
	 * 
	 * @return center of sphere
	 */
	Point getCenter() {
		return center;
	}
	
	/**
	 * This function returns the radius of the sphere
	 * 
	 * @return radius of sphere
	 */
	double getRadius() {
		return radius;
	}
}
