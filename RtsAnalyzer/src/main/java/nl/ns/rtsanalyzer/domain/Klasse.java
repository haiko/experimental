package nl.ns.rtsanalyzer.domain;

import java.io.Serializable;

/**
 * Klasse van de transacties
 * @author   haiko
 */
public enum Klasse implements Serializable{

    /**
	 * @uml.property  name="eERSTE"
	 * @uml.associationEnd  
	 */
    EERSTE("1"), /**
	 * @uml.property  name="tWEEDE"
	 * @uml.associationEnd  
	 */
    TWEEDE("2");

    private String klasseNumber;
  
    private Klasse(String i) {
        this.klasseNumber = i;
    }

    public static Klasse fromValue(String v) {
        if(v.equals(EERSTE.klasseNumber)){
        	return EERSTE;
        }
        else {
        	return TWEEDE;
        }
    }

    /**
	 * @return the klasseNumber
	 */
	public String getKlasseNumber() {
		return klasseNumber;
	}   

}
