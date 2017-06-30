package com.sample.data.appdirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="editionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricingDuration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="items">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "editionCode", "pricingDuration", "items" })
public class Order {

	@XmlElement(required = true)
	private String editionCode;
	@XmlElement(required = true)
	private String pricingDuration;
	@XmlElement(required = true)
	private Items items;

	/**
	 * Gets the value of the editionCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEditionCode() {
		return editionCode;
	}

	/**
	 * Sets the value of the editionCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEditionCode(String value) {
		this.editionCode = value;
	}

	/**
	 * Gets the value of the pricingDuration property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPricingDuration() {
		return pricingDuration;
	}

	/**
	 * Sets the value of the pricingDuration property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPricingDuration(String value) {
		this.pricingDuration = value;
	}

	/**
	 * Gets the value of the items property.
	 * 
	 * @return possible object is {@link Order.Items }
	 * 
	 */
	public Items getItems() {
		return items;
	}

	/**
	 * Sets the value of the items property.
	 * 
	 * @param value
	 *            allowed object is {@link Order.Items }
	 * 
	 */
	public void setItems(Items value) {
		this.items = value;
	}
}