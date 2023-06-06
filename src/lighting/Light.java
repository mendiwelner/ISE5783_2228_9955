package lighting;

import primitives.Color;

/**
 * The class AmbientLight will handle in the light of the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
abstract class Light {
	/** the intensity of the light for the scene */
	private Color intencity;
	protected Light(Color intencity) {
		this.intencity = intencity;
	}
	/**
	 * @return intensity light of the scene
	 */
	public Color getIntencity() {
		return this.intencity;
	}

}
