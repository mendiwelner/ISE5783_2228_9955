package primitives;

public class Vector extends Point{
	
	public Vector(double _x, double _y, double _z)
	{
		super(_x,_y,_z);
		
		if(this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
			
	}
	
	public Vector(Double3 newXyz) {
		super(newXyz);
		
		if(this.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
		
	}
	
	public Vector add(Vector vec) {
		return new Vector(xyz.add(vec.xyz));
	}
	
	public Vector scale(double scalar) {
		
		return new Vector(xyz.scale(scalar));
	}
	
	public double dotProduct(Vector vec) {
		
		double x =  this.xyz.d1 * vec.xyz.d1;
		double y =  this.xyz.d2 * vec.xyz.d2;
		double z =  this.xyz.d3 * vec.xyz.d3;
		
		return x + y + z;
	}
	
	public Vector crossProduct(Vector vec) {
		
		
		double x1 = this.xyz.d1;
		double y1 = this.xyz.d2;
		double z1 = this.xyz.d3;
		
		double x2 = vec.xyz.d1;
		double y2 = vec.xyz.d2;
		double z2 = vec.xyz.d3;
		
		return new Vector(y1 * z2 - z1 * y2,
						  z1 * x2 - x1 * z2,
						  x1 * y2 - y1 * x2
						  );		
	}
	
	public double lengthSquared() {
		
		double x = this.xyz.d1;
		double y = this.xyz.d2;
		double z = this.xyz.d3;
		
		return x * x + y * y + z * z;
	}
	
	public double length() {
		
		return Math.sqrt(this.lengthSquared()); 
	}
	
	public Vector normalize() {
		
		double len = this.length();
		double x = xyz.d1 / len;
		double y = xyz.d2 / len;
		double z = xyz.d3 / len;
		
		Vector temp = new Vector(x,y,z);
		if(temp.xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("The vector can not be zero");
		
		this.xyz = temp.xyz;
		return this;		
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






