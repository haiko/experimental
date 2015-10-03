package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;


/**
 * Een  {@link CoReisTransactieTest}  beschrijft een CoReis die met de  {@link OvcpKaart}  is gedaan.
 * @author haiko
 */
public class CoReisTransactie extends ReisTransactie {

    /**
     * Generated serialVersionID.
     */
    private static final long serialVersionUID = 8652719012326672701L;

    /**
	 * @uml.property  name="vertrek"
	 * @uml.associationEnd  
	 */
    private OVChipEvent vertrek;

    /**
	 * @uml.property  name="aankomst"
	 * @uml.associationEnd  
	 */
    private OVChipEvent aankomst;

    public CoReisTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, OVChipEvent vertrek, OVChipEvent aankomst,
            BigDecimal prijs) {
        super(chipId, sequenceNumber, dateCreated, prijs);
        this.vertrek = vertrek;
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
        CoReisTransactie other = (CoReisTransactie) obj;
        return new EqualsBuilder().append(this.vertrek, other.vertrek).append(this.aankomst, other.aankomst).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.aankomst).append(this.vertrek).hashCode();
    }

    /**
	 * @return
	 * @uml.property  name="vertrek"
	 */
    public OVChipEvent getVertrek() {
        return vertrek;
    }

    /**
	 * @return
	 * @uml.property  name="aankomst"
	 */
    public OVChipEvent getAankomst() {
        return aankomst;
    }
}
