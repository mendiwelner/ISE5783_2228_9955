package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The class SpotLight provide the spot light for the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class SpotLight extends PointLight{
	
	/** the point to focus for the scene */
	private Vector direction;
	
	/**
	 * constructor to initialize the direction,position and direction of light
	 * 
	 * @param intensity intensity of light
	 * @param position position of scene's light
	 * @param direction direction of scene's light
	 */
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
