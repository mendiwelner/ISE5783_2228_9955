package lighting;

import primitives.Color;

/**
 * The class Light will handle in the light of the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
abstract class Light {
	/** the intensity of the light for the scene */
	protected final Color intensity;

	/**
	 * constructor to initialize the intensity of light
	 * 
	 * @param intensity intensity of light
	 */
	public Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * This function returns the intensity of light
	 * 
	 * @return intensity light of the scene
	 */
	public Color getIntensity() {
		return this.intensity;
	}
}
