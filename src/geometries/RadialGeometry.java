package geometries;

public abstract class RadialGeometry implements Geometry {
	
	// the radius of a general shape
	protected final double radius;
	
	/**
	 * @param r is the radius to assign
	 */
	RadialGeometry(double r){			
		
		if(r <= 0) {
			throw new IllegalArgumentException("The radius of a general shape must be greater then 0");
		}
		radius = r;
	}
}
