package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
	
	 
	private final Ray axisRay;
	
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
		return null; 
	}
	
	/**
	 * @return axisRay
	 */
	Ray getRay(){
		return axisRay;
	}

}
