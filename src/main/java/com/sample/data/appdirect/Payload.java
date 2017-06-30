package com.sample.data.appdirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

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
 *         &lt;element name="company">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="order">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="editionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="pricingDuration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="items">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                             &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
public class Payload {

	@XmlElement(required = true)
	private Company company;

	@XmlElement(required = true)
	private Order order;

	private Account account;

	private PayloadUser user;

	/**
	 * Gets the value of the company property.
	 * 
	 * @return possible object is {@link Company }
	 * 
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the value of the company property.
	 * 
	 * @param value
	 *            allowed object is {@link Company }
	 * 
	 */
	public void setCompany(Company value) {
		this.company = value;
	}

	/**
	 * Gets the value of the order property.
	 * 
	 * @return possible object is {@link Order }
	 * 
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the value of the order property.
	 * 
	 * @param value
	 *            allowed object is {@link Order }
	 * 
	 */
	public void setOrder(Order value) {
		this.order = value;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public PayloadUser getUser() {
		return user;
	}

	public void setUser(PayloadUser user) {
		this.user = user;
	}

}