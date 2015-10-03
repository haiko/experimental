/**
 *
 */
package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;


/**
 * Opladen van de {@link OvcpKaart}.
 * 
 * @author haiko
 * 
 */
public class OplaadTransactie extends LaadTransactie {

    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 7835761093674474693L;

    public OplaadTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, BigDecimal bedrag, OVChipEvent oplaadMoment) {
        super(chipId, sequenceNumber, dateCreated, bedrag, oplaadMoment);
    }

}
