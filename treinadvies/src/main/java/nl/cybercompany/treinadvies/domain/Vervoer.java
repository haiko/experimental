/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import java.io.Serializable;

/**
 * @author haiko
 *
 */
public class Vervoer implements Serializable {

	private String type;

	private String vervoerder;

	public static Vervoer create(String type, String vervoer){
		return new Vervoer(type, vervoer);
	}

	private Vervoer(String type, String vervoerder){
		this.type = type;
		this.vervoerder = vervoerder;
	}

	public String getType() {
		return type;
	}

	public String getVervoerder() {
		return vervoerder;
	}
}
