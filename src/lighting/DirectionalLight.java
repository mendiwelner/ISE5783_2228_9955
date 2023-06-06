package lighting;

import primitives.Vector;

import primitives.Color;
import primitives.Point;

public class DirectionalLight extends Light{
	private Vector direction;
	protected DirectionalLight(Color intencity, Vector direction) {
		super(intencity);
		this.direction = direction;
	}

	public Color getIntencity(Point p) {
		return this.getIntencity();
	}
	
	public Vector getL(Point p) {
		// TODO Auto-generated method stub
		return null;
	}
}
