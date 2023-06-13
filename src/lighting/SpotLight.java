package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
	private Vector direction;
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction;
	}
	
	@Override
	public Color getIntensity(Point p) {
		double d = p.distance(position);
		Vector l = p.subtract(position);
		double max = Math.max(0, direction.dotProduct(l));
		return getIntensity().scale(max).scale(kC+kL*d + kQ*d*d);
	}

}
