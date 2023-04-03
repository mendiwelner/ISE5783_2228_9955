package geometries;

public abstract class RadialGeometry implements Geometry {
	
	// the radius of a general shape
	protected final double radius;
	
	/**
	 * @param r is the radius to assign
	 */
	RadialGeometry(double r){		
		radius = r;
	}

}
