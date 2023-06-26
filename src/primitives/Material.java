package primitives;

/**
 * Material class will handle the affect of the Material on the image
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */

public class Material {
	/** the kD of the material */
	public Double3 kD = Double3.ZERO;
	/** the kS of the material */
	public Double3 kS = Double3.ZERO;
	/** the transparency of the material */
	public Double3 kT = Double3.ZERO;
	/** the reflection of the material */
	public Double3 kR = Double3.ZERO;
	/** the shininess of the material */
	public int nShininess = 1;

	/**
	 * sets the kD of the material
	 * 
	 * @param kD for the material (type Double3)
	 * @return the material
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * sets the kD of the material
	 * 
	 * @param kD for the material (type Double)
	 * @return the material
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	
	/**
	 * sets the kS of the material
	 * 
	 * @param kS for the material (type Double3)
	 * @return the material
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * sets the kS of the material
	 * 
	 * @param kS for the material (type Double)
	 * @return the material
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * sets the kT of the material
	 * 
	 * @param kT for the material (type Double3)
	 * @return the material
	 */
	public Material setKt(Double3 kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * sets the kT of the material
	 * 
	 * @param kT for the material (type Double)
	 * @return the material
	 */
	public Material setKt(double kT) {
		this.kT = new Double3(kT);
		return this;
	}

	/**
	 * sets the kR of the material
	 * 
	 * @param kR for the material (type Double3)
	 * @return the material
	 */
	public Material setKr(Double3 kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * sets the kR of the material
	 * 
	 * @param kR for the material (type Double)
	 * @return the material
	 */
	public Material setKr(double kR) {
		this.kR = new Double3(kR);
		return this;
	}
	
	/**
	 * sets the Shininess of the material
	 * 
	 * @param shininess for the material
	 * @return the material
	 */
	public Material setShininess(int shininess) {
		this.nShininess = shininess;
		return this;
	}
}
