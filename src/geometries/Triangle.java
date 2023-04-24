package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;

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
		return null;
	}
}
