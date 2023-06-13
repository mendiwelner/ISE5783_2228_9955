package lighting;

import primitives.*;

public class PointLight extends Light {
	protected Point position;
	protected double kC = 1, kL = 0, kQ = 0;
	
	public PointLight(Color intensity, Point position) {
		super(intensity);
		this.position = position;
	}

	public Color getIntensity(Point p) {
		double d = p.distance(position);
		return getIntensity().scale(kC+kL*d + kQ*d*d);
	}
	
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}
	
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}
	
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}


	@Override
	public Vector getL(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

}
