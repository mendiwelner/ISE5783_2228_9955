package geometries;

import java.util.List;

import primitives.*;

/**
 * This interface handle in the intersections between the geometries
 * 
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com
 *        				Mendy Segal. 211769955. Mendysegal490@gmail.com 
 *
 */
public interface Intersectable {
	List<Point> findIntsersections(Ray ray);
}
