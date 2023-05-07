package primitives;

/**
 * Point class allows us to represent a point with coordinates x,y,z.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal. 211769955.
 *         Mendysegal490@gmail.com
 *
 */

public class Point {

	// x,y,z Double3 object to represent a point
	protected final Double3 xyz;

	/**
	 * Constructor to initializing the point
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 */
	public Point(double x, double y, double z) {
		xyz = new Double3(x, y, z);
	}

	/**
	 * Constructor to initializing the point with Double3 object
	 * 
	 * @param Double3 xyz-object
	 */
	Point(Double3 newXyz) {
		xyz = newXyz;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Point p)
			return xyz.equals(p.xyz);
		return false;
	}

	@Override
	public String toString() {
		return "" + xyz;
	}

	/**
	 * This function adds a vector to the point and returns the new point
	 * 
	 * @param vector
	 * @return new point after we added a vector to it
	 */
	public Point add(Vector vec) {
		return new Point(xyz.add(vec.xyz));
	}

	/**
	 * This function subtracts the point from vector
	 * 
	 * @param point
	 * @return new vector that was subtracted by the given point
	 */
	public Vector subtract(Point p) {
		return new Vector(xyz.subtract(p.xyz));
	}

	/**
	 * This function returns the distance squared of our point from the given point
	 * 
	 * @param p the second point
	 * @return distance squared
	 */
	public double distanceSquared(Point p) {
		double dx = xyz.d1 - p.xyz.d1;
		double dy = xyz.d2 - p.xyz.d2;
		double dz = xyz.d3 - p.xyz.d3;
		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * This function returns the distance of our point from the given point
	 * 
	 * @param point
	 * @return distance of our point from the given point
	 */
	public double distance(Point p) {
		return Math.sqrt(distanceSquared(p));
	}

	/**
	 * This function returns the x coordinate
	 * 
	 * @return x coordinate of the Double3 object
	 */
	public double getX() {
		return xyz.d1;
	}

	/**
	 * This function returns the y coordinate
	 * 
	 * @return y coordinate of the Double3 object
	 */
	public double getY() {
		return xyz.d2;
	}

	/**
	 * This function returns the z coordinate
	 * 
	 * @return z coordinate of the Double3 object
	 */
	public double getZ() {
		return xyz.d3;
	}

}
