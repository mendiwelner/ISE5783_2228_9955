/**
 * 
 */
package renderer;

import primitives.*;
import java.util.List;
import scene.Scene;

/**
 * @author mendy
 *
 */
public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<Point> intersections = scene.geometries.findIntersections(ray);
		if (intersections == null)
			return scene.background;
		return calcColor(ray.findClosestPoint(intersections));

	}

	private Color calcColor(Point p) {
		return scene.ambientLight.getIntensity();
	}
}
