/**
 * 
 */
package scene;

import primitives.*;
import geometries.Geometries;
import lighting.*;

/**
 * @author mendy
 *
 */
public class Scene {

	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = AmbientLight.NONE;
	public Geometries geometries = new Geometries();

	public Scene(String sceneName) {
		name = sceneName;
	}

	public Scene setBackground(Color color) {
		background = color;
		return this;
	}

	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

}
