package lighting;

import primitives.*;

/**
 * The class PointLight provide the point light for the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class PointLight extends Light implements LightSource {

	/** the point to focus for the scene */
	private final Point position;
	/** the attenuation coefficients for the scene */
	private double kC = 1, kL = 0, kQ = 0;

	/**
	 * constructor to initialize the direction and position of light
	 * 
	 * @param intensity intensity of light
	 * @param position  position of scene's light
	 */
	public PointLight(Color intensity, Point position) {
		super(intensity);
		this.position = position;
	}

	/**
	 * Getter of intensity of light
	 * 
	 * @param p point in the scene
	 * @return intensity of light at the point
	 */
	public Color getIntensity(Point p) {
		double d = p.distance(position);
		return intensity.reduce(kC + kL * d + kQ * d * d);
	}

	/**
	 * Setter of kC variable
	 * 
	 * @param kC double
	 * @return PointLight of the scene
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Setter of kL variable
	 * 
	 * @param kL double
	 * @return PointLight of the scene
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Setter of kQ variable
	 * 
	 * @param kQ double
	 * @return PointLight of the scene
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}

}
