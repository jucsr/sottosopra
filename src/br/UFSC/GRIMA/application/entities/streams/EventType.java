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
 *         An abstract event
 *       
 * 
 * <p>Java class for EventType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;urn:mtconnect.org:MTConnectStreams:1.3>ResultType">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventType")
@XmlSeeAlso({
    EndOfBarType.class,
    ControllerModeType.class,
    WorkholdingIdType.class,
    InterfaceStateType.class,
    ExecutionType.class,
    FunctionalModeType.class,
    RotaryModeType.class,
    CodeType.class,
    DirectionType.class,
    RotaryVelocityOverrideType.class,
    ToolNumberType.class,
    PathFeedrateOverrideType.class,
    AxisStateType.class,
    ChuckStateType.class,
    ProgramHeaderType.class,
    ActiveAxesType.class,
    AlarmType.class,
    ProgramCommentType.class,
    ChuckInterlockType.class,
    ToolAssetIdType.class,
    LineType.class,
    PalletIdType.class,
    PathModeType.class,
    AxesCouplingType.class,
    EmergencyStopType.class,
    PowerStatusType.class,
    PowerStateType.class,
    ProgramType.class,
    AxisInterlockType.class,
    ToolIdType.class,
    AssetChangedType.class,
    PartCountType.class,
    AssetRemovedType.class,
    DoorStateType.class,
    AxisFeedrateOverrideType.class,
    AvailabilityType.class,
    PartAssetIdType.class,
    OperatorIdType.class,
    CoupledAxesType.class,
    MessageType.class,
    ActuatorStateType.class,
    BlockType.class,
    PartIdType.class,
    InterfaceEventType.class
})
public abstract class EventType
    extends ResultType
{


}
