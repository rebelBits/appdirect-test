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
 *         &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "baseUrl", "partner" })
public class Marketplace {

	@XmlElement(required = true)
	protected String baseUrl;
	@XmlElement(required = true)
	protected String partner;

	/**
	 * Gets the value of the baseUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Sets the value of the baseUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBaseUrl(String value) {
		this.baseUrl = value;
	}

	/**
	 * Gets the value of the partner property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPartner() {
		return partner;
	}

	/**
	 * Sets the value of the partner property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPartner(String value) {
		this.partner = value;
	}

}