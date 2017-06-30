package com.sample.data.appdirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "quantity", "unit" })
public class Items {

	/**
	 * Property used to store the items quantity
	 */
	private byte quantity;

	/**
	 * Property used to store the items unit
	 */
	@XmlElement(required = true)
	private String unit;

	/**
	 * Gets the value of {@link #quantity}.
	 * 
	 */
	public byte getQuantity() {
		return quantity;
	}

	/**
	 * Sets the value for {@link #quantity}.
	 * 
	 */
	public void setQuantity(byte value) {
		this.quantity = value;
	}

	/**
	 * Gets the value of {@link #unit}
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the value for {@link #unit}
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUnit(String value) {
		this.unit = value;
	}

}