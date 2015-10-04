/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import static nl.cybercompany.treinadvies.util.Checks.nullCheck;

import java.io.Serializable;
import java.util.List;

/**
 * Reis van A naar B zonder tussenstop.
 *
 *
 * @author haiko
 *
 */
public class ReisDeel implements Serializable{


	private List<Punt> reisPunten;

	private Vervoer vervoer;


	ReisDeel(List<Punt> punten, Vervoer vervoer) {
		nullCheck(punten);
		if(punten.size()<2){
			throw new IllegalStateException("Reisdeel moet altijd minimaal twee punten hebben");
		}
		this.reisPunten = punten;
		this.vervoer =  vervoer;

	}

	public static ReisDeel createReisDeel(List<Punt> punten, Vervoer vervoer) {
		return new ReisDeel(punten, vervoer);
	}

	public Punt getVertrek() {
		return reisPunten.get(0);
	}

	public Punt getAankomst() {
		return reisPunten.get(reisPunten.size() -1);
	}

	public List<Punt> getReisPunten() {
		return reisPunten;
	}

	public Vervoer getVervoer() {
		return vervoer;
	}
}
