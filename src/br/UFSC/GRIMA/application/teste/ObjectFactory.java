//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.26 at 05:40:51 PM BRST 
//


package br.UFSC.GRIMA.application.teste;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.UFSC.GRIMA.application.teste package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Carro_QNAME = new QName("", "carro");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.UFSC.GRIMA.application.teste
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Motorista }
     * 
     */
    public Motorista createMotorista() {
        return new Motorista();
    }

    /**
     * Create an instance of {@link Carro }
     * 
     */
    public Carro createCarro() {
        return new Carro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Carro }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carro")
    public JAXBElement<Carro> createCarro(Carro value) {
        return new JAXBElement<Carro>(_Carro_QNAME, Carro.class, null, value);
    }

}