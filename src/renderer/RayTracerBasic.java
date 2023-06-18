package renderer;

import primitives.*;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

import static primitives.Util.alignZero;
import scene.Scene;

/**
 * This department will handle tracking the rays and returning their color
 * 
 * @author mendy
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * constructor by calling super
	 * 
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
		return calcColor(closestGeoPoint, ray);

	}

	private Color calcColor(GeoPoint gp, Ray ray) {
		return scene.ambientLight.getIntensity().add(calcLocalEffects(gp, ray));
	}

	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Vector vector = ray.getDir();
		Vector normal = gp.geometry.getNormal(gp.point);
		double nv = alignZero(normal.dotProduct(vector));
		if (nv == 0)
			return Color.BLACK;
		Material material = gp.geometry.getMaterial();

		Color color = gp.geometry.getEmission();
		for (LightSource lightSource : scene.lights) {
			Vector lightVector = lightSource.getL(gp.point);
			double nl = alignZero(normal.dotProduct(lightVector));
			if (nl * nv > 0) {
				Color lightIntensity = lightSource.getIntensity(gp.point);
				color = color.add(lightIntensity.scale(calcDiffusive(material, nl) //
						.add(calcSpecular(material, normal, lightVector, nl, vector))));
			}
		}
		return color;
	}

	private Double3 calcDiffusive(Material material, double nl) {
		return material.kD.scale(Math.abs(nl));
	}

	private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
		Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
		double minusVR = -vector.dotProduct(reflectedVector);
		return alignZero(minusVR) <= 0 ? Double3.ZERO //
				: material.kS.scale(Math.pow(minusVR, material.nShininess));

	}

}
