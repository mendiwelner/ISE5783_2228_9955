package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * The class SpotLight provide the spot light for the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class SpotLight extends PointLight {

	/** the point to focus for the scene */
	private final Vector direction;

	/**
	 * constructor to initialize the direction,position and direction of light
	 * 
	 * @param intensity intensity of light
	 * @param position  position of scene's light
	 * @param direction direction of scene's light
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		Vector l = getL(p);
		double dirL = direction.dotProduct(l);
		return alignZero(dirL) <= 0 ? Color.BLACK : super.getIntensity(p).scale(dirL);
	}

}
