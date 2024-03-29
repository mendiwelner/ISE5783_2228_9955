package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * Testing Geometries
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 *
 */
class GeometriesTest {

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntsersections() {
		// ============ Equivalence Partitions Tests ==============
		Triangle trg = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 3));
		Sphere sphere = new Sphere(1, new Point(0, 5, 0));
		Plane plane = new Plane(new Point(0, 8, 0), new Vector(0, 1, 0));
		Geometries geometries = new Geometries(trg, sphere, plane);
		Geometries empty = new Geometries();
		// ============ Equivalence Partitions Tests ==============
		// TC01: ray intersects some of the geometries - sphere and plane
		Ray ray1 = new Ray(new Point(0, 2, 0), new Vector(0, 1, 0));
		assertEquals(3, geometries.findIntersections(ray1).size());

		// =============== Boundary Values Tests ==================
		// TC01: ray does not intersects any geometry
		Ray ray2 = new Ray(new Point(1, 1, -1), new Vector(0, 0, -1));
		assertNull(geometries.findIntersections(ray2), "Wrong. 0 intersections");

		// TC02: ray intersects 1 geometry - sphere
		Ray ray3 = new Ray(new Point(0, 5, -2), new Vector(0, 0, 1));
		assertEquals(2, geometries.findIntersections(ray3).size(), "Wrong. 1 intersection");

		// TC03: ray intersects all geometries
		Ray ray4 = new Ray(new Point(0.5d, -1, 0.5d), new Vector(0, 1, 0));
		assertEquals(4, geometries.findIntersections(ray4).size(), "Wrong. 4 intersections");

		// TC04: ray intersects some of the geometries - sphere and plane
		assertNull(empty.findIntersections(ray4), "Wrong. 0 intersections");

	}
}
