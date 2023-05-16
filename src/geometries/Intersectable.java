package geometries;

import java.util.List;
import primitives.Ray;
import primitives.Point;

/**
 * This interface handle in the intersections between the geometries
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com 
 * 		   Mendy Segal. 211769955.Mendysegal490@gmail.com
 *
 */
public interface Intersectable {
	/**
	 * This function returns a list of intersection points between ray and general
	 * geometry
	 * 
	 * @param ray
	 * @return List of intersection points
	 */
	List<Point> findIntsersections(Ray ray);
}
