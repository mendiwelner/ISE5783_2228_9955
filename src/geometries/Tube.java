package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
	private Ray ray;
	
	Tube(double radius, Ray ray){
		super(radius);
		this.ray = ray;
	}
	
	public Vector getNormal(Point p) {
		return null; 
	}
	
	Ray getRay(){
		return ray;
	}

}
