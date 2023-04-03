package primitives;

import static java.lang.Math.*;

public class Point {
	
	// x,y,z for a point
	protected final Double3 xyz;

	/**
	 * constructor to Initializing the point  
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(double x, double y, double z) {
		xyz = new Double3(x, y, z);
	}

	/**
	 * constructor to Initializing the point with Double3 object
	 * @param newXyz
	 */
	Point(Double3 newXyz) {
		xyz = newXyz;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Point p)
			return this.xyz.equals(p.xyz);
		return false;
	}

	@Override
	public String toString() {
		return "" + xyz;
	}

	/**
	 * adds a vector to point and returns the new point
	 * @param vec
	 * @return new point
	 */
	public Point add(Vector vec) {
		return new Point(xyz.add(vec.xyz));
	}

	/**
	 * subtract the point from vector
	 * @param p
	 * @return a vector that was subtracted by a point
	 */
	public Vector subtract(Point p) {
		return new Vector(xyz.subtract(p.xyz));
	}

	/**
	 * @param p
	 * @return distance squared 
	 */
	public double distanceSquared(Point p) {
		return (xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) 
			   + (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) 
			   + (xyz.d3 - p.xyz.d3) * (xyz.d3 - p.xyz.d3);
	}

	/**
	 * @param p
	 * @return distance 
	 */
	public double distance(Point p) {
		return Math.sqrt(distanceSquared(p));
	}

}
