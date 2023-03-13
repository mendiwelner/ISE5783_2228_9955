package geometries;
import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center;
	
	Sphere(double radius1, Point point1){
		super(radius1);
		center = point1;
	}
	

	public Vector getNormal(Point p) {
		return null; 
	}
	
	Point getCenter(){
		return center;
	}

}
