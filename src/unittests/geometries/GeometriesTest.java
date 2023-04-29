/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * @author mendy
 *
 */
class GeometriesTest {

	/**
	 * Test method for {@link geometries.Geometries#Geometries()}.
	 */
	@Test
	void testGeometries() {
		// ============ Equivalence Partitions Tests ==============
		assertDoesNotThrow(() -> new Geometries(), "Failed constructing a correct Geometries");
	}

	/**
	 * Test method for
	 * {@link geometries.Geometries#Geometries(geometries.Intersectable[])}.
	 */
	@Test
	void testIntersectableArray() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link geometries.Geometries#add(geometries.Intersectable[])}.
	 */
	@Test
	void testAdd() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
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
		// TC01: ray intersects some of the  geometries - sphere and plane
		Ray ray1 = new Ray(new Point(0, 2, 0), new Vector(0, 1, 0));
		assertEquals(3, geometries.findIntsersections(ray1).size());

		// =============== Boundary Values Tests ==================
		// TC01: ray does not intersects any geometry
		Ray ray2 = new Ray(new Point(1, 1, -1), new Vector(0, 0, -1));
		assertNull(geometries.findIntsersections(ray2),"Wrong. 0 intersections");

		// TC02: ray intersects 1 geometry - sphere
		Ray ray3 = new Ray(new Point(0, 5, -2), new Vector(0, 0, 1));
		assertEquals(2, geometries.findIntsersections(ray3).size(),"Wrong. 1 intersection");

		// TC03: ray intersects all geometries
		Ray ray4 = new Ray(new Point(0.5d, -1, 0.5d), new Vector(0, 1, 0));
		assertEquals(4, geometries.findIntsersections(ray4).size(),"Wrong. 4 intersections");
		
		// TC04: ray intersects some of the  geometries - sphere and plane
		assertNull(empty.findIntsersections(ray4),"Wrong. 0 intersections");
		
		
		
	}
}
