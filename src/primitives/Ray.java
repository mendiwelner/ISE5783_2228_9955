package primitives;

public class Ray {
	private Point point;
	private Vector dir;
	
	
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
	
	@Override
	public String toString() {
		return point.toString() + dir.toString();
	}

}
