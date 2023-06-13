package lighting;

import primitives.Vector;
import primitives.Color;
import primitives.Point;

public class DirectionalLight extends Light{
	private Vector direction;
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction;
	}

	public Color getIntensity(Point p) {
		return this.getIntensity();
	}
	
	public Vector getL(Point p) {
		return direction;
	}
}
