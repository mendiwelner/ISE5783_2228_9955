package lighting;

import primitives.*;

/**
 * The interface LightSource provide the LightSource for the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public interface LightSource {
	
	/**
	 * Getter of intensity of light
	 * 
	 * @param p point in the scene
	 * @return intensity of light at the point
	 */
	public Color getIntensity(Point p);
	
	/**
	 * Getter of direction of light
	 * 
	 * @param p point in the scene
	 * @return direction of light at the point
	 */
	public Vector getL(Point p);
	
	public double getDistance(Point p);

}
