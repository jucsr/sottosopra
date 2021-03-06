//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 02:22:42 PM BRST 
//


package br.UFSC.GRIMA.application.entities.assets;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *         A cutting tool
 *       
 * 
 * <p>Java class for CuttingToolType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CuttingToolType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mtconnect.org:MTConnectAssets:1.3}AssetType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{urn:mtconnect.org:MTConnectAssets:1.3}AssetDescriptionType" minOccurs="0"/>
 *         &lt;element name="CuttingToolArchetypeReference" type="{urn:mtconnect.org:MTConnectAssets:1.3}CuttingToolArchetypeReferenceType" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="CuttingToolDefinition" type="{urn:mtconnect.org:MTConnectAssets:1.3}CuttingToolDefinitionType"/>
 *             &lt;element name="CuttingToolLifeCycle" type="{urn:mtconnect.org:MTConnectAssets:1.3}CuttingToolLifeCycleType" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="CuttingToolLifeCycle" type="{urn:mtconnect.org:MTConnectAssets:1.3}CuttingToolLifeCycleType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="serialNumber" use="required" type="{urn:mtconnect.org:MTConnectAssets:1.3}SerialNumberType" />
 *       &lt;attribute name="manufacturers" type="{urn:mtconnect.org:MTConnectAssets:1.3}ManufacturersType" />
 *       &lt;attribute name="toolId" use="required" type="{urn:mtconnect.org:MTConnectAssets:1.3}ToolIdType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuttingToolType", propOrder = {
    "rest"
})
public class CuttingToolType
    extends AssetType
{

    @XmlElementRefs({
        @XmlElementRef(name = "CuttingToolDefinition", namespace = "urn:mtconnect.org:MTConnectAssets:1.3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Description", namespace = "urn:mtconnect.org:MTConnectAssets:1.3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "CuttingToolArchetypeReference", namespace = "urn:mtconnect.org:MTConnectAssets:1.3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "CuttingToolLifeCycle", namespace = "urn:mtconnect.org:MTConnectAssets:1.3", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> rest;
    @XmlAttribute(name = "serialNumber", required = true)
    protected String serialNumber;
    @XmlAttribute(name = "manufacturers")
    protected String manufacturers;
    @XmlAttribute(name = "toolId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String toolId;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "CuttingToolLifeCycle" is used by two different parts of a schema. See: 
     * line 998 of file:/C:/Users/Sonir/workspace/MTConnect/src/br/UFSC/GRIMA/application/schema/MTConnectAssets_1.3.xsd
     * line 990 of file:/C:/Users/Sonir/workspace/MTConnect/src/br/UFSC/GRIMA/application/schema/MTConnectAssets_1.3.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CuttingToolArchetypeReferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AssetDescriptionType }{@code >}
     * {@link JAXBElement }{@code <}{@link CuttingToolDefinitionType }{@code >}
     * {@link JAXBElement }{@code <}{@link CuttingToolLifeCycleType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<?>>();
        }
        return this.rest;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the manufacturers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturers() {
        return manufacturers;
    }

    /**
     * Sets the value of the manufacturers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturers(String value) {
        this.manufacturers = value;
    }

    /**
     * Gets the value of the toolId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToolId() {
        return toolId;
    }

    /**
     * Sets the value of the toolId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToolId(String value) {
        this.toolId = value;
    }

}
