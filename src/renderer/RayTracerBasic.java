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

	/** The magnitude of the initial displacement of the rays */
	private static final double DELTA = 0.1;
	/** The max calculation color level for the stop recursive function */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/** The minimum calculation color level for the stop recursive function */
	private static final double MIN_CALC_COLOR_K = 0.001;

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

	/**
	 * Calculates the color of a point in the scene by combining the ambient light
	 * intensity with the local effects at that point.
	 *
	 * @param gp  the GeoPoint representing the point in the scene
	 * @param ray the Ray representing the viewing ray
	 * @return the Color of the point in the scene
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return scene.ambientLight.getIntensity().add(calcLocalEffects(gp, ray));
	}

	/**
	 * Calculates the local effects at a given point in the scene, including diffuse
	 * and specular reflections.
	 * 
	 * @param gp  the GeoPoint representing the point in the scene
	 * @param ray the Ray representing the viewing ray
	 * @return the Color representing the local effects at the point
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Vector vector = ray.getDir();
		Vector normal = gp.geometry.getNormal(gp.point);
		double nv = alignZero(normal.dotProduct(vector));
		if (nv == 0)
			return Color.BLACK;
		Material material = gp.geometry.getMaterial();

		Color color = gp.geometry.getEmission();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(normal.dotProduct(l));
			if (nl * nv > 0) {
				{
					if (unshaded(gp, lightSource, l, normal, nl)) {
						Color lightIntensity = lightSource.getIntensity(gp.point);
						color = color.add(lightIntensity.scale(calcDiffusive(material, nl) //
								.add(calcSpecular(material, normal, l, nl, vector))));
					}
				}
				
			}
		}
		return color;
	}
	
	/**
	 * Calculates the diffusive reflection component for a given material and normal
	 * vector.
	 *
	 * @param material the Material of the object
	 * @param nl       the dot product between the normal vector and the light
	 *                 vector
	 * @return the diffusive reflection component as a Double3 vector
	 */
	private Double3 calcDiffusive(Material material, double nl) {
		return material.kD.scale(Math.abs(nl));
	}

	/**
	 * Calculates the specular reflection component for a given material, normal
	 * vector, light vector, and viewing vector.
	 *
	 * @param material    the Material of the object
	 * @param normal      the surface normal vector at the point
	 * @param lightVector the vector from the point to the light source
	 * @param nl          the dot product between the normal vector and the light
	 *                    vector
	 * @param vector      the viewing vector
	 * @return the specular reflection component as a Double3 vector
	 */
	private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
		Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
		double minusVR = -vector.dotProduct(reflectedVector);
		return alignZero(minusVR) <= 0 ? Double3.ZERO //
				: material.kS.scale(Math.pow(minusVR, material.nShininess));

	}

	/**
	 * Checks if a point on a surface is unshaded by performing a shadow ray test.
	 *
	 * @param gp the GeoPoint representing the point on the surface
	 * @param l  the vector from the point to the light source
	 * @param n  the surface normal vector at the point
	 * @return true if the point is unshaded, false otherwise
	 */
	private static final double EPS = 0.1;
	private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
		Vector lightDirection = l.scale(-1); // from Point to light source
		Vector epsVector = n.scale(nl < 0 ? EPS : -EPS);
		Point point = gp.point.add(epsVector);
		Ray lightRay = new Ray(point, lightDirection);
		var intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return true;
		double d = light.getDistance(point);
		Point rayHead = lightRay.getP0();
		for (var intersection : intersections) {
			if(intersection.point.distance(rayHead) < d) return false;
		}
		return true;
	}
	
	
	
	/**
     * calculates the transparency of the point
     *
     * @param gp    - the intersection point
     * @param light - the light source
     * @param l     - the light vector
     * @param n     - the normal vector
     * @param nl    - the dot product of n and l, used in calcLocalEffects
     * @return - the transparency of the point
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, n, lightDirection); // ray to check shading
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        //loop over intersections and for each intersection which is closer to the
        //point than the light source multiply ktr by 𝒌𝑻 of its geometry
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - light.getDistance(gp.point)) <= 0) {
                ktr = ktr.product(geoPoint.geometry.getMaterial().kD);
                if (ktr.equals(Double3.ZERO))
                    break;
            }
        }

        return ktr; //returns the transparency of the point
    }
}
	
	
	
	
	
	
	
	
	
	
	
	

}
