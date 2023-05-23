/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 *
 */
class RayTest {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Ray ray = new Ray(new Point(0,0,1),new Vector(0,0,2));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Positive scalar
		assertEquals(new Point(0,0,2),ray.getPoint(1),"ERROR. wrong point");	
		// TC02: Negative scalar
		assertEquals(new Point(0,0,-1),ray.getPoint(-2),"ERROR. wrong point");
		// =============== Boundary Values Tests ==================
		// TC03: Zero scalar
		assertEquals(new Point(0,0,1),ray.getPoint(0),"ERROR. wrong point");
	}
	
	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testClosestPoint() {
		Ray ray = new Ray(new Point(0,0,1),new Vector(0,0,2));
		// ============ Equivalence Partitions Tests ==============
		// TC01: point in the middle of list
		List<Point> points1 = Arrays.asList(new Point(1,2,3),new Point(0,1,2),new Point(5,6,7));
		assertEquals(new Point(0,1,2), ray.findClosestPoint(points1),"wrong point");
		
		// =============== Boundary Values Tests ==================
		// TC01: empty list
		List<Point> points2 = new ArrayList();
		assertEquals(null, ray.findClosestPoint(points2),"wrong. list is empty");
		
		// TC02: first point of the list
		List<Point> points3 = Arrays.asList(new Point(0,0,2),new Point(0,0,3),new Point(0,0,4));
		assertEquals(new Point(0,0,2), ray.findClosestPoint(points3),"wrong point");
		
		// TC03: last point of the list
		List<Point> points4 = Arrays.asList(new Point(0,4,1),new Point(0,3,1),new Point(0,1,1));
		assertEquals(new Point(0,1,1), ray.findClosestPoint(points4),"wrong point");
		
	}

}
