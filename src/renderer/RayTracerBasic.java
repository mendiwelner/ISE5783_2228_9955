package renderer;
import primitives.*;
import primitives.Ray;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

import java.util.List;
import static primitives.Util.alignZero;
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
		return calcColor(closestGeoPoint, ray);

	}

	private Color calcColor(GeoPoint gp, Ray ray) {
		Color c = calcLocalEffects(gp, ray);
		return scene.ambientLight.getIntensity().add(c); 
	}
	
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Color color = gp.geometry.getEmission();
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) return color;
		Material material = gp.geometry.getMaterial();
		
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color iL = lightSource.getIntensity(gp.point);
				color = color.add(iL.scale(calcDiffusive(material.kD, nl, iL)),
						iL.scale(calcSpecular(material, n, l, nl, v));
			}
			return color;
		}
	}
	
	private Color calcDiffusive(Double3 kD, double nl, Color iL) {
        // Calculate the diffuse coefficient by scaling the material's surface color with the dot product.
        Double3 diffuseCoefficient = kD.scale(Math.abs(nl));

        // Multiply the light intensity with the diffuse coefficient to obtain the diffusive color.
        Color diffuseColor = iL.scale(diffuseCoefficient);

        // Return the diffusive color.
        return diffuseColor;
    }
	
	private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl, Vector v, Color iL, int nShininess) {
        // Calculate the reflection vector using the surface normal and the light direction.
        Vector r = l.subtract(n.scale(nl * 2));

        // Calculate the dot product between the view direction and the reflection vector.
        double minusVR = -alignZero(v.dotProduct(r));

        // If the dot product is less than or equal to 0, return black color (no specular reflection).
        if (minusVR <= 0)
            return Color.BLACK;

        // Calculate the specular coefficient raised to the power of the shininess factor.
        Double3 shine = kS.scale(Math.pow(minusVR, nShininess));

        // Multiply the light intensity with the specular coefficient to obtain the specular color.
        return iL.scale(shine);
    }
	
}
