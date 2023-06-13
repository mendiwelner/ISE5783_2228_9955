package lighting;

import primitives.Color;
import primitives.Point;

/**
 * The class Light will handle in the light of the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
abstract class Light implements LightSource{
	/** the intensity of the light for the scene */
	private Color intensity;
	protected Light(Color intensity) {
		this.intensity = intensity;
	}
	/**
	 * @return intensity light of the scene
	 */
	public Color getIntensity() {
		return this.intensity;
	}
}
