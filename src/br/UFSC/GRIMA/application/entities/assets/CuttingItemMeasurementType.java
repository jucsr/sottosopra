//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 02:22:42 PM BRST 
//


package br.UFSC.GRIMA.application.entities.assets;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Measurements for the cutting item
 *       
 * 
 * <p>Java class for CuttingItemMeasurementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CuttingItemMeasurementType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;urn:mtconnect.org:MTConnectAssets:1.3>MeasurementType">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuttingItemMeasurementType")
@XmlSeeAlso({
    PointAngleType.class,
    ToolLeadAngleType.class,
    StepIncludedAngleType.class,
    ToolCuttingEdgeAngleType.class,
    CuttingHeightType.class,
    FunctionalWidthType.class,
    IncribedCircleDiameterType.class,
    CornerRadiusType.class,
    FlangeDiameterType.class,
    CuttingReferencePointType.class,
    StepDiameterLengthType.class,
    WiperEdgeLengthType.class,
    CuttingEdgeLengthType.class,
    CuttingDiameterType.class,
    InclinationAngleType.class
})
public abstract class CuttingItemMeasurementType
    extends MeasurementType
{


}