/**
 *
 */
package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;


/**
 * Een  {@link ReisTransactie}  beschrijft een reis die met de  {@link OvcpKaart}  is gedaan.
 * @author  haiko
 */
public abstract class ReisTransactie extends OVChipkaartTransactie {

    /**
     * Generated serialVersionID.
     */
    private static final long serialVersionUID = 7275816442275477517L;

    /**
	 * @uml.property  name="productNaam"
	 */
    protected String productNaam;

    /**
	 * Prijs ins EUR.
	 * @uml.property  name="prijs"
	 */
    protected BigDecimal prijs;

    /**
	 * @uml.property  name="klasse"
	 * @uml.associationEnd  
	 */
    protected Klasse klasse;

    public ReisTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, BigDecimal prijs) {
        super(chipId, sequenceNumber, dateCreated);
        this.prijs = prijs;
    }
    
    public ReisTransactie() {
	  super();
	}

    /**
	 * @return  the prijs
	 * @uml.property  name="prijs"
	 */
    public BigDecimal getPrijs() {
        return prijs;
    }

    /**
	 * @param prijs  the prijs to set
	 * @uml.property  name="prijs"
	 */
    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    /**
	 * @return  the productNaam
	 * @uml.property  name="productNaam"
	 */
    public String getProductNaam() {
        return productNaam;
    }

    /**
	 * @param productNaam  the productNaam to set
	 * @uml.property  name="productNaam"
	 */
    public void setProductNaam(String productNaam) {
        this.productNaam = productNaam;
    }

    /**
	 * @return  the klasse
	 * @uml.property  name="klasse"
	 */
    public Klasse getKlasse() {
        return klasse;
    }

    /**
	 * @param klasse  the klasse to set
	 * @uml.property  name="klasse"
	 */
    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }
}
