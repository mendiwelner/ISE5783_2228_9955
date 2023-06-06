package renderer;
import primitives.Color;
import primitives.Ray;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import scene.Scene;

/**
 * This department will handle tracking the rays and returning their color
 * @author mendy
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * constructor by calling super
	 * @param scene for super
	 */
	public RayTracerBasic(Scene scene) { 
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		GeoPoint closestGeoPoint = ray.findClosestGeoPoint(intersections);
		return calcColor(closestGeoPoint);

	}

	private Color calcColor(GeoPoint p) {
		Color c = p.geometry.getEmission();
		return scene.ambientLight.getIntensity().add(c);
	}
}
