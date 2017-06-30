package com.sample.data.appdirect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
*         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="marketplace">
*           &lt;complexType>
*             &lt;complexContent>
*               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                 &lt;sequence>
*                   &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="partner" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                 &lt;/sequence>
*               &lt;/restriction>
*             &lt;/complexContent>
*           &lt;/complexType>
*         &lt;/element>
*         &lt;element name="creator">
*           &lt;complexType>
*             &lt;complexContent>
*               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                 &lt;sequence>
*                   &lt;element name="address">
*                     &lt;complexType>
*                       &lt;complexContent>
*                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                           &lt;sequence>
*                             &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                           &lt;/sequence>
*                         &lt;/restriction>
*                       &lt;/complexContent>
*                     &lt;/complexType>
*                   &lt;/element>
*                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="locale" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="openId" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                   &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                 &lt;/sequence>
*               &lt;/restriction>
*             &lt;/complexContent>
*           &lt;/complexType>
*         &lt;/element>
*         &lt;element name="payload">
*           &lt;complexType>
*             &lt;complexContent>
*               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                 &lt;sequence>
*                   &lt;element name="company">
*                     &lt;complexType>
*                       &lt;complexContent>
*                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                           &lt;sequence>
*                             &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
*                           &lt;/sequence>
*                         &lt;/restriction>
*                       &lt;/complexContent>
*                     &lt;/complexType>
*                   &lt;/element>
*                   &lt;element name="order">
*                     &lt;complexType>
*                       &lt;complexContent>
*                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                           &lt;sequence>
*                             &lt;element name="editionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="pricingDuration" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                             &lt;element name="items">
*                               &lt;complexType>
*                                 &lt;complexContent>
*                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*                                     &lt;sequence>
*                                       &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}byte"/>
*                                       &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
*                                     &lt;/sequence>
*                                   &lt;/restriction>
*                                 &lt;/complexContent>
*                               &lt;/complexType>
*                             &lt;/element>
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
@XmlType(name = "", propOrder = { "type", "marketplace", "creator", "payload" })
@XmlRootElement(name = "event")
public class SubscriptionEvent {

    @XmlElement(required = true)
    private EventType type;

    @XmlElement(required = true)
    private Marketplace marketplace;

    @XmlElement(required = true)
    private Creator creator;

    @XmlElement(required = true)
    private Payload payload;

    /**
     * Gets the value of the type property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public EventType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setType(final EventType value) {
        this.type = value;
    }

    /**
     * Gets the value of the marketplace property.
     * 
     * @return possible object is {@link Marketplace }
     * 
     */
    public Marketplace getMarketplace() {
        return marketplace;
    }

    /**
     * Sets the value of the marketplace property.
     * 
     * @param value
     *            allowed object is {@link Marketplace }
     * 
     */
    public void setMarketplace(final Marketplace value) {
        this.marketplace = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return possible object is {@link Creator }
     * 
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *            allowed object is {@link Creator }
     * 
     */
    public void setCreator(final Creator value) {
        this.creator = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return possible object is {@link Payload }
     * 
     */
    public Payload getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *            allowed object is {@link Payload }
     * 
     */
    public void setPayload(final Payload value) {
        this.payload = value;
    }

}