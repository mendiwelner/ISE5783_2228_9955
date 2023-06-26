package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
	 * @param radius the radius of the tube
	 * @param ray    the axis ray of the tube
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
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		Vector v = ray.getDir();
		Vector v0 = axisRay.getDir();

		// Calculating temp1 = v - v0 * (v,v0)
		Vector temp1 = v;
		double vv0 = v.dotProduct(v0);
		if (!isZero(vv0)) {
			Vector v0vv0 = v0.scale(vv0);
			if (v.equals(v0vv0)) {
				return null;
			}
			temp1 = v.subtract(v0vv0);
		}

		// Calculating temp2 = dp - v0 * (dp,v0) where dp = p0 - p
		double temp1DotTemp2 = 0;
		double squaredTemp2 = 0;
		if (!ray.getP0().equals(axisRay.getP0())) {
			Vector dp = ray.getP0().subtract(axisRay.getP0());
			Vector temp2 = dp;
			double dpv0 = dp.dotProduct(v0);
			if (isZero(dpv0)) {
				temp1DotTemp2 = temp1.dotProduct(temp2);
				squaredTemp2 = temp2.lengthSquared();
			} else {
				Vector v0dpv0 = v0.scale(dpv0);
				if (!dp.equals(v0dpv0)) {
					temp2 = dp.subtract(v0dpv0);
					temp1DotTemp2 = temp1.dotProduct(temp2);
					squaredTemp2 = temp2.lengthSquared();
				}
			}
		}

		// Getting the quadratic equation: at^2 +bt + c = 0
		double a = temp1.lengthSquared();
		double b = 2 * temp1DotTemp2;
		double c = alignZero(squaredTemp2 - radius * radius);

		double squaredDelta = alignZero(b * b - 4 * a * c);
		if (squaredDelta <= 0)
			return null;

		double delta = Math.sqrt(squaredDelta);
		// a is always non-negative, therefore t1 < t2
		double t2 = alignZero((-b + delta) / (2 * a));
		if (t2 <= 0)
			return null;

		double t1 = alignZero((-b - delta) / (2 * a));
		return t1 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2)))
				: List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
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
