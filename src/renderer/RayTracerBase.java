package renderer;
import primitives.*;


import scene.Scene;

/**
 * This department will handle tracking the rays and returning their color
 * @author mendy
 */
public abstract class RayTracerBase {

	protected final Scene scene;

	/**
	 * Constructor to create a new RayTracerBase object 
	 * @param scene for the scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * the color of the traceRay
	 * @param ray for ray
	 */
	public abstract Color traceRay(Ray ray);

}
