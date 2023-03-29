package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


//Cylinder class is used for Cylinder shape
 
public class Cylinder extends Tube{
	//the height of the Cylinder
	private double height;
	
	/** Constructor to initialize Cylinder
	   * @param radius1 radius value
	   * @param ray1 ray value
	   * @param height1 height value */
	Cylinder(double radius, Ray ray, double height){
		super(radius, ray);
		this.height = height;
	}
	
	//this function returns the normal of the Cylinder in sum point on it, @param point 
	public Vector getNormal(Point point) {
		return null; 
	}
	
	//this function returns the height of the Cylinder
	double getHeight(){
		return height;
	}

}
