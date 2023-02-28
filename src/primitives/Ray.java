package primitives;

public class Ray {
	private Point point;
	private Vector vector;
	
	@Override
	 public boolean equals(Object obj) {
		 if (this == obj) return true;
		 if (obj instanceof Ray r)
			 return this.point.equals(r.point) && this.vector.equals(r.vector);
		 return false;
	}
	
	@Override
	public String toString() {
		return point.toString() + vector.toString();
	}

}
