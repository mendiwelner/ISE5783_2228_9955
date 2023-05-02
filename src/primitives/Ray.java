package primitives;

import static primitives.Util.isZero;

/**
 * Ray class will serve as the ray of a general shape 
 * to build the shape later on.
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com
 *         Mendy Segal. 211769955. Mendysegal490@gmail.com  
 */
public class Ray {

	private final Point p0;
	private final Vector dir;

	/**
	 * Constructor to create a new Ray object with the specified starting point and direction. The
	 * direction vector is normalized to have a length of 1.
	 * 
	 * @param point-the starting point of the Ray
	 * @param vector-the direction vector of the Ray
	 */
	public Ray(Point p, Vector vec) {
		p0 = p;
		dir = vec.normalize();
	}

	/**
	 * This function returns the central point on the bottom base which is p0
	 * 
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * This function returns a point p0 on the central base and we're adding
	 * to it the direction vector scaled by t. if t equals to 0 then we just return p0.
	 * 
	 * @param t is a scalar
	 * @return new point
	 */
	public Point getPoint(double t) {
		return isZero(t) ? p0 : p0.add(dir.scale(t));
	}

	/**
	 * This function returns the direction vector normalized
	 * 
	 * @return direction vector of the axis ray
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Ray r)
			return this.p0.equals(r.p0) && this.dir.equals(r.dir);
		return false;
	}

	@Override
	public String toString() {
		return p0 + "->" + dir;
	}

}
