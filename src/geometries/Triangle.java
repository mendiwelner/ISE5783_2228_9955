package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 * The class Triangle extends the Polygon class
 * and allows us to represent a Triangle by 3 points.
 * 
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com
 *        				Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */
public class Triangle extends Polygon {

	/**
	 * Constructor to initialize Triangle with 3 points
	 * 
	 * @param point p1
	 * @param point p2
	 * @param point p3
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}
	
	@Override
	public List<Point> findIntsersections(Ray ray){
		
		Vector dir = ray.getDir();
		Point p0 = ray.getP0();
		Vector v1 = vertices.get(0).subtract(p0);
		Vector v2 = vertices.get(1).subtract(p0);
		Vector v3 = vertices.get(2).subtract(p0);
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		double res1 = alignZero(dir.dotProduct(n1));
		double res2 = alignZero(dir.dotProduct(n2));
		double res3 = alignZero(dir.dotProduct(n3));
		
		if((res1 > 0 && res2 > 0 && res3 > 0) || (res1 < 0 && res2 < 0 && res3 < 0)) {
			return plane.findIntsersections(ray);
		}
		
		return null;
	}
}
