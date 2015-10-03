package nl.ns.rtsanalyzer.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;


public class AutomatischOplaadTransactie extends LaadTransactie {

    /**
     * Generated serialVersoinUID.
     */
    private static final long serialVersionUID = -2028141258559534699L;

    public AutomatischOplaadTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated, BigDecimal bedrag, OVChipEvent oplaadMoment) {
        super(chipId, sequenceNumber, dateCreated, bedrag, oplaadMoment);
    }

}
