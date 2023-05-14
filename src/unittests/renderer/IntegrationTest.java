package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import renderer.Camera;
import primitives.*;

public class IntegrationTest {

	@Test
	void testSphere() {

		// ============ Equivalence Partitions Tests ==============
		// EP01: sphere is across the central pixel of view plane - 2 intersections
		Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
		Sphere sphere = new Sphere(1, new Point(0, 0, -3));
		LinkedList<Ray> rayList = pixelRays(camera.setVPSize(3, 3), 3, 3);
		assertEquals(2, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// EP01: sphere is across the whole view plane - 18 intersections
		sphere = new Sphere(2.5, new Point(0, 0, -2.5));
		camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
		rayList = pixelRays(camera.setVPSize(3, 3), 3, 3);
		assertEquals(18, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// EP01: sphere is across the view plane - 10 intersections
		sphere = new Sphere(2, new Point(0, 0, -2));
		assertEquals(10, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// EP01: view plane is inside sphere - 18 intersections
		sphere = new Sphere(4, new Point(0, 0, -2));
		assertEquals(9, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// EP01: sphere is behind view plane - 0 intersections
		sphere = new Sphere(0.5, new Point(0, 0, 1));
		assertEquals(0, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

	}

	private LinkedList<Ray> pixelRays(Camera camera, int nX, int nY) {

		LinkedList<Ray> rays = new LinkedList<Ray>();
		for (int i = 0; i < nX; i++)
			for (int j = 0; j < nY; j++)
				rays.add(camera.constructRay(nX, nY, j, i));

		return rays;
	}

	private int countIntersections(LinkedList<Ray> rays, Sphere sphere) {

		int counter = 0;
		List<Point> intersections = new LinkedList<Point>();
		for (Ray ray : rays) {
			intersections = sphere.findIntsersections(ray);
			if (intersections != null)
				counter += intersections.size();
		}

		return counter;
	}
}
