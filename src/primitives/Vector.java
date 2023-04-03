package primitives;

public class Vector extends Point {

	/**
	 * Constructor to initialize xyz
	 * @param _x The x-coordinate
	 * @param _y The y-coordinate
	 * @param _z The z-coordinate
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
	}

	/**
	 * Constructor to initialize xyz with Double3 object
	 * @param newXyz
	 */
	Vector(Double3 newXyz) {
		super(newXyz);
		if (this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
	}

	/**
     * Add the given vector to this vector and return the result as a new vector.
     * @param vec The vector to add
     * @return A new vector that is the sum of this vector and the given vector
     */
	public Vector add(Vector vec) {
		return new Vector(xyz.add(vec.xyz));
	}

	/**
     * Scale this vector by the given scalar and return the result as a new vector.
     * @param scalar The scalar to multiply the vector by
     * @return A new vector that is this vector multiplied by the given scalar
     */
	public Vector scale(double scalar) {
		return new Vector(xyz.scale(scalar));
	}

	 /**
     * Compute the dot product of this vector and the given vector.
     * @param vec The vector to compute the dot product with
     * @return The dot product of this vector and the given vector
     */
	public double dotProduct(Vector vec) {
		return   xyz.d1 * vec.xyz.d1 
			   + xyz.d2 * vec.xyz.d2 
			   + xyz.d3 * vec.xyz.d3;
	}

	/**
     * Compute the cross product of this vector and the given vector and return the
     * result as a new vector.
     * @param vec The vector to compute the cross product with
     * @return A new vector that is the cross product of this vector and the given vector
     */
	public Vector crossProduct(Vector vec) {
		return new Vector(xyz.d2 * vec.xyz.d3 - xyz.d3 * vec.xyz.d2, //
				xyz.d3 * vec.xyz.d1 - xyz.d1 * vec.xyz.d3, //
				xyz.d1 * vec.xyz.d2 - xyz.d2 * vec.xyz.d1);
	}

	/**
     * Compute the squared length of this vector.
     * @return The squared length of this vector
     */
	public double lengthSquared() {
		return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
	}

	/**
     * Compute the length of this vector.
     * @return The length of this vector
     */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
     * Return a new vector that is the normalized version of this vector.
     * @return A new vector that has the same direction as this vector but a length of 1
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
