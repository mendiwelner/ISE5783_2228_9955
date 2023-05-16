package geometries;

/**
 * The abstract class RadialGeometry implements the determination of the general
 * shape radius and more things down the road.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com 
 * 		   Mendy Segal. 211769955.Mendysegal490@gmail.com
 */
public abstract class RadialGeometry implements Geometry {

	/** the radius of a general shape */
	protected final double radius;
	/** squared the radius of a general shape */
	protected final double radius2;

	/**
	 * Constructor to initialize the radius of general radial geometry
	 * 
	 * @param radius r to assign the radius field
	 */
	RadialGeometry(double r) {

		if (r <= 0) {
			throw new IllegalArgumentException("The radius of a general shape must be greater then 0");
		}
		radius = r;
		radius2 = r * r;
	}
}
