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
public class AmbientLight extends Light {

	/** the ambient light for the scene */
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

	/**
	 * constructor to initialize the ambient light and intensity
	 * 
	 * @param color color of ambient light
	 * @param kA    3 coordinates for intensity of light
	 */
	public AmbientLight(Color color, Double3 kA) {
		super(color.scale(kA));
	}

	/**
	 * constructor to initialize the ambient light and intensity
	 * 
	 * @param color color of ambient light
	 * @param kA    scaler for intensity of light
	 */
	public AmbientLight(Color color, double kA) {
		super(color.scale(kA));
	}

}
