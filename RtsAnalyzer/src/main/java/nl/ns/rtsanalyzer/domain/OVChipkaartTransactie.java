package nl.ns.rtsanalyzer.domain;

import java.io.Serializable;
import java.math.BigInteger;

import org.joda.time.DateTime;


/**
 * A transaction of the  {@link OvcpKaart}
 * @author  haiko
 */
public abstract class OVChipkaartTransactie implements Serializable {
	
	
	/**
	 * Persistency identifier.
	 * @uml.property  name="id"
	 */
	private Long id;

    /**
	 * Kenmerk, kan null zijn
	 * @uml.property  name="kenmerk"
	 * @uml.associationEnd  
	 */
    protected Kenmerk kenmerk;

    /**
	 * @uml.property  name="chipId"
	 */
    protected BigInteger chipId;

    /**
	 * @uml.property  name="sequenceNumber"
	 */
    protected Integer sequenceNumber;

    /**
	 * @uml.property  name="dateCreated"
	 */
    protected DateTime dateCreated;

    /**
	 * Can be maximum 100 characters long.
	 * @uml.property  name="notitie"
	 */
    protected String notitie;

    public OVChipkaartTransactie(BigInteger chipId, Integer sequenceNumber, DateTime dateCreated) {
        super();
        this.chipId = chipId;
        this.sequenceNumber = sequenceNumber;
        this.dateCreated = dateCreated;
    }
    
    OVChipkaartTransactie(){
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder("OVCPTransactie|").append("chipid:").append(chipId).append("|sequencenumber").append(sequenceNumber).append(
                "|created:").append(dateCreated).toString();
    }

    /**
	 * @return  the kenmerk
	 * @uml.property  name="kenmerk"
	 */
    public Kenmerk getKenmerk() {
        return kenmerk;
    }

    /**
	 * @param kenmerk  the kenmerk to set
	 * @uml.property  name="kenmerk"
	 */
    public void setKenmerk(Kenmerk kenmerk) {
        this.kenmerk = kenmerk;
    }


    /**
	 * @return
	 * @uml.property  name="chipId"
	 */
    public BigInteger getChipId() {
        return chipId;
    }

    /**
	 * @return
	 * @uml.property  name="sequenceNumber"
	 */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
	 * @return
	 * @uml.property  name="dateCreated"
	 */
    public DateTime getDateCreated() {
        return dateCreated;
    }

    /**
	 * @return  the notitie
	 * @uml.property  name="notitie"
	 */
    public String getNotitie() {
        return notitie;
    }

    /**
	 * @param notitie  the notitie to set
	 * @uml.property  name="notitie"
	 */
    public void setNotitie(String notitie) {
        this.notitie = notitie;
    }

	/**
	 * @return  the id
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id  the id to set
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
