package geometries;
import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
	private Point center;
	
	Sphere(double radius, Point point){
		super(radius);
		center = point;
	}

	public Vector getNormal(Point p) {
		return null; 
	}
	
	Point getCenter(){
		return center;
	}

}
