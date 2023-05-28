package geometries;

import primitives.Vector;

import java.awt.Color;

import primitives.Point;

/**
 * The abstract class Geometry will serve as the representation of a general shape
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */

public abstract class Geometry extends Intersectable {

	/**the color of the emission, initialize to black*/
	protected Color emission = Color.BLACK;
	
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
	public abstract Color getEmission();
	
	/**
	 * This function sets the Color of the emission
	 * 
	 * @return this geometry
	 */
	public abstract Geometry setEmission( Color emission);
}
