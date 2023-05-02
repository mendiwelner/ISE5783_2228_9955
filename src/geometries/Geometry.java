package geometries;

import primitives.Vector;

import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * The interface Geometry will serve as the representation of a general shape
 * 
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com
 *        				Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */

public interface Geometry extends Intersectable {

	/**
	 * This function returns the normal vector at the given point 
	 * on the shell of general geometry
	 * 
	 * @param point
	 * @return the normal of the shape at that point
	 */
	public Vector getNormal(Point p);

}
