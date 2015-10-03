package nl.ns.rtsanalyzer.domain;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.joda.time.DateTime;

/**
 * Een {@link CiCoReisTransactie} beschrijft een CiCoReis die met de
 * {@link OvcpKaart} is gedaan. 
 * 
 */
public class CiCoReisTransactie extends ReisTransactie implements Writable {


	private static final long serialVersionUID = 5620690019683657662L;

	public static final String EMPTY = "empty";
	

	/**
	 * Vertrektijd en lokatie.
	 */
	private OVChipEvent vertrek;

	/**
	 * Aankomsttijd en lokatie.
	 * 
	 */
	private OVChipEvent aankomst;
	
	public CiCoReisTransactie() {
		super();
	}

	public CiCoReisTransactie(BigInteger chipId, Integer sequenceNumber,
			DateTime dateCreated, OVChipEvent vertrek, OVChipEvent aankomst,
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
		CiCoReisTransactie other = (CiCoReisTransactie) obj;
		return new EqualsBuilder().append(this.vertrek, other.vertrek).append(
				this.aankomst, other.aankomst).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.aankomst).append(this.vertrek)
				.hashCode();
	}

	/**
	 * @return
	 * 
	 */
	public OVChipEvent getVertrek() {
		return vertrek;
	}

	/**
	 * @return
	 * 
	 */
	public OVChipEvent getAankomst() {
		return aankomst;
	}

	/** 
	 * Wordt gebruikt bij inlezen van sequentiele dataset om CiCoReistransactie te construeren
	 * vanaf HDFS.
	 */
	@Override
	public void readFields(DataInput arg) throws IOException {
		this.chipId = BigInteger.valueOf(arg.readLong());
		this.dateCreated = new DateTime( WritableUtils.readString(arg));
		String kenmerkLabel = WritableUtils.readString(arg);
		if (kenmerkLabel.equals(EMPTY)) {
			this.kenmerk = null;
		} else {
			this.kenmerk = Kenmerk.getKenmerk(kenmerkLabel);
		}

		String aNotitie = WritableUtils.readString(arg);
		if(aNotitie.equals(EMPTY)){
			this.notitie = null;
		}
		else {
			this.notitie = aNotitie;
		}
		this.sequenceNumber = arg.readInt();
		this.klasse = Klasse.fromValue(WritableUtils.readString(arg));
		this.prijs = BigDecimal.valueOf(arg.readDouble());

		DateTime aankomstTime = new DateTime(WritableUtils.readString(arg));
		String aankomstLokatie = WritableUtils.readString(arg);

		this.aankomst = new OVChipEvent(aankomstTime, aankomstLokatie);

		DateTime vertrekTime = new DateTime(WritableUtils.readString(arg));
		String vertrekLokatie = WritableUtils.readString(arg);

		this.vertrek = new OVChipEvent(vertrekTime, vertrekLokatie);

	}

	/** 
	 * 
	 */
	@Override
	public void write(DataOutput arg) throws IOException {
		arg.writeLong(this.chipId.longValue());
		String date = this.dateCreated.toString();
		WritableUtils.writeString(arg , this.dateCreated.toString());
		WritableUtils.writeString(arg, this.kenmerk == null ? EMPTY : this.kenmerk.getLabel());
		WritableUtils.writeString(arg, this.notitie == null ? EMPTY : this.notitie);
		arg.writeInt(sequenceNumber);
		WritableUtils.writeString(arg, klasse.getKlasseNumber());
	    arg.writeDouble(prijs.doubleValue());

	    WritableUtils.writeString(arg, this.aankomst.getTijdstip().toString());
	    WritableUtils.writeString(arg, this.aankomst.getLokatie());

	    WritableUtils.writeString(arg, this.vertrek.getTijdstip().toString());
	    WritableUtils.writeString(arg, this.vertrek.getLokatie());

	}

}
