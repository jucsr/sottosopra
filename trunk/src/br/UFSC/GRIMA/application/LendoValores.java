package br.UFSC.GRIMA.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import br.UFSC.GRIMA.application.entities.streams.MTConnectStreamsType;
import br.UFSC.GRIMA.application.visual.TesteValores;

public class LendoValores extends TesteValores
{
	private double x;
	private double y;
	private double z;
	public LendoValores() throws Exception
	{
		
		
		//		System.out.println("Your Fisrt Hello World Program" + "\n");
//		 URL MTConnect = new URL("http://agent.MTConnect.org/probe");
		URL MTConnect = new URL("http://agent.mtconnect.org/current");
		BufferedReader in = new BufferedReader(new InputStreamReader(MTConnect.openStream()));
		String inputLine;
		String ler = "";
		while ((inputLine = in.readLine()) != null) {
		//System.out.println(inputLine);
			ler = ler + "\n" + inputLine;
		}
		in.close();
		System.out.println(ler);
		System.out.println("\n" + "XML from probe command printed above");
//		//////////////////////////////////////////////////////////////////////
		JAXBContext jc = JAXBContext.newInstance(MTConnectStreamsType.class);
		Unmarshaller u = jc.createUnmarshaller();
		URL url = new URL( "http://agent.mtconnect.org/current" );
		JAXBElement<MTConnectStreamsType> element =(JAXBElement<MTConnectStreamsType>)u.unmarshal(url);
		MTConnectStreamsType teste = element.getValue();
		System.err.println("" + teste.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(2).getValue().getName());
//		textField1.setText(teste.getStreams().getDeviceStream().get(0).getComponentStream().get(0).getSamples().getSample().get(0).getValue().getValue());
//		textField2.setText(teste.getStreams().getDeviceStream().get(0).getComponentStream().get(1).getSamples().getSample().get(0).getValue().getValue());
//		textField3.setText(teste.getStreams().getDeviceStream().get(0).getComponentStream().get(2).getSamples().getSample().get(0).getValue().getValue());
//		System.err.println(teste.getStreams().getDeviceStream().get(0).getComponentStream().get(2).getSamples().getSample().get(0).getValue().getValue());
		this.setVisible(true);	
	}
}

