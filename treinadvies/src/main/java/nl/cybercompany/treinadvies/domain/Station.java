/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Station op het spoor.
 *
 * @author haiko
 *
 */
@Root(name="station", strict=false)
public class Station implements Serializable{

	public static Station createSimple(String name){
		return new Station(name, null, null);
	}

	@Element
	private String name;

	@Element
	private String code;

	@Element(name="country")
	private String countryCode;

	public Station() {
	}

	public Station(String name, String code, String countryCode) {
		super();
		this.name = name;
		this.code = code;
		this.countryCode = countryCode;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(obj instanceof Station) {
			Station s = (Station) obj;
			return new EqualsBuilder().append(s.name, this.name).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.name).toHashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
