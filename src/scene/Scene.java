/**
 * 
 */
package scene;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.*;

/**
 * Class Scene will serve as tester of all Scene operations
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 *
 */
public class Scene {

	/** the name of the scene */
	public final String name;
	/** the color of the background, initialize to black */
	public Color background = Color.BLACK;
	/** the ambientLight, initialize to none */
	public AmbientLight ambientLight = AmbientLight.NONE;
	/** the geometries for the scene */
	public Geometries geometries = new Geometries();
	/** the lights for the scene */
	public List<LightSource> lights = new LinkedList<>();

	/**
	 * set the name of the scene
	 * 
	 * @param sceneName for the name of the scene
	 */
	public Scene(String sceneName) {
		name = sceneName;
	}

	/**
	 * set the background of the scene
	 * 
	 * @param color for the color of the background
	 * @return this scene
	 */
	public Scene setBackground(Color color) {
		background = color;
		return this;
	}

	/**
	 * set the ambientLight of the scene
	 * 
	 * @param ambientLight for the ambientLight of the scene
	 * @return this scene
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * set the geometries of the scene
	 * 
	 * @param geometries for the geometries of the scene
	 * @return this scene
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

	/**
	 * set the Lights of the scene
	 * 
	 * @param lights for the lights of the scene
	 * @return this scene
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
}
