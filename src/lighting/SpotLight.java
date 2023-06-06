package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
	private Vector direction;
	protected SpotLight(Color intencity, Vector direction) {
		super(intencity);
		this.direction = direction;
	}
	
	@Override
	public Color getIntencity(Point p) {
		double d = p.distance(position);
		Vector l = p.subtract(position);
		double max = Math.max(0, direction.dotProduct(l));
		return getIntencity().scale(max).scale(kC+kL*d + kQ*d*d);
	}

}
