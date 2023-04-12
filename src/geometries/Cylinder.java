package geometries;
import geometries.Plane;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


//Cylinder class is used for Cylinder shape
 
public class Cylinder extends Tube{
	//the height of the Cylinder
	private final double height;
	private final Plane topCap , bottomCap;
	
	/** Constructor to initialize Cylinder
	   * @param radius1 radius value
	   * @param ray1 ray value
	   * @param height1 height value */
	Cylinder(double radius, Ray axisRay, double height){
		super(radius, axisRay);
		this.height = height;
		topCap = new Plane(axisRay.getPoint(height) , axisRay.getDir());
		bottomCap = new Plane(axisRay.getP0(), axisRay.getDir().scale(-1));
	}
	
	//this function returns the normal of the Cylinder in sum point on it, @param point 
	public Vector getNormal(Point point) {
		
		if(isBetweenCaps(point))
			return super.getNormal(point);
		
		Vector v = axisRay.getDir();
		Point p0 = axisRay.getP0();
		
		if(point.equals(p0) || v.dotProduct(point.subtract(p0)) == 0)
			return bottomCap.getNormal();
				
		return topCap.getNormal();
	}
	
	//this function returns the height of the Cylinder
	double getHeight(){
		return height;
	}
	
	private boolean isBetweenCaps(Point p) {
		
		Vector n = axisRay.getDir();
		Point p0 = axisRay.getP0();
		Point topPoint = axisRay.getPoint(height);
		
		if(p.equals(p0) || p.equals(topPoint))
			return false;
		
		return n.dotProduct(p.subtract(p0)) > 0 
				&& n.dotProduct(p.subtract(topPoint)) < 0;	
	}
}
