//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 04:26:30 PM BRST 
//


package br.UFSC.GRIMA.application.entities.devices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         A list of generic components
 *       
 * 
 * <p>Java class for ComponentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComponentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:mtconnect.org:MTConnectDevices:1.3}Component" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComponentsType", propOrder = {
    "component"
})
public class ComponentsType {

    @XmlElementRef(name = "Component", namespace = "urn:mtconnect.org:MTConnectDevices:1.3", type = JAXBElement.class)
    protected List<JAXBElement<? extends ComponentType>> component;

    /**
     * Gets the value of the component property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the component property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AxisType }{@code >}
     * {@link JAXBElement }{@code <}{@link LubricationType }{@code >}
     * {@link JAXBElement }{@code <}{@link PneumaticType }{@code >}
     * {@link JAXBElement }{@code <}{@link SystemType }{@code >}
     * {@link JAXBElement }{@code <}{@link DeviceType }{@code >}
     * {@link JAXBElement }{@code <}{@link CommonComponentType }{@code >}
     * {@link JAXBElement }{@code <}{@link ControllerType }{@code >}
     * {@link JAXBElement }{@code <}{@link ComponentType }{@code >}
     * {@link JAXBElement }{@code <}{@link AxesType }{@code >}
     * {@link JAXBElement }{@code <}{@link SpindleType }{@code >}
     * {@link JAXBElement }{@code <}{@link DoorType }{@code >}
     * {@link JAXBElement }{@code <}{@link SystemsType }{@code >}
     * {@link JAXBElement }{@code <}{@link MaterialHandlerType }{@code >}
     * {@link JAXBElement }{@code <}{@link BarFeederType }{@code >}
     * {@link JAXBElement }{@code <}{@link SensorType }{@code >}
     * {@link JAXBElement }{@code <}{@link ActuatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link RotaryType }{@code >}
     * {@link JAXBElement }{@code <}{@link PowerType }{@code >}
     * {@link JAXBElement }{@code <}{@link InterfacesType }{@code >}
     * {@link JAXBElement }{@code <}{@link InterfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link LinearType }{@code >}
     * {@link JAXBElement }{@code <}{@link HydraulicType }{@code >}
     * {@link JAXBElement }{@code <}{@link CoolantType }{@code >}
     * {@link JAXBElement }{@code <}{@link ChuckInterfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link ElectricType }{@code >}
     * {@link JAXBElement }{@code <}{@link PathType }{@code >}
     * {@link JAXBElement }{@code <}{@link DoorInterfaceType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends ComponentType>> getComponent() {
        if (component == null) {
            component = new ArrayList<JAXBElement<? extends ComponentType>>();
        }
        return this.component;
    }

}
