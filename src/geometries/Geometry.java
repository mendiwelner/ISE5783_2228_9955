package geometries;
import primitives.Vector;
import primitives.Point;

public interface Geometry {
	
    /**
     * @param point
     * @return the normal at that point
     */
	public Vector getNormal(Point p);
}
