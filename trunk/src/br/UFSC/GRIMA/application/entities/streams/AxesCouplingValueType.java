//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 04:30:05 PM BRST 
//


package br.UFSC.GRIMA.application.entities.streams;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AxesCouplingValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AxesCouplingValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TANDEM"/>
 *     &lt;enumeration value="SYCHRONOUS"/>
 *     &lt;enumeration value="MASTER"/>
 *     &lt;enumeration value="SLAVE"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AxesCouplingValueType")
@XmlEnum
public enum AxesCouplingValueType {

    TANDEM,
    SYCHRONOUS,
    MASTER,
    SLAVE,
    UNAVAILABLE;

    public String value() {
        return name();
    }

    public static AxesCouplingValueType fromValue(String v) {
        return valueOf(v);
    }

}
