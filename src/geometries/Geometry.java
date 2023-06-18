package geometries;

import primitives.Vector;

import primitives.Color;
import primitives.Material;
import primitives.Point;

/**
 * The abstract class Geometry will serve as the representation of a general
 * shape
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */

public abstract class Geometry extends Intersectable {

	/** the color of the emission, initialize to black */
	private Color emission = Color.BLACK;
	/** the material of the Geometry*/
	private Material material = new Material();

	/**
	 * This function returns the normal vector at the given point on the shell of
	 * general geometry
	 * 
	 * @param p the point on the surface of the geometry
	 * @return the normal of the shape at that point
	 */
	public abstract Vector getNormal(Point p);

	/**
	 * This function returns the Color of the emission
	 * 
	 * @return the Color of the emission
	 */
	public Color getEmission() {
		return this.emission;
	}

	/**
	 * This function sets the Color of the emission
	 * 
	 * @param emission for the emission
	 * @return this geometry
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * This function returns the material of the geometry
	 * 
	 * @return the material of the geometry
	 */
	public Material getMaterial() {
		return this.material;
	}

	/**
	 * This function sets the material of the geometry
	 * 
	 * @param material for the material of the geometry
	 * @return this geometry
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

}
