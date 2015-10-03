package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;


/**
 * Opladen van de  {@link OvcpKaart} .
 * @author  haiko
 */
public abstract class LaadTransactie extends OVChipkaartTransactie {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = -489681824393108265L;

    /**
	 * Bedrag in EUR bij OVCPSaldo.
	 * @uml.property  name="bedrag"
	 */
    private BigDecimal bedrag;

    /**
	 * Plek en tijdstip van opladen.
	 * @uml.property  name="oplaadMoment"
	 * @uml.associationEnd  
	 */
    private OVChipEvent oplaadMoment;

    public LaadTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, BigDecimal bedrag, OVChipEvent oplaadMoment) {
        super(chipId, sequenceNumber, dateCreated);
        this.bedrag = bedrag;
        this.oplaadMoment = oplaadMoment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see nl.ns.iss.domain.reishistorie.OVChipkaartTransactie#equals(java.lang.Object)
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
        LaadTransactie other = (LaadTransactie) obj;
        return new EqualsBuilder().append(this.oplaadMoment, other.oplaadMoment).append(this.bedrag, other.bedrag).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see nl.ns.iss.domain.reishistorie.OVChipkaartTransactie#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.oplaadMoment).append(this.bedrag).toHashCode();
    }

    /**
	 * @return  the bedrag
	 * @uml.property  name="bedrag"
	 */
    public BigDecimal getBedrag() {
        return bedrag;
    }

    /**
	 * @return  the oplaadMoment
	 * @uml.property  name="oplaadMoment"
	 */
    public OVChipEvent getOplaadMoment() {
        return oplaadMoment;
    }

}
