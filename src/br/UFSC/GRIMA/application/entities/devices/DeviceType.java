//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 04:26:30 PM BRST 
//


package br.UFSC.GRIMA.application.entities.devices;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The top level component managed by the agent
 *       
 * 
 * <p>Java class for DeviceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mtconnect.org:MTConnectDevices:1.3}ComponentType">
 *       &lt;attribute name="iso841Class" type="{urn:mtconnect.org:MTConnectDevices:1.3}Iso841ClassType" />
 *       &lt;attribute name="uuid" use="required" type="{urn:mtconnect.org:MTConnectDevices:1.3}UuidType" />
 *       &lt;attribute name="name" use="required" type="{urn:mtconnect.org:MTConnectDevices:1.3}NameType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceType")
public class DeviceType
    extends ComponentType
{

    @XmlAttribute(name = "iso841Class")
    protected BigInteger iso841Class;
    @XmlAttribute(name = "uuid", required = true)
    protected String uuid;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the iso841Class property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIso841Class() {
        return iso841Class;
    }

    /**
     * Sets the value of the iso841Class property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIso841Class(BigInteger value) {
        this.iso841Class = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
