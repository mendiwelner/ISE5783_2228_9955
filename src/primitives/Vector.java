package primitives;

/**
 * Vector class will serve as the "upgraded point" to represent
 * direction and size.
 * 
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com
 *        				Mendy Segal. 211769955. Mendysegal490@gmail.com 
 */

public class Vector extends Point {

	/**
	 * Constructor to initialize Double3 object xyz
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
	}

	/**
	 * Constructor to initialize xyz with Double3 object
	 * 
	 * @param Double3 object
	 */
	Vector(Double3 newXyz) {
		super(newXyz);
		if (this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
	}

	/**
	 * This function adds the given vector to our vector and return the result as a new vector.
	 * 
	 * @param vector 
	 * @return new vector
	 */
	public Vector add(Vector vec) {
		return new Vector(xyz.add(vec.xyz));
	}

	/**
	 * This function scales this vector by the given scalar and return the result as a new vector.
	 * 
	 * @param scalar to multiply the vector by
	 * @return new vector
	 */
	public Vector scale(double scalar) {
		return new Vector(xyz.scale(scalar));
	}

	/**
	 * This function computes the dot product of this vector and the given vector.
	 * 
	 * @param vector to compute the dot product with
	 * @return scalar
	 */
	public double dotProduct(Vector vec) {
		return xyz.d1 * vec.xyz.d1 + xyz.d2 * vec.xyz.d2 + xyz.d3 * vec.xyz.d3;
	}

	/**
	 * This function computes the cross product of this vector and the given vector and return the
	 * result as a new vector that orthogonal to both vectors.
	 * 
	 * @param vector to compute the cross product with
	 * @return new orthogonal vector 
	 *         
	 */
	public Vector crossProduct(Vector vec) {
		return new Vector(xyz.d2 * vec.xyz.d3 - xyz.d3 * vec.xyz.d2, //
				xyz.d3 * vec.xyz.d1 - xyz.d1 * vec.xyz.d3, //
				xyz.d1 * vec.xyz.d2 - xyz.d2 * vec.xyz.d1);
	}

	/**
	 * compute the squared length of this vector.
	 * 
	 * @return the squared length of this vector
	 */
	public double lengthSquared() {
		double dx = xyz.d1;
		double dy =  xyz.d2;
		double dz = xyz.d3;
		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * compute the length of this vector.
	 * 
	 * @return the length of this vector
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * normalize this vector to be a vector with 
	 * the same direction as this vector but a length of 1
	 * 
	 * @return normalized vector   
	 */
	public Vector normalize() {
		return this.scale(1 / this.length());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Vector v)
			return this.xyz.equals(v.xyz); //
		return false;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
