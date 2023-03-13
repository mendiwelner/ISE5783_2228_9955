package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
	private double height;
	
	Cylinder(double radius1, Ray ray1, double height1){
		super(radius1, ray1);
		height = height1;
	}
	

	public Vector getNormal(Point p) {
		return null; 
	}
	
	double getHeight(){
		return height;
	}

}
