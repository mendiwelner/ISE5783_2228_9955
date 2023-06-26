package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic is a basic ray tracer, it extends RayTracerBase and implements
 * the traceRay function
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 * 
 */

public class RayTracerBasic extends RayTracerBase {

	/* the initial k for the recursion */
	private static final Double3 INITIAL_K = Double3.ONE;
	/* the max level of recursion */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/* the minimum k for the recursion */
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * constructor to build the scene
	 * 
	 * @param scene we build the scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	/**
	 * This function calculates the color in a point
	 * 
	 * @param closestPoint the intersection point
	 * @ray incoming ray
	 * @return the background color for now
	 */
	private Color calcColor(GeoPoint closestPoint, Ray ray) {
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * This function calculates the color in a point
	 * 
	 * @param intersection point of intersection
	 * @param ray          intersects the geometry
	 * @param level        of intersections
	 * @param k            for calculating the global effects
	 * @return Color of the point
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;

		Color color = calcLocalEffects(intersection, v, n, nv, k);
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, v, n, nv, level, k));
	}

	/**
	 * This function calculates the global effects in the scene
	 * 
	 * @param gp    - the intersection point
	 * @param v     The direction of the ray that intersected the geometry at the
	 *              GeoPoint.
	 * @param n     normal to the geometry surface at the intersection point
	 * @param nv    dot-product of (n,v)
	 * @param level - the level of recursion
	 * @param k     - the k for the recursion
	 * @return the global effects on the intersection point
	 */
	private Color calcGlobalEffects(GeoPoint gp, Vector v, Vector n, double nv, int level, Double3 k) {
		Material material = gp.geometry.getMaterial();
		return calcGlobalEffect(constructReflectedRay(gp, v, n, nv), level, k, material.kR)
				.add(calcGlobalEffect(constructRefractedRay(gp, v, n), level, k, material.kT));
	}

	/**
	 * different method for the global effects, for the reflected rays
	 *
	 * @param ray   - the ray
	 * @param level - the level of recursion
	 * @param k     - the k for the recursion
	 * @param kx    - the k for the specific effect
	 * @return the global effects on the intersection point
	 */
	private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
		Double3 kkx = k.product(kx);
		if (kkx.lowerThan(MIN_CALC_COLOR_K))
			return Color.BLACK;

		GeoPoint gp = findClosestIntersection(ray);
		return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}

	/**
	 * Constructs a refracted ray based on the given GeoPoint, incident vector, and
	 * surface normal.
	 *
	 * @param gp The GeoPoint representing the intersection point.
	 * @param v  The incident vector.
	 * @param n  The surface normal.
	 * @return The refracted ray.
	 */
	private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
		return new Ray(gp.point, n, v);
	}

	/**
	 * Constructs a reflected ray based on the given GeoPoint, incident vector, and
	 * surface normal.
	 *
	 * @param gp The GeoPoint representing the intersection point.
	 * @param v  The incident vector.
	 * @param n  The surface normal.
	 * @param vn dot-product of (n,v)
	 * @return The reflected ray.
	 */
	private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n, double vn) {
		// r = v - 2.(v.n).n
		Vector r = v.subtract(n.scale(2 * vn));
		return new Ray(gp.point, n, r);
	}

	/**
	 * Finds the closest intersection of the given ray with the geometries in the
	 * scene.
	 *
	 * @param ray The ray to find intersections with.
	 * @return The closest GeoPoint intersection.
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		return intersections == null ? null : ray.findClosestGeoPoint(intersections);
	}

	/**
	 * TODO ???????????
	 * 
	 * @param gp - the intersection point
	 * @param ,  the light source
	 * @param -  the light vector
	 * @param -  the normal vector
	 * @param -  the dot product of n and l, used in calcLocalEffects
	 * @return - true if the point is unshaded, false otherwise
	 */
	@SuppressWarnings("unused")
	private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl) {
		Vector lightDirection = l.scale(-1);
		// from point to light source
		Ray lightRay = new Ray(gp.point, n, lightDirection);
		// ray to check shading
		var intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return true;

		double lightDistance = lightSource.getDistance(gp.point);
		// if there are points in the intersections list that are closer to the point
		// than lightDistance, the point is shaded
		for (GeoPoint geoPoint : intersections)
			if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0
					&& geoPoint.geometry.getMaterial().kT == Double3.ZERO)
				return false;
		return true;
	}

	/**
	 * Calculates the local effects of the light sources on a specific intersection
	 * point.
	 *
	 * @param gp The GeoPoint representing the intersection point.
	 * @param v  The direction of the ray that intersected the geometry at the
	 *           GeoPoint.
	 * @param n  normal to the geometry surface at the intersection point
	 * @param nv dot-product of (n,v)
	 * @param k  The transparency factor.
	 * @return The color after applying the local effects of the light sources.
	 */
	private Color calcLocalEffects(GeoPoint gp, Vector v, Vector n, double nv, Double3 k) {
		Color color = gp.geometry.getEmission();
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Double3 ktr = transparency(gp, lightSource, l, n, nl);
				if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
					Color iL = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(iL.scale(calcDiffusive(material, nl)),
							iL.scale(calcSpecular(material, n, l, nl, v)));
				}
			}
		}
		return color;
	}

	/**
	 * Calculates the diffuse reflection factor based on the material and the dot
	 * product of the surface normal and light direction.
	 *
	 * @param material The material of the intersected geometry.
	 * @param nl       The dot product of the surface normal and light direction.
	 * @return The diffuse reflection factor.
	 */
	private Double3 calcDiffusive(Material material, double nl) {
		return material.kD.scale(nl < 0 ? -nl : nl);
	}

	/**
	 * Calculates the specular reflection component of a material at a given
	 * intersection point.
	 *
	 * @param material The material of the intersected geometry
	 * @param n        The surface normal
	 * @param l        The light direction
	 * @param nl       The dot product between the surface normal and the light
	 *                 direction
	 * @param v        The view direction
	 * @return The color of the specular reflection
	 */
	private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
		Vector r = l.subtract(n.scale(2 * nl));
		double minusRV = alignZero(r.dotProduct(v));
		return minusRV <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(minusRV, material.nShininess));
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

		// loop over intersections and for each intersection which is closer to the
		// point than the light source multiply ktr by kT of its geometry
		Double3 ktr = Double3.ONE;
		var distanceToLight = light.getDistance(gp.point);
		for (GeoPoint geoPoint : intersections) {
			if (alignZero(geoPoint.point.distance(gp.point) - distanceToLight) <= 0) {
				ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
				if (ktr.lowerThan(MIN_CALC_COLOR_K))
					return Double3.ZERO;
			}
		}

		return ktr; // returns the transparency of the point
	}
}