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
	 * gets the kD of the material
	 * 
	 * @return the kD
	 */
	public Double3 getKd() {
		return this.kD;
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
	 * gets the kS of the material
	 * 
	 * @return the kS
	 */
	public Double3 getKs() {
		return this.kS;
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
	 * gets the kT of the material
	 * 
	 * @return the kT
	 */
	public Double3 getKt() {
		return this.kT;
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
	 * gets the kR of the material
	 * 
	 * @return the kR
	 */
	public Double3 getKr() {
		return this.kR;
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
	
	/**
	 * gets the Shininess of the material
	 * 
	 * @return the shininess
	 */
	public int getShininess() {
		return this.nShininess;
	}

}
