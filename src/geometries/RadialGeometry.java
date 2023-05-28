package geometries;

import static primitives.Util.*;

/**
 * The abstract class RadialGeometry implements the determination of a rounded
 * shape radius and more things down the road.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public abstract class RadialGeometry extends Geometry {

	/** the radius of a rounded shape */
	protected final double radius;
	/** the squared radius of a rounded shape */
	protected final double radius2;

	/**
	 * Constructor to initialize the radius of general radial geometry
	 * 
	 * @param r the radius value
	 */
	RadialGeometry(double r) {
		if (alignZero(r) <= 0)
			throw new IllegalArgumentException("The radius of a general shape must be greater then 0");

		radius = r;
		radius2 = r * r;
	}
}
