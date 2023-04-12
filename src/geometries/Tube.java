package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

public class Tube extends RadialGeometry{
	
	 
	protected final Ray axisRay;
	
	/**
	 * Creates a cylindrical tube with the specified radius and axis ray.
	 * @param radius the radius of the tube
	 * @param axisRay the axis ray of the tube
	 */
	Tube(double radius, Ray ray){
		super(radius);
		this.axisRay = ray;
	}
	

	public Vector getNormal(Point p) {
		
		Vector v = axisRay.getDir();
		Point p0 = axisRay.getP0();
		double t = v.dotProduct(p.subtract(p0));
		Point O = p0;
		
		if(!isZero(t))// avoiding from zero vector
			O = p0.add(v.scale(t));
		
		return p.subtract(O).normalize();		 
	}
	
	/**
	 * @return axisRay
	 */
	Ray getRay(){
		return axisRay;
	}

}
