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
	
	Scene()

}
