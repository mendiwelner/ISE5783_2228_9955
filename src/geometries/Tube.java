package geometries;

import java.util.List;
import primitives.*;

/**
 * The class Tube extends the RadialGeometry class and allows us to represent a
 * Tube with radius and ray.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class Tube extends RadialGeometry {

	/** axis ray of the tube - represents the whole axis line */
	protected final Ray axisRay;

	/**
	 * Constructor to Creates a cylindrical tube with the specified radius and axis
	 * ray.
	 * 
	 * @param radius  the radius of the tube
	 * @param ray the axis ray of the tube
	 */
	public Tube(double radius, Ray ray) { // is it should be Package Friendly
		super(radius);
		this.axisRay = ray;
	}

	@Override
	public Vector getNormal(Point p) {
		double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
		return p.subtract(axisRay.getPoint(t)).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
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
