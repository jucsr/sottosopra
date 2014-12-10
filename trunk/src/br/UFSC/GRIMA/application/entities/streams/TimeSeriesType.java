//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 04:30:05 PM BRST 
//


package br.UFSC.GRIMA.application.entities.streams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         An abstract time series with the restriction value
 *       
 * 
 * <p>Java class for TimeSeriesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeSeriesType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;urn:mtconnect.org:MTConnectStreams:1.3>AbsTimeSeriesType">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeSeriesType")
@XmlSeeAlso({
    WattTimeSeriesType.class,
    AbsTimeSeriesTimeSeriesType.class,
    ConductivityTimeSeriesType.class,
    ViscosityTimeSeriesType.class,
    PowerFactorTimeSeriesType.class,
    AmperageTimeSeriesType.class,
    SoundPressureTimeSeriesType.class,
    AccelerationTimeSeriesType.class,
    FillLevelTimeSeriesType.class,
    AxisFeedrateTimeSeriesType.class,
    RotaryVelocityTimeSeriesType.class,
    AccumulatedTimeTimeSeriesType.class,
    LinearForceTimeSeriesType.class,
    PathFeedrateTimeSeriesType.class,
    FlowTimeSeriesType.class,
    VoltsTimeSeriesType.class,
    StrainTimeSeriesType.class,
    PressureTimeSeriesType.class,
    VelocityTimeSeriesType.class,
    GlobalPositionTimeSeriesType.class,
    AngleTimeSeriesType.class,
    WattageTimeSeriesType.class,
    FrequencyTimeSeriesType.class,
    TiltTimeSeriesType.class,
    DisplacementTimeSeriesType.class,
    TorqueTimeSeriesType.class,
    ConcentrationTimeSeriesType.class,
    ElectricalEnergyTimeSeriesType.class,
    TemperatureTimeSeriesType.class,
    LoadTimeSeriesType.class,
    VoltageTimeSeriesType.class,
    AngularVelocityTimeSeriesType.class,
    LengthTimeSeriesType.class,
    PathPositionTimeSeriesType.class,
    PositionTimeSeriesType.class,
    AngularAccelerationTimeSeriesType.class,
    SpindleSpeedTimeSeriesType.class,
    ResistanceTimeSeriesType.class
})
public abstract class TimeSeriesType
    extends AbsTimeSeriesType
{


}
