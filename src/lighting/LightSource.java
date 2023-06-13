package lighting;

import primitives.*;

/**
 * The class LightSource provide the LightSource for the scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public interface LightSource {
	public Color getIntensity(Point p);
	public Vector getL(Point p);

}
