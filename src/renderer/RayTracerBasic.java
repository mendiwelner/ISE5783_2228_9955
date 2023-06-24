package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import static primitives.Util.alignZero;

/**
 * This department will handle tracking the rays and returning their color
 * 
 * @author mendy
 */
public class RayTracerBasic extends RayTracerBase {

	/** the initial k for the recursion */
	private static final Double3 INITIAL_K = Double3.ONE;
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
	 * @param closestPoint - the intersection point
	 * @return - the background color for now
	 */
	private Color calcColor(GeoPoint closestPoint, Ray ray) {
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.getAmbientLight().getIntensity());
	}

	/*
	 * @param intersection - the intersection point
	 * 
	 * @param ray - the ray
	 * 
	 * @param level - the level of recursion
	 * 
	 * @param k - the k for the recursion
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
		Color color = calcLocalEffects(intersection, ray, k);
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
	}

	// constructs the refracted ray
	private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
		return new Ray(gp.point, n, v);
	}

	// constructs the reflected ray
	private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
		// r = v - 2.(v.n).n
		double vn = v.dotProduct(n);
		if (vn == 0) {
			return null;
		}
		Vector r = v.subtract(n.scale(2 * vn));
		return new Ray(gp.point, n, r);
	}

	// finds the closest intersection point to the ray's starting point using
	// findGeoIntersections
	private GeoPoint findClosestIntersection(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		return ray.findClosestGeoPoint(intersections);
	}

	/**
	 * @param gp    - the intersection point
	 * @param ray   - the ray
	 * @param level - the level of recursion
	 * @param k     - the k for the recursion
	 * @return the global effects on the intersection point
	 */
	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = Color.BLACK;
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		return calcGlobalEffect(constructReflectedRay(gp, v, n), level, k, material.getKr())
				.add(calcGlobalEffect(constructRefractedRay(gp, v, n), level, k, material.getKt()));
	}

	/**
	 * different method for the global effects, for the reflected and refracted rays
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
		if (gp == null)
			return scene.getBackground().scale(kx);

		return Util.isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir())) ? Color.BLACK
				: calcColor(gp, ray, level - 1, kkx).scale(kx);
	}

	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
		Color color = gp.geometry.getEmission();
		Vector v = ray.getDir();
		Point point = gp.point;
		Vector n = gp.geometry.getNormal(point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return color;
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Double3 ktr = transparency(gp, lightSource, l, n, nl);
				if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
					Color iL = lightSource.getIntensity(point).scale(ktr);
					color = color.add(iL.scale(calcDiffusive(material, nl)),
							iL.scale(calcSpecular(material, n, l, nl, v)));
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
		double max = -r.dotProduct(v);
		return alignZero(max) > 0 ? material.kS.scale(Math.pow(max, material.getShininess())) : Double3.ZERO;
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
		// loop over intersections and for each intersection which is closer to the
		// point than the light source multiply ktr by 𝒌𝑻 of its geometry
		for (GeoPoint geoPoint : intersections) {
			if (alignZero(geoPoint.point.distance(gp.point) - light.getDistance(gp.point)) <= 0) {
				ktr = ktr.product(geoPoint.geometry.getMaterial().kD);
				if (ktr.equals(Double3.ZERO))
					break;
			}
		}

		return ktr; // returns the transparency of the point
	}
}
