package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
	private Ray ray;
	
	Tube(double radius1, Ray ray1){
		super(radius1);
		ray = ray1;
	}
	

	public Vector getNormal(Point p) {
		return null; 
	}
	
	Ray getRay(){
		return ray;
	}

}
