package lighting;

import primitives.Vector;
import primitives.Color;
import primitives.Point;

/**
 * DirectionalLight class will handle the Directional source Light
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */

public class DirectionalLight extends Light implements LightSource {
	/**the direction of the vector of the light*/
	private Vector direction;
	/**
	 * Constructor to create a new Directional Light object that call the super (light)
	 * 
	 * @param intensity the intensity of the color
	 * @param direction of the light
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}
	/**
	 * This function returns the intensity of view light
	 * 
	 * @return getIntensity
	 */
	public Color getIntensity(Point p) {
		return intensity;
	}
	/**
	 * This function returns the direction of the light
	 * 
	 * @return direction
	 */
	public Vector getL(Point p) {
		return direction;
	}
	
	@Override
	public double getDistance(Point p) {
		return Double.POSITIVE_INFINITY;
	}
}
