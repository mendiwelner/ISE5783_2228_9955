/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.*;
import org.junit.jupiter.api.Test;

import geometries.Plane;

/**
 * @author mendy
 *
 */
class PlaneTest {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point, primitives.Vector)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct plane
		try {
			new Plane(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: wrong plane - point 1 and 2 equal
		// =============== Boundary Values Tests ==================
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0)));

		// TC03: wrong plane - all points on the same vector
		// =============== Boundary Values Tests ==================
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)));


	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here - using a quad
		Point p1 = new Point(0, 0, 1);
		Point p2 = new Point(0, 1, 0);
		Point p3 = new Point(1, 0, 0);
		Plane pl = new Plane(p1, p2, p3);
		// ensure there are no exceptions
		assertDoesNotThrow(() -> pl.getNormal());
		// generate the test result
		Vector result = pl.getNormal();
		// ensure |result| = 1
		assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
		// ensure the result is orthogonal to all the edges
			assertTrue(isZero(result.dotProduct(p1.subtract(p2))),
					"Polygon's normal is not orthogonal to one of the edges");
			assertTrue(isZero(result.dotProduct(p1.subtract(p3))),
					"Polygon's normal is not orthogonal to one of the edges");
			assertTrue(isZero(result.dotProduct(p2.subtract(p3))),
					"Polygon's normal is not orthogonal to one of the edges");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here - using a quad
		Point p1 = new Point(0, 0, 0);
		Point p2 = new Point(1, 0, 0);
		Point p3 = new Point(0, 1, 0);
		Point p = new Point(2, 1, 0);
		Plane pl = new Plane(p1, p2, p3);
		// ensure there are no exceptions
		assertDoesNotThrow(() -> pl.getNormal(p));
		// generate the test result
		Vector result = pl.getNormal(p);
		// ensure |result| = 1
		assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
		// ensure the result is orthogonal to all the edges
			assertTrue(isZero(result.dotProduct(p1.subtract(p2))),
					"Polygon's normal is not orthogonal to one of the edges");
			assertTrue(isZero(result.dotProduct(p1.subtract(p3))),
					"Polygon's normal is not orthogonal to one of the edges");
			assertTrue(isZero(result.dotProduct(p2.subtract(p3))),
					"Polygon's normal is not orthogonal to one of the edges");
	}

}
