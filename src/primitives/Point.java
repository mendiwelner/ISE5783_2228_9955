package primitives;
import static java.lang.Math.*;


public class Point {
	protected Double3 xyz;
	
	Point(double x,double y,double z) {
		
		xyz = new Double3(x,y,z);
	}
	
	Point(Double3 newXyz){
		xyz = newXyz;
	}
	
	@Override
	 public boolean equals(Object obj) {
		 if (this == obj) return true;
		 if (obj instanceof Point p)
			 return this.xyz.equals(p.xyz);
		 return false;
	}
	
	@Override
	public String toString() {
		return xyz.toString();
	}
	
	public Point add(Vector vec) {
		return new Point(xyz.add(vec.xyz));
	}
	
	// the name is subtract
	public Vector subtract(Point p) {
		
		return new Vector(xyz.subtract(p.xyz));
	}
	
	public double distanceSquared(Point p) {
		double x = xyz.d1 - p.xyz.d1;
		double y = xyz.d2 - p.xyz.d2;
		double z = xyz.d3 - p.xyz.d3;
		return x*x + y*y + z*z;
	}
	
	public double distance(Point p) {
		return Math.sqrt(distanceSquared(p));
	}
	

}
