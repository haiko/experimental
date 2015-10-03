/**
 *
 */
package nl.ns.rtsanalyzer.domain;

/**
 * Kenmerk van der reis
 * @author   haiko
 */
public enum Kenmerk {

    /**
	 * @uml.property  name="pRIVE"
	 * @uml.associationEnd  
	 */
    PRIVE("Prive"), /**
	 * @uml.property  name="zAKELIJK"
	 * @uml.associationEnd  
	 */
    ZAKELIJK("Zakelijk");

    /**
	 * @uml.property  name="label"
	 */
    private String label;

    private Kenmerk(String label) {
        this.label = label;
    }

    /**
	 * @return
	 * @uml.property  name="label"
	 */
    public String getLabel() {
        return this.label;
    }
    
    public static Kenmerk getKenmerk(String name){
    	if(name.equalsIgnoreCase(ZAKELIJK.label)){
    		return ZAKELIJK;
    	}
    	else {
    		return PRIVE;
    	}
    }

}
