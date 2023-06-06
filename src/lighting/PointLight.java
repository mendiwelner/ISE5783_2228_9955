package lighting;

import primitives.*;

public class PointLight extends Light {
	protected Point position;
	protected double kC = 1, kL = 0, kQ = 0;
	
	protected PointLight(Color intencity) {
		super(intencity);
	}

	public Color getIntencity(Point p) {
		double d = p.distance(position);
		return getIntencity().scale(kC+kL*d + kQ*d*d);
	}
	
	PointLight setKC(double kC) {
		this.kC = kC;
		return this;
	}
	
	PointLight setKL(double kL) {
		this.kL = kL;
		return this;
	}
	
	PointLight setKQ(double kQ) {
		this.kQ = kQ;
		return this;
	}


	@Override
	public Vector getL(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

}
