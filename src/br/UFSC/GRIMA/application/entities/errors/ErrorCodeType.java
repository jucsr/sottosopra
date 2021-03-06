//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 01:58:22 PM BRST 
//


package br.UFSC.GRIMA.application.entities.errors;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNAUTHORIZED"/>
 *     &lt;enumeration value="NO_DEVICE"/>
 *     &lt;enumeration value="OUT_OF_RANGE"/>
 *     &lt;enumeration value="TOO_MANY"/>
 *     &lt;enumeration value="INVALID_URI"/>
 *     &lt;enumeration value="INVALID_REQUEST"/>
 *     &lt;enumeration value="INTERNAL_ERROR"/>
 *     &lt;enumeration value="INVALID_PATH"/>
 *     &lt;enumeration value="UNSUPPORTED"/>
 *     &lt;enumeration value="ASSET_NOT_FOUND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorCodeType")
@XmlEnum
public enum ErrorCodeType {

    UNAUTHORIZED,
    NO_DEVICE,
    OUT_OF_RANGE,
    TOO_MANY,
    INVALID_URI,
    INVALID_REQUEST,
    INTERNAL_ERROR,
    INVALID_PATH,
    UNSUPPORTED,
    ASSET_NOT_FOUND;

    public String value() {
        return name();
    }

    public static ErrorCodeType fromValue(String v) {
        return valueOf(v);
    }

}
