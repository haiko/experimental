package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;


/**
 * Een  {@link CoTransactie}  beschrijft een Check-out die met de  {@link OvcpKaart}  is gedaan.
 * @author  haiko
 */
public class CoTransactie extends ReisTransactie {

    /**
     * Generated serialVersionID.
     */
    private static final long serialVersionUID = -7402530304524580136L;

    /**
	 * @uml.property  name="aankomst"
	 * @uml.associationEnd  
	 */
    private OVChipEvent aankomst;

    public CoTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, OVChipEvent aankomst, BigDecimal prijs) {
        super(chipId, sequenceNumber, dateCreated, prijs);
        this.aankomst = aankomst;
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
        CoTransactie other = (CoTransactie) obj;
        return new EqualsBuilder().append(this.aankomst, other.aankomst).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.aankomst).hashCode();
    }

    /**
	 * @return
	 * @uml.property  name="aankomst"
	 */
    public OVChipEvent getAankomst() {
        return aankomst;
    }

}
