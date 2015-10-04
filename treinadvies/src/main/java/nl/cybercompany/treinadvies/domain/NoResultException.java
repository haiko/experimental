/**
 *
 */
package nl.cybercompany.treinadvies.domain;

/**
 * Geen resultaten gevonden.
 *
 * @author haiko
 *
 */
public class NoResultException extends Exception {

	public NoResultException(String s) {
		super(s);
	}

	public NoResultException(String s, Exception e) {
		super(s,e);
	}

}
