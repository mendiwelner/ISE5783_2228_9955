/**
 * 
 */
package lighting;

import primitives.*;

/**
 * The class AmbientLight will serve as the light of the scene finite Tube
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class AmbientLight {

	/* the ambient light for the scene */
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
	/* the intensity of the light for the scene */
	private final Color intensity;

	/**
	 * constructor to initialize the ambient light and intensity
	 * 
	 * @param color-color of ambient light
	 * @param kA-scaler for intensity of light
	 */
	public AmbientLight(Color color, Double3 kA) {
		intensity = color.scale(kA);

	}

	/**
	 * 
	 * @return intensity light of the scene
	 */
	public Color getIntensity() {
		return intensity;
	}
}