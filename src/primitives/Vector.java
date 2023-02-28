package primitives;

public class Vector extends Point{
	
	public Vector(Double3 newXyz) {
		super(newXyz);
	}
	
	public Vector add(Vector vec) {
		return new Vector(xyz.add(vec.xyz));
	}
	
	public Vector scale(double num) {
		return new Vector(xyz.add(vec.xyz));
	}
	
	@Override
	 public boolean equals(Object obj) {
		 if (this == obj) return true;
		 if (obj instanceof Vector v)
			 return this.xyz.equals(v.xyz);
		 return false;
	}
	
	@Override
	public String toString() {
		return xyz.toString();
	}

	

	



}
