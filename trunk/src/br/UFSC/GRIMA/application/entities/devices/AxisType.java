//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 04:26:30 PM BRST 
//


package br.UFSC.GRIMA.application.entities.devices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         A abstract machine axis
 *       
 * 
 * <p>Java class for AxisType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AxisType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mtconnect.org:MTConnectDevices:1.3}CommonComponentType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AxisType")
@XmlSeeAlso({
    LinearType.class,
    SpindleType.class,
    RotaryType.class
})
public abstract class AxisType
    extends CommonComponentType
{


}