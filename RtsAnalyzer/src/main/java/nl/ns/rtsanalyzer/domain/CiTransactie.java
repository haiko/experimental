package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;


/**
 * Een  {@link CiTransactie}  beschrijft een Check-in die met de  {@link OvcpKaart}  is gedaan.
 * @author  haiko
 */
public class CiTransactie extends ReisTransactie {

    /**
     * Generated serialVersionID.
     */
    private static final long serialVersionUID = -2716950259347918251L;

    /**
	 * @uml.property  name="vertrek"
	 * @uml.associationEnd  
	 */
    private OVChipEvent vertrek;

    public CiTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, OVChipEvent vertrek, BigDecimal prijs) {
        super(chipId, sequenceNumber, dateCreated, prijs);
        this.vertrek = vertrek;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        CiTransactie other = (CiTransactie) obj;
        return new EqualsBuilder().append(this.vertrek, other.vertrek).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.vertrek).hashCode();
    }

    /**
	 * @return
	 * @uml.property  name="vertrek"
	 */
    public OVChipEvent getVertrek() {
        return vertrek;
    }

}
