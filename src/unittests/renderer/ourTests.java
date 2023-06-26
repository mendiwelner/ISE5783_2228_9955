package unittests.renderer;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;

import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * test class for our scene
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
public class ourTests {
	private Scene scene = new Scene("Test scene");

	/** Produce a picture of a sphere lighted by a spot light */
	@Test
	public void table() {
		// Define the camera parameters
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPSize(150, 150)
				.setVPDistance(1000);

		// Define the geometry objects in the scene
		scene.geometries.add(
				// Triangle 1
				new Triangle(new Point(-50, -50, -100), new Point(50, -50, -100), new Point(0, -30, -50))
						.setEmission(new Color(150, 10, 10))
						.setMaterial(new Material().setShininess(100).setKr(0.8).setKt(1)),

				// Triangle 2
				new Triangle(new Point(0, -30, -50), new Point(50, -50, -100), new Point(-10, 50, -15))
						.setEmission(new Color(10, 150, 10))
						.setMaterial(new Material().setShininess(100).setKr(0.8).setKt(1)),

				// Triangle 3
				new Triangle(new Point(0, -30, -50), new Point(-50, -50, -100), new Point(-10, 50, -15))
						.setEmission(new Color(10, 10, 150))
						.setMaterial(new Material().setShininess(100).setKr(0.8).setKt(1)),

				// Triangle 4
				new Triangle(new Point(-50, -50, -100), new Point(50, -50, -100), new Point(-10, 50, -15))
						.setEmission(new Color(100, 100, 20)).setMaterial(new Material().setKr(0.2).setKt(0)),

				// Sphere
				new Sphere(10d, new Point(0, -20, -65)).setEmission(new Color(200, 20, 200))
						.setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(50)),

				// Tube 1
				new Tube(4, new Ray(new Point(-10, 0, -55), new Vector(1, 0, 0))).setEmission(new Color(150, 20, 220))
						.setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(50)),

				// Tube 2
				new Tube(8, new Ray(new Point(-10, 20, -80), new Vector(1, 0, 0))).setEmission(new Color(100, 50, 250))
						.setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(100)));

		// Add a spot light to the scene
		scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
				.setKl(0.0004).setKq(0.0000006));

		// Render the image using the specified camera and write it to a file
		camera.setImageWriter(new ImageWriter("table", 500, 500)).setRayTracer(new RayTracerBasic(scene)).renderImage()
				.writeToImage();
	}

}
