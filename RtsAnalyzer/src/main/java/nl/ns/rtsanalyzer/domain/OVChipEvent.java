/**
 *
 */
package nl.ns.rtsanalyzer.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;


/**
 * Tijd en plaats waar men incheckt, uitcheck of oplaadt met OVChip.
 * @author  haiko
 */
public class OVChipEvent implements Serializable {

    /**
	 * Tijdstip reis.
	 * @uml.property  name="tijdstip"
	 */
    private DateTime tijdstip;

    /**
	 * Beschrijving van de lokatie.
	 * @uml.property  name="lokatie"
	 */
    private String lokatie;

    public OVChipEvent(DateTime tijdstip, String lokatie) {
        this.lokatie = lokatie;
        this.tijdstip = tijdstip;
    }
    
    public OVChipEvent() {
	}

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof OVChipEvent) {
            OVChipEvent other = (OVChipEvent) obj;
            return new EqualsBuilder().append(this.tijdstip, other.tijdstip).append(this.lokatie, other.lokatie).isEquals();
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.lokatie).append(this.tijdstip).toHashCode();
    }

    /**
	 * @return  the tijdstip
	 * @uml.property  name="tijdstip"
	 */
    public DateTime getTijdstip() {
        return tijdstip;
    }

    /**
	 * @return  the lokatie
	 * @uml.property  name="lokatie"
	 */
    public String getLokatie() {
        return lokatie;
    }
}
