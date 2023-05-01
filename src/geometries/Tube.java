package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.isZero;


/**
 * The class Tube extends the RadialGeometry class
 * and allows us to represent a Tube with radius and ray.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com
 *         Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */
public class Tube extends RadialGeometry {
	
	// our axis ray
	protected final Ray axisRay;

	/**
	 * Constructor to Creates a cylindrical tube with the specified radius and axis ray.
	 * 
	 * @param radius  the radius of the tube
	 * @param axisRay the axis ray of the tube
	 */
	public Tube(double radius, Ray ray) { // is it should be Package Friendly
		super(radius);
		this.axisRay = ray;
	}

	@Override
	public Vector getNormal(Point p) {
		Vector v = axisRay.getDir();
		Point p0 = axisRay.getP0();
		
		double t = v.dotProduct(p.subtract(p0));
		// Check that there is no zero vector
		Point o = isZero(t) ? p0 : p0.add(v.scale(t));
		return p.subtract(o).normalize();
	}
	
	@Override
	public List<Point> findIntsersections(Ray ray){
		return null;
	}

	/**
	 * This function returns the axis ray of the tube 
	 * 
	 * @return axisRay
	 */
	Ray getRay() {
		return axisRay;
	}

}
