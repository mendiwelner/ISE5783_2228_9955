package primitives;

public class Ray {
	
	private Point point;
	private Vector dir;
	
	/**
	 * Creates a new Ray object with the specified starting point and direction.
	 * The direction vector is normalized to have a length of 1.
	 * @param p the starting point of the Ray
	 * @param vec the direction vector of the Ray
	 */
	public Ray(Point p, Vector vec) {
		point = p;
		dir = vec.normalize();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof Ray r)
			return this.point.equals(r.point) && this.dir.equals(r.dir);
		return false;
	}
	
	/**
	 * Returns a string representation of this Ray object.
	 * The string is in the format "startPoint -> directionVector".
	 * @return a string representation of the Ray object
	 */
	@Override
	public String toString() {
		return point + "->" + dir;
	}

}

